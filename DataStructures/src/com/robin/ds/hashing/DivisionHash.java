package com.robin.ds.hashing;

/**
 * Class represents a division based hash technique.
 * 
 * @author robin
 * 
 * @param <K>
 */
public class DivisionHash<K> implements IHashFunction<K> {

   int divisor;

   public DivisionHash(int divisor) {
      this.divisor = divisor;
   }

   /**
    * When using the division method, we usually avoid certain values of m. For
    * example, m should not be a power of 2, since if m = 2^p, then hash(k) is just
    * the p lowest-order bits of k. Unless we know that all low-order p-bit
    * patterns are equally likely, we are better off designing the hash function
    * to depend on all the bits of the key.
    * A prime not too close to an exact power of 2 is often a good choice for m.
    */
   @Override
   public int hash(K key) {
      if (key == null) {
         return NULL_HASH;
      }
      return key.hashCode() % divisor;
   }

   @Override
   public void setHashLimit(int hashLimit) {
      this.divisor = hashLimit;
      
   }

}
