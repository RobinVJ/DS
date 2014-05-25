package com.robin.ds.hashing;

/**
 * Class defines the logic for probing. <blockquote>If faced with a collision
 * situation in a Hashtable, the table will look onto to subsequent hash
 * elements until the first free space is found.
 * 
 * This traversal is known as probing the table</blockquote>
 * 
 * @author robin
 */
public interface IProbeFunction {

   /**
    * Method computes the next probe location given the previous probed
    * location.
    * 
    * @param index
    * @return int value
    */
   int probeNext(int index);

   /**
    * Method is used to reinitialize/reset any settinsg necessary to make the
    * probe work.
    */
   void initializeProbe();

   /**
    * Method is used to update the probe limit.
    * 
    * @param limit
    */
   void setProbeLimit(int limit);
}
