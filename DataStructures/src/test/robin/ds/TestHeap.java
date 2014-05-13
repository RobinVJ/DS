package test.robin.ds;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Test;

import com.robin.ds.Heap;

public class TestHeap {

   @Test
   public void createHeap() {
      Heap<Integer> integers = new Heap<>(true, new Integer[] { 2, 1, 3 });
      assertFalse(integers.isEmpty());
      assertEquals(3, integers.size());
      assertEquals(Integer.valueOf(1), integers.peak());

      Heap<Integer> numbers = new Heap<>(false, Arrays.asList(new Integer[] { 1, 2, 3, 2, 1 }));
      assertEquals(5, numbers.size());
      assertEquals(Integer.valueOf(3), numbers.peak());

      integers = new Heap<Integer>(true);
      assertEquals(0, integers.size());
      assertEquals(null, integers.peak());
      assertTrue(integers.isEmpty());
   }

   @Test(expected = IllegalArgumentException.class)
   public void createInvalidSizeHeap() {
      new Heap<BigDecimal>(true, Integer.MAX_VALUE);
   }

   @Test
   public void removePeak() {
      Heap<Integer> integers = new Heap<>(false, new Integer[] { 4, 1, 3, 2, 16, 9, 10, 14, 8, 7 });
      assertEquals(10, integers.size());
      assertEquals(Integer.valueOf(16), integers.remove());
      assertEquals(Integer.valueOf(14), integers.remove());
      assertEquals(Integer.valueOf(10), integers.remove());
      assertEquals(7, integers.size());
      assertEquals(Integer.valueOf(9), integers.remove());
      assertEquals(Integer.valueOf(8), integers.remove());
      assertEquals(Integer.valueOf(7), integers.remove());
      assertEquals(Integer.valueOf(4), integers.remove());
      assertEquals(Integer.valueOf(3), integers.remove());
      assertEquals(Integer.valueOf(2), integers.remove());
      assertEquals(Integer.valueOf(1), integers.remove());
      assertEquals(0, integers.size());
      assertEquals(null, integers.remove());
   }
   
   @Test
   public void addPeak() {
      Heap<Integer> integers = new Heap<>(false,3); 
      assertEquals(0, integers.size());
      
      integers.add(7);
      assertEquals(Integer.valueOf(7), integers.peak());
      integers.add(4);
      assertEquals(Integer.valueOf(7), integers.peak());
      integers.add(8);
      assertEquals(Integer.valueOf(8), integers.peak());
      
      integers.remove();
      assertEquals(Integer.valueOf(7), integers.peak());
      
      integers.add(1);
      assertEquals(Integer.valueOf(7), integers.peak());
      integers.add(3);
      assertEquals(Integer.valueOf(7), integers.peak());
      
      integers.add(8);
      integers.add(2);
      assertEquals(Integer.valueOf(8), integers.peak());
      integers.add(9);
      assertEquals(Integer.valueOf(9), integers.peak());
      
      integers.add(10);
      assertEquals(Integer.valueOf(10), integers.peak());
      integers.add(14);
      assertEquals(Integer.valueOf(14), integers.peak());
      integers.add(16);
      assertEquals(Integer.valueOf(16), integers.peak());
      
   }
}
