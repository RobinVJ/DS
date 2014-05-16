package test.robin.ds;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.robin.ds.PriorityQueue;

public class TestPriorityQueue {

   @Test
   public void createPriorityQueue() {
      PriorityQueue<String, Integer> emptyQ = new PriorityQueue<>(true); // minPriorityQ
      assertTrue(emptyQ.isEmpty());
      assertEquals(0, emptyQ.size());
      assertEquals(null, emptyQ.viewPriorityElement());
      assertEquals(null, emptyQ.getPriorityElement());

      PriorityQueue<String, Integer> nonEmptyQ = new PriorityQueue<>(false);
      nonEmptyQ.add("Proc1", 1);
      nonEmptyQ.add("Proc2", 2);
      nonEmptyQ.add("Proc3", 3);
      nonEmptyQ.add("Proc-2", 2);
      nonEmptyQ.add("Proc-1", 1);
      assertFalse(nonEmptyQ.isEmpty());
      assertEquals(5, nonEmptyQ.size());
      assertEquals("Proc3", nonEmptyQ.viewPriorityElement());

   }

   @Test
   public void addRemoveElements() {
      PriorityQueue<String, Integer> maxPriQ = new PriorityQueue<>(false);
      maxPriQ.add("Proc 4", 4);
      
      maxPriQ.add("Proc 1", 1);
      maxPriQ.add("Proc 3", 3);
      maxPriQ.add("Proc 2", 2);
      maxPriQ.add("Proc 16", 16);
      maxPriQ.add("Proc 9", 9);
      maxPriQ.add("Proc 10", 10);
      maxPriQ.add("Proc 14", 14);
      maxPriQ.add("Proc 8", 8);
      maxPriQ.add("Proc 7", 7);

      assertEquals(10, maxPriQ.size());
      assertTrue(maxPriQ.containsElement("Proc 16"));
      assertEquals("Proc 16", maxPriQ.getPriorityElement());
      assertFalse(maxPriQ.containsElement("Proc 16"));

      assertEquals("Proc 14", maxPriQ.getPriorityElement());
      assertEquals("Proc 10", maxPriQ.getPriorityElement());
      assertEquals(7, maxPriQ.size());
      
      assertEquals("Proc 9", maxPriQ.viewPriorityElement());
      maxPriQ.improveKeyValue("Proc 8", 10);
      assertEquals("Proc 8", maxPriQ.viewPriorityElement());
      
      assertEquals("Proc 8", maxPriQ.getPriorityElement());
      assertEquals("Proc 9", maxPriQ.getPriorityElement());      
      
      maxPriQ.improveKeyValue("Proc 1", 10);
      maxPriQ.improveKeyValue("Proc 3", 9);
      maxPriQ.improveKeyValue("Proc 4", 5);
      assertEquals("Proc 1", maxPriQ.getPriorityElement());
      
      assertEquals("Proc 3", maxPriQ.getPriorityElement());
      assertEquals("Proc 7", maxPriQ.getPriorityElement());
      assertEquals("Proc 4", maxPriQ.getPriorityElement());
      assertEquals("Proc 2", maxPriQ.getPriorityElement());
      assertEquals(0, maxPriQ.size());
      assertEquals(null, maxPriQ.getPriorityElement());
   }

   @Test
   public void addViewElements() {
      PriorityQueue<String, Integer> maxPriQ = new PriorityQueue<>(false);
      assertEquals(0, maxPriQ.size());

      maxPriQ.add("Proc 7", 7);
      assertEquals("Proc 7", maxPriQ.viewPriorityElement());
      maxPriQ.add("Proc 4", 4);
      assertEquals("Proc 7", maxPriQ.viewPriorityElement());
      maxPriQ.add("Proc 8", 8);
      assertEquals("Proc 8", maxPriQ.viewPriorityElement());

      maxPriQ.getPriorityElement();
      assertEquals("Proc 7", maxPriQ.viewPriorityElement());

      maxPriQ.add("Proc 1", 1);
      assertEquals("Proc 7", maxPriQ.viewPriorityElement());
      maxPriQ.add("Proc 3", 3);
      assertEquals("Proc 7", maxPriQ.viewPriorityElement());

      maxPriQ.add("Proc 8", 8);
      maxPriQ.add("Proc 2", 2);
      assertEquals("Proc 8", maxPriQ.viewPriorityElement());
      maxPriQ.add("Proc 9", 9);
      assertEquals("Proc 9", maxPriQ.viewPriorityElement());

      maxPriQ.add("Proc 10", 10);
      assertEquals("Proc 10", maxPriQ.viewPriorityElement());
      maxPriQ.add("Proc 14", 14);
      assertEquals("Proc 14", maxPriQ.viewPriorityElement());
      maxPriQ.add("Proc 16", 16);
      assertEquals("Proc 16", maxPriQ.viewPriorityElement());
   }

}
