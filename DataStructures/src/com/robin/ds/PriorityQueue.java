package com.robin.ds;

import java.util.HashMap;
import java.util.Map;

/**
 * Priority Queue implementation that is based on a Heap.
 * 
 * @author robin
 * 
 * @param <E>
 *           - represents an element. Each element must be unique.
 * @param <K>
 *           - represents a priority assigned to Element E
 */
public class PriorityQueue<E, K extends Comparable<K>> {

   /**
    * Represents an Element and its associated Key.
    * 
    * @author robin
    * 
    */
   class Entry implements Comparable<Entry> {
      K key;
      E element;

      @Override
      public String toString() {
         return "E [ k: " + key + ", e : " + element + " ]";
      }

      @Override
      public int hashCode() {
         return element.hashCode();
      }

      @Override
      @SuppressWarnings("unchecked")
      public boolean equals(Object obj) {
         if (obj == null || obj.getClass().equals(Entry.class)) {
            return false;
         }
         if (obj == this) {
            return true;
         }
         Entry otherEntry = (Entry) obj;
         return otherEntry.key.equals(key);// key can never be null;
      }

      @Override
      public int compareTo(Entry otherEntry) {
         return this.key.compareTo(otherEntry.key);
      }
   }

   /**
    * Internal Heap class used by the Priority Queue Implementation. Overrides
    * the add, up-heap and down heap methods.
    * 
    * @author robin
    * 
    */
   class InternalHeap extends Heap<Entry> {

      /**
       * Constructor.
       * 
       * @param minHeap
       *           - boolean value indicates type of heap. True value will
       *           indicate MinHeap else MaxHeap
       */
      public InternalHeap(boolean minHeap, int initialCapacity) {
         super(minHeap, initialCapacity);
      }

      public void add(Entry newVal) {
         processHeapIndex.put(newVal.element, super.size()); // track the new
                                                             // insertion
         super.add(newVal);
      }

      /**
       * Method overrides the upHeap operation in the Heap class.<br/>
       * <quote> When you upheap a node, you compare its value to its parent
       * node; if its value is less than its parent node, then you switch the
       * two nodes and continue the process. Otherwise the condition is met that
       * the parent node is less than the child node, and so you can stop the
       * process. </quote>
       * 
       * As a node location changes, the method keeps track of changing indexes.
       * 
       * @param index
       */
      @SuppressWarnings("unchecked")
      protected void heapUp(int index) {
         Entry value = (Entry) array[index];
         while (true) {
            int parent = parent(index);
            if (parent == index) {
               break;// root reached
            }
            if (hasGreaterValue(value, (Entry) array[parent])) {
               array[index] = array[parent];// bring parent down
               processHeapIndex.put(((Entry) array[index]).element, index);
               index = parent; // continue up the heap - O(log n)
            } else {
               break;// heap is OK
            }
         }
         array[index] = value;// insert value in correct location
         processHeapIndex.put(((Entry) array[index]).element, index);
      }

      /**
       * The method will heap down from the specified index value. It is an
       * overridden method. It keeps track of index changes within the heap
       * elements.
       * 
       * @param index
       */
      @SuppressWarnings("unchecked")
      protected void heapDown(int index) {
         int left = left(index);
         int right = right(index);
         int largest = index;
         if (left < super.size() && left >= 0 && hasGreaterValue((Entry) array[left], (Entry) array[index])) {
            largest = left;
         }
         if (right < super.size() && right >= 0 && hasGreaterValue((Entry) array[right], (Entry) array[largest])) {
            largest = right;
         }
         if (largest != index) {
            Entry temp = (Entry) array[index];
            array[index] = array[largest];
            processHeapIndex.put(((Entry) array[index]).element, index);
            array[largest] = temp;
            processHeapIndex.put(temp.element, largest);
            heapDown(largest); // continue the downHeap process - log(n)
         }
      }

   }

   private Map<E, Integer> processHeapIndex; // points to location in heap
   // structure needed to ensure n log n performance
   private Heap<Entry> heap;

   /**
    * Constructor
    * 
    * @param minPriority
    *           - ascending or descending priorities
    */
   public PriorityQueue(boolean minPriority) {
      processHeapIndex = new HashMap<>();
      heap = new InternalHeap(minPriority, 10);
   }

   /**
    * Method indicates the next element that will be returned by PriorityQueue.
    * Returns null if no element in Queue.
    * 
    * @return E
    */
   public E viewPriorityElement() {
      Entry top = heap.peak();
      return null != top ? top.element : null;
   }

   /**
    * Method will return the element for PriorityQueue with highest priority.
    * 
    * @return E
    */
   public E getPriorityElement() {
      Entry highestPriority = heap.remove();
      if (null != highestPriority) {
         processHeapIndex.remove(highestPriority.element);
         return highestPriority.element;
      }
      return null;
   }

   /**
    * Method is used to add a value to the Queue. If element already exists it
    * will not add returning false.
    * 
    * @param element
    * @param key
    * @return boolean value
    */
   public boolean add(E element, K key) throws RuntimeException {
      if (processHeapIndex.containsKey(element)) {
         return false;
      }
      Entry entry = new Entry();
      entry.key = key;
      entry.element = element;
      heap.add(entry);
      return true;
   }

   /**
    * Method is used to better the priority value of an existing Queue element.
    * The priority can only be improved. For a min Priority Queue the value of
    * an individual element can only be reversed. Conversely the priority value
    * can only be increased for a Max Priority Queue.
    * 
    * @param existingElement
    * @param newKey
    * @throws IllegalArgumentException
    *            if element does not exists !
    */
   @SuppressWarnings("unchecked")
   public void improveKeyValue(E existingElement, K newKey) throws IllegalArgumentException {
      Integer arrayIndex = processHeapIndex.get(existingElement);
      if (null == arrayIndex) {
         throw new IllegalArgumentException("Element passed does not exists in Priority Queue !!  - " + existingElement);
      } else {
         int index = arrayIndex.intValue();
         Entry entry = (Entry) heap.array[index];
         K originalKeyValue = entry.key;
         entry.key = newKey;
         boolean validValue = heap.heapPropertySatisfiedForChildren(index);
         if (!validValue) {
            entry.key = originalKeyValue;
            throw new IllegalArgumentException("Value not allowed for current index");
         } else {
            heap.heapUp(index); //fix the heap
         }
      }
   }

   /**
    * Method indicates if an element is already in the PriorityQueue
    * 
    * @param element
    * @return boolean value
    */
   public boolean containsElement(E element) {
      return this.processHeapIndex.containsKey(element);
   }

   /**
    * Method gives the size of the PriorityQueue.
    * 
    * @return int value
    */
   public int size() {
      return this.heap.size();
   }

   /**
    * Method indicates if heap is empty.
    * 
    * @return boolean value
    */
   public boolean isEmpty() {
      return this.size() == 0;
   }

}
