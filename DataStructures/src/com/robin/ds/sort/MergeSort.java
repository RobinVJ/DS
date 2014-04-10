package com.robin.ds.sort;

/**
 * Class provides an implementation of the merge sort algorithm
 * 
 * @author robin
 * 
 */
class MergeSort {

   /**
    * Basic method to perform sorting using merge sort
    * 
    * @param values
    * @param left
    * @param right
    */
   static <E extends Comparable<E>> void mergeSort(E[] values, int left, int right) {
      // Check for base case
      if (left < right) { // case for split - greater than 1 element
         int mid = (left + right) / 2; // Divide step
         mergeSort(values, left, mid);
         mergeSort(values, mid + 1, right);
         merge(values, left, mid, right); // Conquer step
      }

   }

   /**
    * The merge method - O(n)
    * @param values
    * @param left
    * @param mid
    * @param right
    */
   @SuppressWarnings({ "rawtypes", "unchecked" })
   private static <E extends Comparable<E>> void merge(E[] values, int left, int mid, int right) {

      // assume len =8
      // so params are left =0, right =7 and mid =3
      int leftLength = mid - left + 1;// 3-0+1 =4
      int rightLength = right - mid; // 7-3 =4

      Comparable[] leftValues = new Comparable[leftLength];// left array of size
                                                           // 4
      int index = 0;
      for (int i = left; i <= mid; i++) {
         leftValues[index++] = values[i];// copy from left side ( 0 to 3)
      }

      Comparable[] rightValues = new Comparable[rightLength];// left array of
                                                             // size 4
      index = 0;
      for (int i = mid + 1; i <= right; i++) {
         rightValues[index++] = values[i];// copy from left side (4 to 7)
      }

      int i = 0, j = 0, count = left;
      while (i < leftLength && j < rightLength) {
         if (leftValues[i].compareTo(rightValues[j]) < 0) {
            values[count++] = (E) leftValues[i++];
         } else {
            values[count++] = (E) rightValues[j++];
         }
      }
      while (i < leftLength) {
         values[count++] = (E) leftValues[i++];
      }
      while (j < rightLength) {
         values[count++] = (E) rightValues[j++];
      }
   }
}