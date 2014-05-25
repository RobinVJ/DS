package com.robin.ds.hashing;

/**
 * Class represents a hash function that is used by Hashing Tables to identify
 * the lookup index.
 * 
 * @author robin
 * 
 * @param <K>
 */
public interface IHashFunction<K> {

   public static final int NULL_HASH = 0;

   /**
    * Method will generate a (int based) numerical hash from the passed Key
    * <blockquote> A good hash function satisfies (approximately) the assumption
    * of simple uniform hashing: each key is equally likely to hash to any of
    * the m slots, independently of where any other key has hashed to.
    * </blockquote>
    * 
    * @param key
    * @return int value
    */
   int hash(K key);

   /**
    * Method is used to set the upperbound on max value that the hash Function
    * can generate.
    * 
    * @param hashLimit
    */
   void setHashLimit(int hashLimit);
}
