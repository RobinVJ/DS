package test.robin.ds;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.robin.ds.sort.Sort;

public class TestQuickSort {

   @Test
   public void testNormalArray() {
      Integer[] numbers = new Integer[] { 10, 98, 9, 7, 65, 14, 1, 2 };
      Sort.quickSort(numbers);
      Assert.assertArrayEquals(new Integer[] { 1, 2, 7, 9, 10, 14, 65, 98 }, numbers);
   }

   @Test
   public void testReverseSorted() {
      BigDecimal[] numbers = new BigDecimal[] { new BigDecimal("100"), new BigDecimal("98"), new BigDecimal("89"),
            new BigDecimal("77"), new BigDecimal("65"), new BigDecimal("34.8"), new BigDecimal("34.1"),
            new BigDecimal("0.876"), BigDecimal.ZERO };
      Sort.quickSort(numbers);
      BigDecimal[] expected = new BigDecimal[] { BigDecimal.ZERO, new BigDecimal("0.876"), new BigDecimal("34.1"),
            new BigDecimal("34.8"), new BigDecimal("65"), new BigDecimal("77"), new BigDecimal("89"),
            new BigDecimal("98"), new BigDecimal("100") };
      assertArrayEquals(expected, numbers);
   }

   @Test
   public void testSortedArray() {
      String[] chars = new String[] { "A", "Ball", "CAT", "Diii", "Dzimim", "EEE", "Eee", "ZZZ" };
      Sort.quickSort(chars);
      String[] expected = new String[] { "A", "Ball", "CAT", "Diii", "Dzimim", "EEE", "Eee", "ZZZ" };
      assertArrayEquals(expected, chars);
   }
}
