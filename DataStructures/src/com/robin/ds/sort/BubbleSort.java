package com.robin.ds.sort;

/**
 * Method is used to perform bubble sort.
 * 
 * @author robin
 * 
 */
class BubbleSort {

   /**
    * sort method
    * 
    * @param values
    */
   static <T extends Comparable<T>> void sort(T[] values) {
      for (int i = 0; i < values.length; i++) {
         boolean swapNeeded = false;
         // Every run sorts the element in (length-i) position
         for (int j = 1; j < values.length - i; j++) {
            // compare adjacent elements
            if (values[j - 1].compareTo(values[j]) > 0) {
               // swap
               T temp = values[j - 1];
               values[j - 1] = values[j];
               values[j] = temp;
               swapNeeded = true;
            }
         }
         if (!swapNeeded) {
            break; // no swaps needed anymore implies array already sorted
         }
      }
   }
}
