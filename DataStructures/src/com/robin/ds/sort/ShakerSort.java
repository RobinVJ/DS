package com.robin.ds.sort;

/**
 * Class implements the shaker sort or bidirectional bubble sort or cocktail sort algorithm
 * Complexity worst case n^2 and best case n
 * @author robin
 *
 */
class ShakerSort {

   static <E extends Comparable<E>> void sort(E values[]) {
      int begin = 0;
      int end = values.length - 1;
      while (begin < end) {
         int min = begin;
         int max = begin;
         for (int i = begin + 1; i <= end; i++) {
            if (values[i].compareTo(values[min]) < 0) {
               min = i;
            }
            if (values[i].compareTo(values[max]) > 0) {
               max = i;
            }
         }
         // min and max refer to smallest and largest elements in array

         // swap min and begin element
         E swap = values[min];
         values[min] = values[begin];
         values[begin] = swap;

         if (max == begin) {
            // swap the max and min(original begin)
            swap = values[min];
            values[min] = values[end];
            values[end] = swap;
         } else {
            swap = values[max];
            values[max] = values[end];
            values[end] = swap;
         }
         // smallest and largest elements in correct locations
         // exclude these values from future sorts
         begin++;
         end--;
      }
   }
}
