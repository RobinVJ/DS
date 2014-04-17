package test.robin.ds;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import com.robin.ds.Queue;

public class TestQueue {

   @Test
   public void creation() {
      Queue<BigDecimal> queue = new Queue<BigDecimal>();
      assertTrue(queue.empty());
   }

   @Test
   public void testAddRemove() {
      Queue<Integer> queue = new Queue<>();
      queue.add(10);
      assertFalse(queue.empty());
      assertEquals(Integer.valueOf(10), queue.element());
      queue.add(11);
      assertEquals(Integer.valueOf(10), queue.element());
      assertEquals(Integer.valueOf(10), queue.remove());
      assertEquals(Integer.valueOf(11), queue.remove());
   }

   @Test(expected = IllegalStateException.class)
   public void testRemove1() {
      Queue<Integer> queue = new Queue<>();
      queue.remove();
   }

   @Test(expected = IllegalStateException.class)
   public void testElement1() {
      Queue<Integer> queue = new Queue<>();
      queue.element();
   }

   @Test(expected = IllegalStateException.class)
   public void testRemove2() {
      Queue<Float> queue = new Queue<>();
      queue.add(Float.valueOf(87f));
      queue.remove();
      queue.add(11.98F);
      queue.remove();
      queue.remove();
   }

   @Test(expected = IllegalStateException.class)
   public void testElement2() {
      Queue<String> queue = new Queue<>();
      queue.add("sssf");
      assertEquals("sssf", queue.element());
      assertEquals("sssf", queue.remove());
      assertTrue(queue.empty());
      queue.remove();
   }

}
