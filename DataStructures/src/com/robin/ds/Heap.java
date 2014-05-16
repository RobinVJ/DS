package com.robin.ds;

import java.util.Collection;

/**
 * Class implements a generic Heap with option for both minHeap and max Heap
 * 
 * @author robin
 * 
 * @param <T>
 */
public class Heap<T extends Comparable<T>> {

   // allow subclasses to have access to the underlying heap representation.
   protected Object[] array; // starts at 0
   
   private int size = 0;
   private int capacity = 0;
   private boolean minHeap;

   private static final int MAX_CAPACITY = Integer.MAX_VALUE - 10000;
   

   /**
    * Constructor.
    * 
    * @param minHeap
    *           - boolean value indicates type of heap. True value will indicate
    *           MinHeap else MaxHeap
    */
   public Heap(boolean minHeap) {
      this(minHeap, 10);
   }

   /**
    * Constructor will create a Heap with the passed collection as the initial
    * values for the Heap.
    * 
    * @param minHeap
    * @param initialValues
    */
   public Heap(boolean minHeap, Collection<T> initialValues) {
      this(minHeap, initialValues.size());
      int i = 0;
      for (T val : initialValues) {
         this.array[i++] = val;
      }
      // Fix the internal nodes in the Heap.
      buildHeap(initialValues.size());
   }

   /**
    * Method creates a heap using values from the passed array as inital heap
    * values.
    * 
    * @param minHeap
    * @param initialValues
    */
   public Heap(boolean minHeap, T[] initialValues) {
      this(minHeap, initialValues.length);
      int i = 0;
      for (T val : initialValues) {
         this.array[i++] = val;
      }
      // Fix the internal nodes in the Heap.
      buildHeap(initialValues.length);
   }

   /**
    * Will create an empty Heap of specified size.
    * 
    * @param minHeap
    * @param initialCapacity
    */
   public Heap(boolean minHeap, int initialCapacity) {
      if (initialCapacity >= MAX_CAPACITY) {
         throw new IllegalArgumentException("Heap size way over allowed value " + MAX_CAPACITY);
      }
      this.minHeap = minHeap;
      this.capacity = initialCapacity;
      this.array = new Object[this.capacity];
   }

   /**
    * Method adds a value to the Heap. If value already exists it will still be
    * added
    * 
    * @param newVal
    */
   public void add(T newVal) {
      resize(); // is needed
      array[size++] = newVal;// value added at end
      heapUp(size - 1); // fix the heap
   }

   /**
    * Method allows you to see the next value that will be returned by Heap. If
    * Heap empty returns null.
    * 
    * @return T
    */
   @SuppressWarnings("unchecked")
   public T peak() {
      return (size == 0) ? null : (T) array[0];
   }

   /**
    * Method removes the value at root of Heap. If Heap empty returns null.
    * 
    * @return T
    */
   @SuppressWarnings("unchecked")
   public T remove() {
      T value = null;
      if (size != 0) {
         value = (T) array[0];
         array[0] = array[size - 1]; // last element at size-1
         size--;
         if (size != 0) {
            heapDown(0);
         }
      }
      return value;
   }

   /**
    * Method indicates if heap is empty.
    * 
    * @return boolean value
    */
   public boolean isEmpty() {
      return size == 0;
   }

   /**
    * Method will return the heap size.
    * 
    * @return
    */
   public int size() {
      return size;
   }

   /**
    * Method is used to perform the upHeap operation.<br/>
    * <quote> When you upheap a node, you compare its value to its parent node;
    * if its value is less than its parent node, then you switch the two nodes
    * and continue the process. Otherwise the condition is met that the parent
    * node is less than the child node, and so you can stop the process.
    * </quote>
    * 
    * @param node
    */
   @SuppressWarnings("unchecked")
   protected void heapUp(int index) {
      T value = (T) array[index];
      while (true) {
         int parent = parent(index);
         if (parent == index) {
            break;// root reached
         }
         if (hasGreaterValue(value, (T) array[parent])) {
            array[index] = array[parent];// bring parent down
            index = parent; // continue up the heap - O(log n)
         } else {
            break;// heap is OK
         }
      }
      array[index] = value;// insert value in correct location
   }

   /**
    * The method will heap down from the specified index value.
    * 
    * @param index
    */
   @SuppressWarnings("unchecked")
   protected void heapDown(int index) {
      int left = left(index);
      int right = right(index);
      int largest = index;
      if (left < size && left >= 0 && hasGreaterValue((T) array[left], (T) array[index])) {
         largest = left;
      }
      if (right < size && right >= 0 && hasGreaterValue((T) array[right], (T) array[largest])) {
         largest = right;
      }
      if (largest != index) {
         Object temp = array[index];
         array[index] = array[largest];
         array[largest] = temp;
         heapDown(largest); // continue the downHeap process - log(n)
      }
   }

   /**
    * Method will return the index of parent. (parent for 0 will be returned as
    * 0)
    * 
    * @param index
    * @return int value
    */
   protected int parent(int index) {
      // complexity resulting from having used a 0 indexed array !!
      if (index == 0) {
         return 0;
      }
      return (index -1) >> 1;
   }

   /**
    * Method will return the index for the left child (even if child index is
    * beyond array size).
    * 
    * @param index
    * @return index value
    */
   protected int left(int index) {
      // return 2 * i +1;
      if (index == 0) {
         return 1;
      }
      int num = index << 1;
      return num + 1;
   }

   /**
    * Method will return the index for the right child (even if child index is
    * beyond array size).
    * 
    * @param i
    * @return
    */
   protected int right(int index) {
      // return 2 * i +2; // more efficiently done as a left shift
      if (index == 0) {
         return 2;
      }
      return (index << 1)+2;
   }

   /**
    * Method checks if for a given node its direct children satisfy the heap
    * property.
    * 
    * @param index
    * @return {@link Boolean} value
    */
   @SuppressWarnings("unchecked")
   protected boolean heapPropertySatisfiedForChildren(int index) {
      int left = left(index);
      int right = right(index);
      boolean valid = true;
      if (left < size && left >= 0 && hasGreaterValue((T) array[left], (T) array[index])) {
         valid = false;
      }
      if (right < size && right >= 0 && hasGreaterValue((T) array[right], (T) array[index])) {
         valid = false;
      }
      return valid;
   }

   /**
    * Method indicate if the passed value is greater than the second value
    * subject to Heap strategy
    * 
    * @param val1
    * @param val2
    * @return
    */
   protected boolean hasGreaterValue(T val1, T val2) { 
      boolean val1Greater = false;
      if (minHeap) {
         val1Greater = val1.compareTo(val2) < 0;
      } else {
         val1Greater = val1.compareTo(val2) > 0;
      }
      return val1Greater;
   }

   // private boolean isEqual(T val1, T val2) {
   // return val1.equals(val2);
   // }

   /**
    * Method will resize the Heap if the capacity still exists.
    */
   private void resize() {
      if (size == MAX_CAPACITY) {
         throw new RuntimeException("Heap Overflow - cannt grow Heap any further !!");
      }
      if (size == capacity) {
         // need to resize to add a new element
         Object[] newHeap = new Object[size * 2];
         for (int i = 0; i < array.length; i++) {
            newHeap[i] = array[i];
         }
         this.array = null;
         this.array = newHeap;
         capacity = newHeap.length;
      }
   }
   
   /**
    * Method is called as a part of the heap creation process.
    */
   private void buildHeap(int heapSize) {
      size = heapSize;
      for (int i = size / 2; i >= 0; i--) {
         heapDown(i);
      }
   }

}
