package com.robin.ds.sort;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Class implements combsort an improved version of bubblesort.
 * <p/>
 * Worst case performance Omega(n^2)
 * <p/>
 * Best case performance O(n)
 * <p/>
 * Average case performance Omega(n^2/2^p), where p is the number of increments
 * <p/>
 * 
 * @author robin
 * 
 */
public class CombSort {

   static <T extends Comparable<T>> void sort(T[] values) {
      final BigDecimal SHRINK_FACTOR = new BigDecimal("1.3");
      int arrayLength = values.length;
      int gap = arrayLength;
      while (gap != 1) {
         gap = new BigDecimal(gap).divide(SHRINK_FACTOR, RoundingMode.FLOOR).intValue();
         // floor used to ensure that gap /shrink factor does not round off to
         // gap again
         // possible for small values
         System.out.println("gap = " + gap);
         // boolean swapped = false;
         for (int i = 0; (gap + i) < arrayLength; i++) {
            if (values[i].compareTo(values[i + gap]) > 0) {
               T swap = values[i];
               values[i] = values[i + gap];
               values[i + gap] = swap;
               // swapped = true; when the gap is large possible no swaps occur.
               // So flag optimization may not work
            }
         }
         // if (!swapped) {
         // break;// array is sorted
         // }
      }
   }

}
