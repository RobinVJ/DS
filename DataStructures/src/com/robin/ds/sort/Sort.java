package com.robin.ds.sort;

/**
 * Includes sorting methods
 * 
 * @author robin
 * 
 */
public class Sort {

   /**
    * Perform sorting on the given array using mergesort
    * 
    * @param values
    */
   public static <E extends Comparable<E>> void mergeSort(E[] values) {
      MergeSort.mergeSort(values, 0, values.length - 1);
   }

   /**
    * Perform sorting on the given array using insertionSort
    * 
    * @param values
    */
   public static <E extends Comparable<E>> void insertionSort(E[] values) {
      InsertionSort.sort(values);
   }

   /**
    * Perform sorting on the given array using quickSort
    * 
    * @param values
    */
   public static <E extends Comparable<E>> void quickSort(E[] values) {
      QuickSort.sort(values, 0, values.length - 1);
   }
}
