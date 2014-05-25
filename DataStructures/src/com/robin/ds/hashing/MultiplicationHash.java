package com.robin.ds.hashing;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Class represents a multiplication based hash technique.
 * 
 * @author robin
 * 
 * @param <K>
 */
public class MultiplicationHash<K> implements IHashFunction<K> {

   private BigDecimal constant; // in the range of (0,1)
   // 0.6180339887 - [(root(5) -1)/2]suggested by Knuth
   private BigDecimal multiplicand;

   public MultiplicationHash(int multiplicand) {
      this.multiplicand = new BigDecimal(multiplicand); // this naming is ok
      constant = new BigDecimal("0.6180339887");
   }

   /**
    * <blockquote> An advantage of the multiplication method is that the value
    * of m is not critical. We typically choose it to be a power of 2, since we
    * can then easily implement the function on most computers as follows.
    * </blockquote>
    */
   @Override
   public int hash(K key) {
      if (key == null) {
         return NULL_HASH;
      }
      // Multiply key by Constant
      // Take the decimal part of the product (hence the modulo 1 operation)
      // Multiply this with the multiplicand parameter
      return new BigDecimal(key.hashCode()).multiply(constant, MathContext.DECIMAL32)
            .remainder(new BigDecimal("1").multiply(multiplicand, MathContext.DECIMAL32)).intValue();
   }

   @Override
   public void setHashLimit(int hashLimit) {
      this.multiplicand = new BigDecimal(hashLimit);
   }

}
