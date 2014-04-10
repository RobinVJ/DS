package com.robin.ds.sort;

/**
 * Method is used to perform quick sort.
 * 
 * @author robin
 * 
 */
class QuickSort {

   static <E extends Comparable<E>> void sort(E[] values, int low, int high) {
      if (low < high) {
         int pivot = partition(values, low, high);
         sort(values, low, pivot - 1);
         sort(values, pivot + 1, high);
      }
   }

   private static <E extends Comparable<E>> int partition(E[] values, int low, int high) {
      // int pivotVal = values[high];
      int i = low - 1;
      for (int j = low; j < high; j++) {
         if (values[j].compareTo(values[high]) < 0) {
            i = i + 1;
            // swap
            {
               E temp = values[i];
               values[i] = values[j];
               values[j] = temp;
            }
         }
      }
      E temp = values[i + 1];
      values[i + 1] = values[high];
      values[high] = temp;
      return i + 1;
   }
}
