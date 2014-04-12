package test.robin.ds;

import java.math.BigDecimal;
import static org.junit.Assert.*;

import org.junit.Test;

import com.robin.ds.Stack;

public class TestStack {

   @Test
   public void creation() {
      new Stack<String>();
      Stack<BigDecimal> stack = new Stack<BigDecimal>(10);
      assertTrue(stack.empty());
   }

   @Test(expected = IllegalArgumentException.class)
   public void creationWithInvalidSize() {
      new Stack<String>(-1);
   }

   @Test
   public void testPushPopPeek() {
      Stack<Integer> stack = new Stack<>();
      assertEquals(null, stack.peek());
      stack.push(10);
      assertFalse(stack.empty());
      assertEquals(Integer.valueOf(10), stack.peek());
      stack.push(11);
      assertEquals(Integer.valueOf(11), stack.peek());
      assertEquals(Integer.valueOf(11), stack.pop());
      assertEquals(Integer.valueOf(10), stack.pop());
   }

   @Test(expected = IllegalStateException.class)
   public void testPush() {
      Stack<Integer> stack = new Stack<>(2);
      stack.push(10);
      assertFalse(stack.full());
      stack.push(11);
      assertTrue(stack.full());
      stack.push(12);
   }

   @Test(expected = IllegalStateException.class)
   public void testEmptyPop() {
      Stack<Integer> stack = new Stack<>(2);
      stack.pop();
   }

   @Test
   public void testSearch() {
      Stack<String> stack = new Stack<String>();
      stack.push("FTTT");
      assertEquals(0, stack.search("FTTT"));
      assertEquals(-1, stack.search("FT"));
      stack.push("JHUY");
      assertEquals(1, stack.search("FTTT"));
      stack.push(null);
      stack.push("JHUY");
      stack.push("JHUY");
      assertEquals(4, stack.search("FTTT"));
      stack.push("hhhh");
      assertEquals(3, stack.search(null));
   }
}
