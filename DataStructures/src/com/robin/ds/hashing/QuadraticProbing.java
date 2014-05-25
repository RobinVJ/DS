package com.robin.ds.hashing;

/**
 * <blockquote> An example sequence using quadratic probing is:
 * <code>H + 1^2 , H + 2^2 , H + 3^2 , H + 4^2 , ... , H + k^2</code>
 * <p/>
 * This method works much better than linear probing, but to make full use of
 * the hash table, the values of constants 'c1', 'c2', and 'm' are constrained.
 * <p/>
 * If two keys have the same initial probe position, then their probe sequences
 * are the same, since h(k1,0)= h(k2, 0) implies h(k1, i) = h(k2,i). This
 * property leads to a milder form of clustering, called secondary clustering.
 * As in linear probing, the initial probe determines the entire sequence, and
 * so only m distinct probe sequences are used.</blockquote>
 * 
 * @author robin
 * 
 */
public class QuadraticProbing implements IProbeFunction {

   private int size;
   private int incr = 0;

   public QuadraticProbing(int size) {
      this.size = size;
   }

   /**
    * Will initialize the probe for use
    */
   @Override
   public void initializeProbe() {
      incr = 0;
   }

   @Override
   public int probeNext(int index) {
      int incrSqr = (incr + 1) * (incr + 1);
      int nextLocn = (index + incrSqr) % size;
      incr++;
      return nextLocn;
   }

   public static void main(String[] args) {
      int incr = 0;
      int size = 13;
      int index = 0;// original hash locn
      for (int i = 0; i < size; i++) {

         int incrSqr = (incr + 1) * (incr + 1);
         System.out.println("index + incrSqr " + (index + incrSqr));
         int nextLocn = (index + incrSqr) % size;
         incr++;
         System.out.println("next" + nextLocn);
      }
   }

   @Override
   public void setProbeLimit(int limit) {
      this.size = limit;
   }

}
