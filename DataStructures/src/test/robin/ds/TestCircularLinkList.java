package test.robin.ds;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.robin.ds.CircularLinkList;
import com.robin.ds.LinksBasedList;

/**
 * Test class for methods of CircularLinkList
 * 
 * @author robin
 * 
 */
public class TestCircularLinkList {

   @Test
   public void create() {
      CircularLinkList<Integer> integers = new CircularLinkList<>(new Integer[] { 1, 2, 3 });
      assertTrue(!integers.isEmpty());
      CircularLinkList<Number> numbers = new CircularLinkList<Number>(integers);
      assertEquals(3, numbers.size());
   }

   @Test
   public void getSet() {
      CircularLinkList<Integer> integers = new CircularLinkList<>(new Integer[] { 1, 2, 3 });
      assertEquals(Integer.valueOf(1), integers.get(0));
      assertEquals(Integer.valueOf(2), integers.get(1));
      assertEquals(Integer.valueOf(3), integers.get(2));
      assertEquals(Integer.valueOf(1), integers.set(0, -1));
      assertEquals(Integer.valueOf(-1), integers.get(0));

      integers = new CircularLinkList<>(new Integer[] { 1, 2, 3, 4, 5, 6 });
      assertEquals(Integer.valueOf(6), integers.get(5));
      assertEquals(Integer.valueOf(5), integers.get(4));
      assertEquals(Integer.valueOf(6), integers.set(5, -6));
      integers.set(4, -5);
      assertEquals(Integer.valueOf(-6), integers.get(5));
      assertEquals(Integer.valueOf(-5), integers.get(4));
      integers.set(2, 2);
      assertEquals(Integer.valueOf(2), integers.get(2));

   }

   @Test(expected = UnsupportedOperationException.class)
   public void setInvalidCase1() {
      CircularLinkList<Integer> integers = new CircularLinkList<>();
      assertTrue(integers.isEmpty());
      integers.set(0, 1);
   }

   @Test(expected = UnsupportedOperationException.class)
   public void setInvalidCase2() {
      CircularLinkList<Integer> integers = new CircularLinkList<>();
      integers.set(-11, 1);
   }

   @Test(expected = UnsupportedOperationException.class)
   public void setInvalidCase3() {
      CircularLinkList<Integer> integers = new CircularLinkList<>(new Integer[] { 1, 2, 3 });
      assertEquals(Integer.valueOf(3), Integer.valueOf(integers.size()));
      integers.set(3, 3);
   }

   @Test(expected = UnsupportedOperationException.class)
   public void addInvalidCase1() {
      CircularLinkList<Integer> integers = new CircularLinkList<>(new Integer[] { 1, 2 });
      integers.add(3, 1);
   }

   @Test
   public void addAtEnd() {
      CircularLinkList<Integer> integers = new CircularLinkList<>();
      integers.add(0, 1);// add in end
   }

   @Test
   public void addRemove() {
      LinksBasedList<Integer> integers = new LinksBasedList<>(new Integer[] { 1, 2, 3, 4, 5, 6 });
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
      integers.remove(1);
      assertEquals(Integer.valueOf(1), integers.get(0));
      assertEquals(Integer.valueOf(3), integers.get(1));
      integers.remove(3);// removing 5
      assertEquals(Integer.valueOf(6), integers.get(3));
      assertEquals(Integer.valueOf(4), integers.get(2));
      // Values in list should be 1,3,4,6
      integers.add(1, 2);
      assertEquals(Integer.valueOf(1), integers.get(0));
      assertEquals(Integer.valueOf(3), integers.get(2));
      integers.add(4, 5);
      assertEquals(Integer.valueOf(4), integers.get(3));
      assertEquals(Integer.valueOf(6), integers.get(5));
      // add at end
      integers.add(6, 7);

      // insert in location 1
      integers.add(2, -2);
   }

   @Test
   public void addRemoveSingleElement() {
      CircularLinkList<String> integers = new CircularLinkList<>();
      integers.add(0, "0");
      integers.remove(0);
      assertTrue(integers.isEmpty());
   }

}
