package com.robin.ds.hashing;

/**
 * A Hash Table Interface.
 * 
 * @author robin
 * 
 * @param <K>
 *           the Key param
 * @param <E>
 *           the Element/Value param
 */
public interface HashTable<K, E> {

   /**
    * Method looks-up the specified key value and returns the value there. In
    * case the key does not exists it will return null
    * 
    * @param key
    * @return E
    */
   public E lookUp(K key);

   /**
    * Method will insert a value in the table.
    * 
    * @param key
    * @param value
    * @return E
    */
   public E insert(K key, E value);

   /**
    * Method will delete a value from the table, freeing up the slot.
    * 
    * @param key
    */
   public void delete(K key);

   /**
    * Returns the number of items in the table.
    * 
    * @return int value
    */
   public int count();

   /**
    * Method checks if the HashTable is empty.
    * 
    * @return boolean value
    */
   public boolean isEmpty();
}
