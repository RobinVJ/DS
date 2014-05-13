package com.robin.ds.sort;

import com.robin.ds.Heap;

/**
 * Sorting algorithm based on heap. Can be done in-place too by treating the
 * array as a heap. Done here using the {@link com.robin.ds.Heap} class created
 * earlier. Time Complexity: O(n log n). BUILD-MAXHEAP takes time O(n) and each
 * of the n - 1 calls to MAX-HEAPIFY takes time O(log n)
 * 
 * @author robin
 * 
 */
public class HeapSort {

   static <T extends Comparable<T>> void sort(T[] values) {
      Heap<T> heap = new Heap<T>(true, values);
      int length = values.length;
      for (int i = 0; i < length; i++) {
         values[i] = heap.remove();
      }
   }
}
