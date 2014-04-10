package com.robin.ds.sort;

/**
 * Method is used to perform insertion sort.
 * 
 * @author robin
 * 
 */
class InsertionSort {

   /**
    * sort method
    * @param values
    */
   static <T extends Comparable<T>> void sort(T[] values) {
      for (int i = 0; i < values.length; i++) {
         T key = values[i];
         int j = i - 1;
         while (j >= 0 && values[j].compareTo(key) > 0) {
            // move the bigger number a step behind
            values[j + 1] = values[j];
            j--;
         }
         values[j + 1] = key;
      }
   }
}
