package com.robin.ds;

/**
 * Interface represents a list data structure.
 * 
 * @author robin
 * 
 * @param <E>
 *           The item held in the List
 */
public interface MyList<E> {

   /**
    * Method will return number of elements in the List.
    * 
    * @return int value
    */
   public int size();

   /**
    * Method indicates if array is List
    * 
    * @return boolean value
    */
   public boolean isEmpty();

   /**
    * Method is used to get value at a particular index
    * 
    * @param index
    * @return previous value
    */
   public E get(int index);

   /**
    * Method is used to set a value at a selected index
    * 
    * @param index
    * @param newVal
    * @return value
    */
   // can only set for values from start to end
   public E set(int index, E newVal);

   /**
    * Method is used to add a value at a particular index (add maximum upto
    * index = size)
    * 
    * @param index
    * @param newVal
    */
   public void add(int index, E newVal);

   /**
    * Method is used to remove an element at specified index
    * 
    * @param index
    * @return removed value
    */
   public E remove(int index);

}
