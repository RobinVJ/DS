package com.robin.ds.hashing;

/**
 * A Direct Address Table - holds a set of values whose keys are unique values
 * in the range of 0 to maxValue-1.
 * 
 * @author robin
 * 
 * @param <E>
 *           the Elements in the Table
 */
public class DirectAddressTable<E> {
   // represents a null valued Element
   private static final Object NULL = new Object();

   private Object[] values;
   private int count;

   /**
    * Constructor: Instance holds keys denoted by T[0,1..., keyMax-1], in which
    * each position, or slot, corresponds to a key in the universe U.
    * 
    * @param keyMax
    */
   public DirectAddressTable(int keyMax) {
      if (keyMax < 1) {
         throw new IllegalArgumentException("Invalid size input");
      }
      values = new Object[keyMax];
      count = 0;
   }

   /**
    * Method looks-up the specified key value and returns the value there. In
    * case the key is invalid (key<0 or key> mavValue-1) it will throw an
    * {@link IllegalArgumentException}
    * 
    * @param key
    * @return E
    * @throws IllegalArgumentException
    */
   @SuppressWarnings("unchecked")
   public E lookUp(int key) throws IllegalArgumentException {
      if (validKey(key)) {
         return values[key] == NULL ? null : (E) values[key];
      } else {
         throw new IllegalArgumentException("lookUp performed at invalid location");
      }
   }

   /**
    * Method will insert a value in the table.
    * 
    * @param value
    * @param key
    * @return E
    */
   @SuppressWarnings("unchecked")
   public E insert(E value, int key) throws IllegalArgumentException {
      if (validKey(key)) {
         E oldValue = (E) values[key];
         values[key] = value == null ? NULL : value;
         if (null == oldValue) {
            count++; // a slot has been occupied
         }
         return oldValue == NULL ? null : oldValue;
      } else {
         throw new IllegalArgumentException("insert performed at invalid location");
      }
   }

   /**
    * Method will delete a value from the table, freeing up the slot.
    * 
    * @param key
    */
   @SuppressWarnings("unchecked")
   public void delete(int key) throws IllegalArgumentException {
      if (validKey(key)) {
         E oldValue = (E) values[key];
         if (null != oldValue) {
            count--; // a slot has been freed
         }
         values[key] = null;
      } else {
         throw new IllegalArgumentException("delete performed at invalid location");
      }
   }

   /**
    * Method indicates if a slot is empty.
    * 
    * @param key
    * @return boolean value.
    */
   public boolean isSlotEmpty(int key) {
      return values[key] == null;
   }

   /**
    * Method returns the size of the table.
    * 
    * @return int value
    */
   public int size() {
      return values.length;
   }

   /**
    * Method gives a count of the free slots in the table.
    * 
    * @return int value
    */
   public int emptySlotCount() {
      return values.length - count;
   }

   /**
    * Method will indicate if a key is invalid.
    * 
    * @param key
    * @return boolean value.
    */
   private boolean validKey(int key) {
      return key >= 0 && key < values.length;
   }
}
