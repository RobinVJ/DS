package com.robin.ds;

/**
 * Class is a simplified array based list. it grows dynamically as per needs.
 * 
 * @author robin
 * 
 * @param <E>
 */
public class ArrayBasedList<E> {

   private static final int DEFAULT_LENGTH = 10;
   private int size = 0; // no of elements in the list
   private Object[] array; // the backing array

   /**
    * Default constructor
    */
   public ArrayBasedList() {
      this(DEFAULT_LENGTH);
   }

   /**
    * Creates an arraylist with the specified capacity
    * 
    * @param capacity
    */
   public ArrayBasedList(int capacity) {
      // array = new E[length]; -> cannot create a generic array
      array = new Object[capacity];
   }

   /**
    * Adds the values in the passed array to this arraylist
    * 
    * @param initialValues
    */
   public ArrayBasedList(E[] initialValues) {
      // array = new E[length]; cannot create a generic array
      array = new Object[initialValues.length];
      int i = 0;
      for (E value : initialValues) {
         array[i++] = value;
      }
      size = array.length;
   }

   /**
    * Copy Constructor
    * 
    * @param initialValues
    */
   public ArrayBasedList(ArrayBasedList<? extends E> initialValues) {
      // array = new E[length]; cannot create a generic array
      int paramSize = initialValues.size();
      array = new Object[paramSize];

      for (int i = 0; i < paramSize; i++) {
         array[i] = initialValues.get(i);
      }
      // for (E value : initialValues) {
      // array[i++] = value;
      // } - support the special interface
      size = paramSize;
   }

   /**
    * Method will return number of elements in the array.
    * 
    * @return int value
    */
   public int size() {
      return size;
   }

   /**
    * Method indicates if array is empty
    * 
    * @return boolean value
    */
   public boolean isEmpty() {
      return 0 == size;
   }

   /**
    * Method is used to get value at a particular index
    * 
    * @param index
    * @return previous value
    */
   @SuppressWarnings("unchecked")
   public E get(int index) {
      checkValidAccess(index);
      return (E) array[index];
   }

   /**
    * Method is used to set a value at a selected index
    * 
    * @param index
    * @param newVal
    * @return value
    */
   // can only set for values from start to end
   public E set(int index, E newVal) {
      checkValidAccess(index);
      @SuppressWarnings("unchecked")
      E oldValue = (E) array[index];
      array[index] = newVal;
      return oldValue;
   }

   /**
    * Method is used to add a value at a particular index (add maximum upto
    * index = size)
    * 
    * @param index
    * @param newVal
    */
   public void add(int index, E newVal) {
      if (index > size) {
         throw new UnsupportedOperationException("Attempt to use an invalid Index " + index + " max value " + size);
      }
      // check invalid and throw exception
      ensureCapacityFor(size + 1);
      if (index < size) {
         // need to push values
         for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
         }
         array[index] = newVal;
      } else {// if (index == size) {
         // inserting in a new location
         array[index] = newVal;
      }
      size++;
   }

   /**
    * Method is used to remove an element at specified index
    * 
    * @param index
    * @return removed value
    */
   public E remove(int index) {
      checkValidAccess(index); // handles case >index-1
      @SuppressWarnings("unchecked")
      E oldValue = (E) array[index];
      if (index == size - 1) {
         // remove from end
         array[index] = null;
      } else {// if (index < size - 1) {
         for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
         }
         array[size - 1] = null;
      }
      size--;
      return oldValue;
   }

   /*
    * ----------------------PRIVATE METHODS-------------------------
    */

   /**
    * Method will check if the request is valid for a given index, if not it
    * will throw an exception.
    * 
    * @param index
    */
   private void checkValidAccess(int index) {
      if (index < 0 || index > size - 1) {
         throw new UnsupportedOperationException("Attempt to use an invalid Index " + index + " with size  from 0 to "
               + (size - 1));
      }

   }

   /**
    * Method will check if capacity present to insert element in ith location.
    * if not it will enlargen the array.
    * 
    * @param i
    */
   private void ensureCapacityFor(int i) {
      if (array.length > i)
         return;
      else
         resize(i * 3 / 2);
   }

   /**
    * Method will resize the array.
    * 
    * @param newCapacity
    */
   private void resize(int newCapacity) {
      Object[] newArray = new Object[newCapacity];
      for (int i = 0; i < size; i++) {
         newArray[i] = array[i];
      }
      array = null;
      array = newArray;
   }

}
