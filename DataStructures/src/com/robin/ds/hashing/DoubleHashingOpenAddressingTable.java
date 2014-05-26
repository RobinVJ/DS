package com.robin.ds.hashing;

/**
 * An open addressing table that uses the Double Hashing technique. <blockquote>
 * Double hashing can perform hashing well but is more complicated and likely to
 * be slower than quadratic probing. </blockquote>
 * 
 * @author robin
 * 
 * @param <K>
 *           the key
 * @param <E>
 *           the element
 */
public class DoubleHashingOpenAddressingTable<K, E> extends AbstractArrayHashTable<K, E> {

   private int primeValBelowSize;

   /**
    * Creates a {@link DoubleHashingOpenAddressingTable} with specified number
    * of buckets. Uses Division Hashing Technique. (Minimum size = 4)
    * 
    * @param size
    */
   public DoubleHashingOpenAddressingTable(int size) {
      if (size < 4) {
         throw new IllegalArgumentException("Invalid size input");
      }
      values = new Object[size];
      count = 0;
      primeValBelowSize = computePrimeValueBelowLength(size);
   }

   @SuppressWarnings("unchecked")
   public E lookUp(K key) {
      if (count == 0) { // no entries in table
         return null;
      }
      int tableLen = values.length;
      int hash1 = hashFn1(key);
      int hash2 = hashFn2(key);
      int i = 0;
      E matchValue = null;
      // Case 2: - when table is full and the key not present
      while (values[hash1] != null && i++ <= tableLen) {
         if (DELETED.equals(values[hash1])) {
            continue;// nothing to be done
         }

         K k = ((Entry) values[hash1]).key;
         if (nullSafeEquals(key, k)) {
            matchValue = ((Entry) values[hash1]).element;
            break;
         }
         // compute next offset
         hash1 = (hash1 + hash2) % tableLen;
      }
      return matchValue;
   }

   public E insert(K key, E element) {
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

   @SuppressWarnings("unchecked")
   public void delete(K key) {
      if (count == 0) {
         return; // table is empty
      }
      int tableLen = values.length;
      int hash1 = hashFn1(key);
      int hash2 = hashFn2(key);
      int i = 0;
      while (values[hash1] != null && i++ < tableLen) {
         if (!DELETED.equals(values[hash1])) {
            K k = ((Entry) values[hash1]).key;
            if (nullSafeEquals(key, k)) {
               values[hash1] = DELETED;
               count--;
               break;
            }
         }
         // compute next offset
         hash1 = (hash1 + hash2) % tableLen;
      }
   }

   @Override
   protected void updateRehashSettings(int newLength) {
      primeValBelowSize = computePrimeValueBelowLength(newLength);
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
      int tableLen = values.length;
      int hash1 = hashFn1(key); // the first location to check
      int hash2 = hashFn2(key); // the offset to use

      // In this technique the offset is also dependent on the hash, unlike the
      // probe techniques where the offset only depends on loop index
      int i = 0;
      while (values[hash1] != null && values[hash1] != DELETED && i++ < tableLen) {
         // check for an update case
         Entry crtEntry = (Entry) values[hash1];
         if (nullSafeEquals(key, crtEntry.key)) {
            oldValue = crtEntry.element;
            crtEntry.element = element;
            return oldValue;// update complete
         }
         // else compute next offset
         hash1 = (hash1 + hash2) % tableLen;
      }
      if (i >= tableLen) {
         // the insertion did not happen
         throw new IllegalStateException("Failed to insert - Rehash needed !!");
      }
      // new object in a brand new location (null) or in a previously used
      // location (DELETED)
      values[hash1] = new Entry(key, element); // insert the new value
      count++;
      return null;
   }

   /**
    * This will compute the first hash value.
    * 
    * @param key
    * @return int value
    */
   private int hashFn1(K key) {
      if (null == key) {
         return 0;
      }
      int x = key.hashCode();
      int hash1OfX = x % values.length; // h1(k) = k%m ( m is size)
      return hash1OfX;
   }

   /**
    * <blockquote>If a collision occurs, a second hash function is applied to x
    * and then multiplied by i.</blockquote>
    * 
    * @param key
    * @return int value
    */
   private int hashFn2(K key) {
      if (null == key) {
         return 0;
      }
      int x = key.hashCode(); // same hash value retrieved
      int hash2OfX = 1 + (x % primeValBelowSize);
      return hash2OfX;
   }

   /**
    * Method will return a prime number less than the passed input.
    * 
    * @param size
    * @return int value
    */
   private int computePrimeValueBelowLength(int size) {
      for (int number = values.length - 1; number >= 1; number--) {
         int factorialCount = 0;
         for (int divisor = 2; divisor <= (int) Math.sqrt(number); divisor++) {
            if (number % divisor == 0) {
               factorialCount++;
            }
         }
         if (factorialCount == 0)
            return number; // a prime number
      }
      // Return smallest prime number
      return 3;
   }

}
