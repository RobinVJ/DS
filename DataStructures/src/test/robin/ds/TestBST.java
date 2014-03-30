package test.robin.ds;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.robin.ds.BST;

public class TestBST {

   @Test
   public void testCreation() {
      BST<Integer> bst = new BST<Integer>();
      assertEquals("Height of empty BST is 0", 0, bst.height());
      assertTrue(bst.isEmpty());

      bst = new BST<Integer>(10);
      assertEquals("Height of single node BST is 1", 1, bst.height());
      assertFalse(bst.isEmpty());

      Integer[] values = null;
      bst = new BST<Integer>(values);
      assertTrue(bst.isEmpty());
   }

   @Test(expected = UnsupportedOperationException.class)
   public void testFailCreationCase1() {
      Integer value = null;
      new BST<Integer>(value);
   }

   @Test(expected = UnsupportedOperationException.class)
   public void testFailCreationCase2() {
      Integer[] values = new Integer[] { 5, null, 0, 10 };
      new BST<Integer>(values);
   }

   @Test
   public void testCreationWithArray() {
      Integer[] values = new Integer[] { 5, -9, 0, 10 };
      BST<Integer> bst = new BST<>(values);
      assertFalse(bst.isEmpty());
      assertTrue(0 != bst.height());
   }

   @Test
   public void testMaximaMinima() {
      BST<Integer> bst = new BST<>(new Integer[] { 0, -9, 100, 21, 76, -81, 400 });
      assertEquals(Integer.valueOf("400"), bst.findMaxima());
      assertEquals(Integer.valueOf("-81"), bst.findMinima());
   }

   @Test
   public void testInsertion() {
      BST<Integer> bst = new BST<>();
      assertTrue(bst.insert(90));
      assertTrue(bst.insert(100));
      assertEquals(false, bst.insert(90));
      assertTrue(100 == bst.findMaxima());
   }

   @Test(expected = UnsupportedOperationException.class)
   public void testNullInsertion() {
      BST<Integer> bst = new BST<Integer>();
      bst.insert(null);
   }

   @Test
   public void testDeletion() {
      // BST bst = new BST();
      // assertTrue(bst.insert(90));
      // assertTrue(bst.insert(100));
      // assertTrue(100 == bst.findMaxima());
      // assertTrue(bst.remove(90));
      // // System.out.println(bst.remove(90));
      // // assertTrue(false==bst.remove(90));
      // assertTrue(bst.remove(100));
      // assertTrue(bst.isEmpty());
      // assertEquals(null, bst.findMaxima());
   }

   @Test
   public void testBST() {
      BST<Integer> bst = new BST<>();
      bst.insert(25);
      bst.insert(15);
      bst.insert(50);
      assertEquals(new Integer(15), bst.findLeftChild(25));
      bst.insert(10);
      bst.insert(22);
      bst.insert(35);
      bst.insert(70);
      assertEquals(new Integer(15), bst.findParent(10));
      bst.insert(4);
      bst.insert(12);
      bst.insert(18);
      bst.insert(24);
      bst.insert(31);
      bst.insert(44);
      bst.insert(66);
      bst.insert(90);
      assertEquals(new Integer(44), bst.findRightChild(35));
      assertTrue(bst.remove(90));// leaf removal
      assertEquals(null, bst.findRightChild(70));
      assertTrue(bst.remove(70));// node with one child removal
      assertEquals(Integer.valueOf(66), bst.findRightChild(50));
      assertTrue(bst.remove(15));// node with both children removal
      assertEquals(Integer.valueOf(18), bst.findLeftChild(25));
   }

}
