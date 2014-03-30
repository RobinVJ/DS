package test.robin.ds;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.robin.ds.ArrayBasedList;

public class TestArrayBasedList {

   @Test
   public void create() {
      ArrayBasedList<Integer> integers = new ArrayBasedList<>(new Integer[] { 1, 2, 3 });
      assertTrue(!integers.isEmpty());
      ArrayBasedList<Number> numbers = new ArrayBasedList<Number>(integers);
      assertEquals(3, numbers.size());
      numbers = new ArrayBasedList<Number>(3);
      assertEquals(0, numbers.size());
   }

   @Test
   public void getSet() {
      ArrayBasedList<Integer> integers = new ArrayBasedList<>(new Integer[] { 1, 2, 3 });
      assertEquals(Integer.valueOf(1), integers.get(0));
      assertEquals(Integer.valueOf(2), integers.get(1));
      assertEquals(Integer.valueOf(3), integers.get(2));
      integers.set(0, -1);
      assertEquals(Integer.valueOf(-1), integers.get(0));
   }

   @Test(expected = UnsupportedOperationException.class)
   public void setInvalidCase1() {
      ArrayBasedList<Integer> integers = new ArrayBasedList<>();
      integers.set(1, 1);
   }

   @Test(expected = UnsupportedOperationException.class)
   public void setInvalidCase2() {
      ArrayBasedList<Integer> integers = new ArrayBasedList<>();
      integers.set(-11, 1);
   }

   @Test(expected = UnsupportedOperationException.class)
   public void setInvalidCase3() {
      ArrayBasedList<Integer> integers = new ArrayBasedList<>(new Integer[] { 1, 2, 3 });
      assertEquals(Integer.valueOf(3), Integer.valueOf(integers.size()));
      integers.set(3, 3);
   }

   @Test
   public void addRemove() {
      ArrayBasedList<Integer> integers = new ArrayBasedList<>(new Integer[] { 1, 2, 3, 4, 5, 6 });
      assertFalse(integers.isEmpty());
      assertEquals(Integer.valueOf(4), integers.get(3));
      integers.remove(3);
      assertEquals(Integer.valueOf(5), Integer.valueOf(integers.size()));
      assertEquals(Integer.valueOf(5), integers.get(3));
      integers.remove(4);
      assertEquals(Integer.valueOf(4), Integer.valueOf(integers.size()));
      integers.add(3, 4);
      integers.add(5, 6);
      assertEquals(Integer.valueOf(6), Integer.valueOf(integers.size()));
      assertEquals(Integer.valueOf(6), integers.get(5));
   }
   
 
}
