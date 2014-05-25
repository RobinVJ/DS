package com.robin.ds.hashing;

/**
 * Class uses a hash based table with chaining used for collision resolutions.
 * 
 * @author robin
 * 
 * @param <E>
 *           Element
 * @param <K>
 *           Key - must be unique
 */
public class ChainHashTable<K, E> {

   IHashFunction<K> hashFunction;
   private int count;
   Object[] values;

   /**
    * Using a double linked list to get good performance for deletion
    * 
    * @author robin
    * 
    */
   private class Entry {
      K key;
      E element;
      Entry prev, next;

      @Override
      public String toString() {
         return "E [ k: " + key + ", e : " + element + " ]";
      }
   }

   /**
    * Creates a ChainHashTable with specified number of buckets. Uses Division
    * Hashing Technique.
    * 
    * @param size
    */
   public ChainHashTable(int size) {
      this(size, new DivisionHash<K>(size));
   }

   /**
    * Creates a ChainHashTable with specified number of buckets Default access -
    * can only be created within the package
    * 
    * @param size
    * @param hashFunction
    *           - the type of Hashing function avalabe for use.
    */
   ChainHashTable(int size, IHashFunction<K> hashFunction) {
      if (size < 1) {
         throw new IllegalArgumentException("Invalid size input");
      }
      values = new Object[size];
      count = 0;
      this.hashFunction = hashFunction;
   }

   /**
    * Method looks-up the specified key value and returns the value there. In
    * case the key does not exists it will return null
    * 
    * @param key
    * @return E
    */
   @SuppressWarnings("unchecked")
   public E lookUp(K key) {
      int index = hash(key);
      Entry e = (Entry) values[index];
      if (null == e) {
         return null; // key not present
      }

      while (e != null) {
         if (nullSafeEquals(key, e.key)) {
            return e.element;
         }
         e = e.next;
      }
      return null; // no match found after collision either
   }

   /**
    * Method will insert a value in the table.
    * 
    * @param key
    * @param value
    * @return E
    */
   @SuppressWarnings("unchecked")
   public E insert(K key, E value) {
      E oldValue = null;
      int index = hash(key);
      Entry e = (Entry) values[index];
      if (null == e) {// no entries in chain
         Entry entry = new Entry();
         entry.key = key;
         entry.element = value;
         values[index] = entry;
         count++;
      } else {
         // check if the item is already in - if yes update
         boolean insertDone = false;
         Entry temp = e;
         while (temp != null) {
            if (nullSafeEquals(key, temp.key)) {
               oldValue = temp.element;
               temp.element = value;
               insertDone = true;
               break;
            }
            temp = temp.next;
         }
         if (!insertDone) { // do an insert
            Entry entry = new Entry();
            entry.key = key;
            entry.element = value;
            entry.next = e;
            e.prev = entry;
            values[index] = entry;// added in beginning of list
            count++;
         }
      }
      return oldValue;
   }

   /**
    * Method will delete a value from the table, freeing up the slot.
    * 
    * @param key
    */
   @SuppressWarnings("unchecked")
   public void delete(K key) {
      int index = hash(key);
      Entry e = (Entry) values[index];
      if (null == e) {
         return; // key not present
      }

      while (e != null) {
         if (nullSafeEquals(key, e.key)) {
            if (e == (Entry) values[index]) {
               values[index] = e.next;
               if (null != e.next) {
                  e.next.prev = null;
               }
            } else {
               e.prev.next = e.next;
               if (null != e.next) {
                  e.next.prev = e.prev;
               }
            }
            e = null;
            count--;
            break;
         }
         e = e.next;
      }

   }

   /**
    * Returns the number of items in the table.
    * 
    * @return int value
    */
   public int count() {
      return this.count;
   }

   /**
    * Method checks if the HashTable is empty.
    * 
    * @return boolean value
    */
   public boolean isEmpty() {
      return count() == 0;
   }

   /**
    * Method implements the Hashing method
    * 
    * @param key
    * @return int value
    */
   protected int hash(K key) {
      return hashFunction.hash(key);
   }

   /**
    * Method checks if the two keys are both null or are they both equivalent to
    * each other.
    * 
    * @param key1
    * @param key2
    * @return boolean value
    */
   private boolean nullSafeEquals(K key1, K key2) {
      return (key1 == null && key2 == null) /* both keys are null */
            /* are actually equal and key1 is not null */
            || (key1 != null && key1.equals(key2));
   }

}
