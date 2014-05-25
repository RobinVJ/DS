package com.robin.ds.hashing;

/**
 * <blockquote>Linear probing is easy to implement, but it suffers from a
 * problem known as primary clustering. Long runs of occupied slots build up,
 * increasing the average search time. Clusters arise because an empty slot
 * preceded by i full slots gets filled next with probability (i+1)/m. Long runs
 * of occupied slots tend to get longer, and the average search time
 * increases.</blockquote>
 * 
 * @author robin
 * 
 */
public class LinearProbing implements IProbeFunction {

   private int size;

   public LinearProbing(int size) {
      this.size = size;
   }

   @Override
   public int probeNext(int index) {
      return (index + 1) % size;
   }

   @Override
   public void initializeProbe() {
      // do nothing
   }

   @Override
   public void setProbeLimit(int limit) {
      this.size = limit;
   }

}
