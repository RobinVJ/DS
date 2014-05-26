package com.robin.ds.hashing;

/**
 * Class forms the base implementation for Array based Hash Tables.
 * 
 * @author robin
 * 
 * @param <K>
 * @param <E>
 */
public abstract class AbstractArrayHashTable<K, E> implements HashTable<K, E> {

   /**
    * Class represents an entry in the values.
    * 
    * @author robin
    * 
    */
   protected class Entry {
      K key;
      E element;

      public Entry(K key, E element) {
         this.key = key;
         this.element = element;
      }

      @Override
      public String toString() {
         return " (K: " + key + " E : " + element + " )";
      }
   }

   protected int count;
   protected Object[] values;
   protected final Object DELETED = new Object();

   public int count() {
      return this.count;
   }

   public boolean isEmpty() {
      return count() == 0;
   }

   /**
    * This method is called to reinitialize the HashTable settings after the
    * array has been grown to the specified parameter value.
    * 
    * @param newLength
    */
   protected abstract void updateRehashSettings(int newLength);

   /**
    * This method is called to rehash the HashTable.
    */
   @SuppressWarnings("unchecked")
   protected void rehash() {
      int newLength = this.findPrimeBiggerThan(values.length * 2);
      // System.out.println("Count of elements is " + count +
      // " in aray of size " + values.length + " Raising it to " + newLength);
      Object[] newArray = new Object[newLength];
      updateRehashSettings(newLength);

      // int originalCount = this.count;
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
      // assert (this.count == originalCount) : " Value loss during rehash";
   }

   /**
    * Method will return a prime number that occurs after the passed parameter
    * 
    * @param i
    * @return int value
    */
   protected int findPrimeBiggerThan(int i) {
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
    * Method checks if the two keys are both null or are they both equivalent to
    * each other.
    * 
    * @param key1
    * @param key2
    * @return boolean value
    */
   protected boolean nullSafeEquals(K key1, K key2) {
      return (key1 == null && key2 == null) /* both keys are null */
            /* are actually equal and key1 is not null */
            || (key1 != null && key1.equals(key2));
   }
}
