package com.robin.ds.hashing;

/**
 * An open addressing table that uses Hashing along with Probing technique.
 * 
 * @author robin
 * 
 * @param <K>
 *           the key
 * @param <E>
 *           the element
 */
public class OpenAddressingTable<K, E> {

   private IProbeFunction probeFunction;
   private IHashFunction<K> hashFunction;
   private int count;
   private Object[] values;

   /**
    * Class represents an entry in the values.
    * 
    * @author robin
    * 
    */
   private class Entry {
      private K key;
      private E element;

      public Entry(K key, E element) {
         this.key = key;
         this.element = element;
      }

      @Override
      public String toString() {
         return "E [ k: " + key + ", e : " + element + " ]";
      }

   }

   private final Object DELETED = new Object();

   /**
    * Creates a {@link OpenAddressingTable} with specified number of buckets.
    * Uses Division Hashing Technique and Linear Probing.
    * 
    * @param size
    */
   public OpenAddressingTable(int size) {
      if (size < 1) {
         throw new IllegalArgumentException("Invalid size input");
      }
      int tableSize = findPrimeBiggerThan(2 * size);
      init(tableSize, new DivisionHash<K>(tableSize), new LinearProbing(tableSize));
   }

   /**
    * Creates a OpenAddressingTable with specified number of buckets Default
    * access - can only be created within the package
    * 
    * @param size
    * @param probeFunction
    *           - the type of Hashing function available for use.
    */
   OpenAddressingTable(int size, IHashFunction<K> hashFunction, IProbeFunction probeFunction) {
      if (size < 1) {
         throw new IllegalArgumentException("Invalid size input");
      }
      int tableSize = findPrimeBiggerThan(2 * size);
      hashFunction.setHashLimit(tableSize);
      probeFunction.setProbeLimit(tableSize);
      init(tableSize, hashFunction, probeFunction);
   }

   /**
    * Method looks-up the specified key value and returns the value associated.
    * In case the key does not exists it will return null
    * 
    * @param key
    * @return E
    */
   @SuppressWarnings("unchecked")
   public E lookUp(K key) {
      if (count == 0) { // no entries in table
         return null;
      }
      int length = values.length;
      int probeLocn = hash(key);
      probeFunction.initializeProbe();
      for (int i = 0; i < length; i++) {
         if (values[probeLocn] == null) {
            break;// value not there
         } else if (values[probeLocn] == DELETED) {
            // ignore a deleted mark
         } else if ((key == null && ((Entry) values[probeLocn]).key == null)
               || (key != null && key.equals(((Entry) values[probeLocn]).key))) {
            return ((Entry) values[probeLocn]).element;
         }
         probeLocn = probeFunction.probeNext(probeLocn);
      }
      return null;
   }

   /**
    * Method will insert a value in the table.
    * 
    * @param key
    * @param element
    * @return E the old value
    */
   public E insert(K key, E element) {
//      System.out.println("Count of elements is " + count + " in aray of size " + values.length);
      E oldValue = null;
      try {
         oldValue = insertEntry(key, element);
      } catch (IllegalStateException illegalStateException) {
         // need to rehash the table
         rehash();
         oldValue = insertEntry(key, element);
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
      if (count == 0) {
         return; // table is empty
      }
      int length = values.length;
      int probeLocn = hash(key);
      probeFunction.initializeProbe();
      for (int i = 0; i < length; i++) {
         if (values[probeLocn] == null) {
            break;// value not there
         } else if (values[probeLocn] == DELETED) {
         // DELETED instance must be ignore
         } else if ((key == null && ((Entry) values[probeLocn]).key == null)
               || (key != null && key.equals(((Entry) values[probeLocn]).key))) {
            values[probeLocn] = DELETED;
            count--;// value found
         }// DELETED instance must be ignore
         probeLocn = this.probeFunction.probeNext(probeLocn);
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
    * Method is responsible for performing the rehash operation on the Hash
    * table. It will double the table size and also remove all Deleted entries.
    */
   @SuppressWarnings("unchecked")
   protected void rehash() {
      
      int newLength = this.findPrimeBiggerThan(values.length * 2);
//      System.out.println("Count of elements is " + count + " in aray of size " + values.length + " Raising it to " + newLength);
      Object[] newArray = new Object[newLength];
      probeFunction.setProbeLimit(newLength);
      hashFunction.setHashLimit(newLength);
      //int originalCount = this.count;
      Object[] originalArray = this.values;
      this.values = newArray;
      this.count = 0;

      for (Object object : originalArray) {
         if (object == null || object == DELETED) {
            continue;
         }
         Entry e = (Entry) object;
         insert(e.key, e.element);
      }

    //  assert (this.count == originalCount) : " Value loss during rehash";
   }

   /**
    * Method is responsible for initialization of the Hash Table.
    * 
    * @param size
    * @param hashFunction
    * @param probeFunction
    */
   private void init(int size, IHashFunction<K> hashFunction, IProbeFunction probeFunction) {
      this.probeFunction = probeFunction;
      this.hashFunction = hashFunction;
      values = new Object[size];
      count = 0;
   }

   /**
    * Method will return a prime number that occurs after the passed parameter
    * 
    * @param i
    * @return int value
    */
   private int findPrimeBiggerThan(int i) {
      int number = i + 1;
      while (true) {
         boolean hasfactorial = false;
         for (int divisor = 2; divisor <= (int) Math.sqrt(number); divisor++) {
            if (number % divisor == 0) {
               hasfactorial = true;
               break;
            }
         }
         if (!hasfactorial) {
            return number; // a prime number
         }
         number++;// try next number
      }
      // Return smallest prime number
   }

   /**
    * Method will insert a value into the HashTable
    * 
    * @param key
    * @param element
    * @return
    */
   @SuppressWarnings("unchecked")
   private E insertEntry(K key, E element) throws IllegalStateException {
      E oldValue = null;
      boolean insertDone = false;
      int length = values.length;
      probeFunction.initializeProbe();
      int probeLocn = hash(key);
      for (int i = 0; i < length;) {
         if (values[probeLocn] == null || values[probeLocn] == DELETED) {
            // insert the entry in this location
            values[probeLocn] = new Entry(key, element);
            count++;
            insertDone = true;
            break;
         } else if (nullSafeEquals(key, ((Entry) values[probeLocn]).key)) {
            Entry entry = ((Entry) values[probeLocn]);
            // update the entry as key already exists
            oldValue = entry.element;
            entry.element = element;
            insertDone = true;
            break;
         } else {
            i++; // continue looking
         }
         probeLocn = probeFunction.probeNext(probeLocn);
      }
      if (!insertDone) {
         throw new IllegalStateException("Failed to insert - Rehash needed !!");
      }
      return oldValue;
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
