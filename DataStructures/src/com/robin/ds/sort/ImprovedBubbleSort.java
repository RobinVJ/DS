package com.robin.ds.sort;

/**
 * Method is used to perform insertion sort.
 * 
 * @author robin
 * 
 */
class ImprovedBubbleSort {
   /**
    * sort method
    * 
    * @param values
    */
   static <T extends Comparable<T>> void sort(T[] values) {
      int arrLength = values.length;
      do {
         int unsortedArrLength = 0;
         for (int i = 1; i < arrLength; i++) {
            // compare adjacent elements
            if (values[i - 1].compareTo(values[i]) > 0) {
               // swap
               T temp = values[i - 1];
               values[i - 1] = values[i];
               values[i] = temp;
               unsortedArrLength = i;// the length of unsorted array
               //values from unsortedArrLength to length are sorted
            }
         }
         arrLength = unsortedArrLength;
      } while (arrLength != 0);

   }
}
