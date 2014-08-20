package test.robin.ds;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.robin.ds.BinaryTree;

public class TestBinaryTree {
	private static final String DISPLAY_SEAPARATOR = "  ";

	@Test
	public void testCreate() {
		BinaryTree<Integer> binaryTree = new BinaryTree<Integer>();
		assertTrue(binaryTree.isEmpty());
		assertEquals(0, binaryTree.size());

		binaryTree = new BinaryTree<Integer>(new Integer[] {});
		assertTrue(binaryTree.isEmpty());
		assertEquals(0, binaryTree.size());

		Integer[] val = null;
		binaryTree = new BinaryTree<Integer>(val);
		assertTrue(binaryTree.isEmpty());
		assertEquals(0, binaryTree.size());
	}

	@Test
	public void testMirror() {
		BinaryTree<Integer> binaryTree = new BinaryTree<Integer>(new Integer[] { 10, 18, 21, 23, 43, 212, 16, 55, 17 });
		BinaryTree<Integer> mirror = binaryTree.getMirror();
		assertFalse(mirror.isEmpty());
		assertEquals(9, mirror.size());
		assertEquals(createDisplay(10, 18, 23, 55, 212, 21, 43, 17, 16), binaryTree.getPreOrderTraversal());
		assertEquals(createDisplay(10, 21, 16, 43, 17, 18, 212, 23, 55), mirror.getPreOrderTraversal());
	}

	@Test
	public void testMinMax() {
		BinaryTree<BigDecimal> binaryTree = new BinaryTree<BigDecimal>();
		assertNull(binaryTree.getMaxElement());
		assertNull(binaryTree.getMinElement());
		binaryTree.insert(BigDecimal.TEN);
		binaryTree.insert(BigDecimal.ONE);
		assertEquals(BigDecimal.TEN, binaryTree.getMaxElement());
		assertEquals(BigDecimal.ONE, binaryTree.getMinElement());
	}

	@Test
	public void testContains() {
		BinaryTree<Integer> binaryTree = new BinaryTree<Integer>(new Integer[] { 10, 18, 21, 23, 43, 212, 16, 55, 17 });
		assertFalse(binaryTree.isEmpty());
		assertEquals(9, binaryTree.size());
		assertEquals(createDisplay(55, 23, 18, 212, 10, 17, 43, 21, 16), binaryTree.getInOrderTraversal());
		assertTrue(binaryTree.contains(16));
		assertFalse(binaryTree.contains(1));
		assertEquals(4, binaryTree.getTreeDepth());
		BinaryTree<Long> binaryTree2 = new BinaryTree<Long>();
		assertTrue(binaryTree2.getTreeDepth() == 0);
	}

	@Test
	public void testTreePaths() {
		BinaryTree<Integer> binaryTree = new BinaryTree<Integer>(new Integer[] { 10, 18, 21, 23, 43, 212, 16, 55, 17 });
		List<String> expectedPaths = new ArrayList<String>();
		expectedPaths.add(createDisplay(10, 18, 23, 55));
		expectedPaths.add(createDisplay(10, 18, 212));
		expectedPaths.add(createDisplay(10, 21, 43, 17));
		expectedPaths.add(createDisplay(10, 21, 16));
		List<String> computedPaths = binaryTree.getRootToLeafPaths();
		assertEquals(expectedPaths.size(), computedPaths.size());
		int matches = 0;
		for (String expectedPath : expectedPaths) {
			for (String foundPath : computedPaths) {
				if (foundPath.equals(expectedPath)) {
					matches++;
					break;
				}
			}
		}
		assertEquals(expectedPaths.size(), matches);
	}

	@Test
	public void insertAndInorder() {
		BinaryTree<Integer> binaryTree = new BinaryTree<Integer>();
		binaryTree.insert(10);
		assertFalse(binaryTree.isEmpty());
		assertEquals(1, binaryTree.size());
		assertEquals(createDisplay(10), binaryTree.getInOrderTraversal());
		binaryTree.insert(18);
		assertEquals(createDisplay(18, 10), binaryTree.getInOrderTraversal());
		binaryTree.insert(21);
		assertEquals(createDisplay(18, 10, 21), binaryTree.getInOrderTraversal());
		binaryTree.insert(23);
		assertEquals(createDisplay(23, 18, 10, 21), binaryTree.getInOrderTraversal());
		binaryTree.insert(43);
		assertEquals(createDisplay(23, 18, 10, 43, 21), binaryTree.getInOrderTraversal());
		binaryTree.insert(212);
		assertEquals(createDisplay(23, 18, 212, 10, 43, 21), binaryTree.getInOrderTraversal());
		binaryTree.insert(16);
		assertEquals(createDisplay(23, 18, 212, 10, 43, 21, 16), binaryTree.getInOrderTraversal());
		binaryTree.insert(55);
		assertEquals(createDisplay(55, 23, 18, 212, 10, 43, 21, 16), binaryTree.getInOrderTraversal());
		binaryTree.insert(17);
		assertEquals(createDisplay(55, 23, 18, 212, 10, 17, 43, 21, 16), binaryTree.getInOrderTraversal());
	}

	@Test
	public void testPreOrder() {
		BinaryTree<Integer> binaryTree = new BinaryTree<Integer>();
		binaryTree.insert(10);
		assertEquals(createDisplay(10), binaryTree.getPreOrderTraversal());
		binaryTree.insert(18);
		assertEquals(createDisplay(10, 18), binaryTree.getPreOrderTraversal());
		binaryTree.insert(21);
		assertEquals(createDisplay(10, 18, 21), binaryTree.getPreOrderTraversal());
		binaryTree.insert(23);
		assertEquals(createDisplay(10, 18, 23, 21), binaryTree.getPreOrderTraversal());
		binaryTree.insert(43);
		assertEquals(createDisplay(10, 18, 23, 21, 43), binaryTree.getPreOrderTraversal());
		binaryTree.insert(212);
		assertEquals(createDisplay(10, 18, 23, 212, 21, 43), binaryTree.getPreOrderTraversal());
		binaryTree.insert(16);
		assertEquals(createDisplay(10, 18, 23, 212, 21, 43, 16), binaryTree.getPreOrderTraversal());
		binaryTree.insert(55);
		assertEquals(createDisplay(10, 18, 23, 55, 212, 21, 43, 16), binaryTree.getPreOrderTraversal());
		binaryTree.insert(17);
		assertEquals(createDisplay(10, 18, 23, 55, 212, 21, 43, 17, 16), binaryTree.getPreOrderTraversal());
	}

	@Test
	public void testPostOrder() {
		BinaryTree<Integer> binaryTree = new BinaryTree<Integer>();
		binaryTree.insert(10);
		assertEquals(createDisplay(10), binaryTree.getPostOrderTraversal());
		binaryTree.insert(18);
		assertEquals(createDisplay(18, 10), binaryTree.getPostOrderTraversal());
		binaryTree.insert(21);
		assertEquals(createDisplay(18, 21, 10), binaryTree.getPostOrderTraversal());
		binaryTree.insert(23);
		assertEquals(createDisplay(23, 18, 21, 10), binaryTree.getPostOrderTraversal());
		binaryTree.insert(43);
		assertEquals(createDisplay(23, 18, 43, 21, 10), binaryTree.getPostOrderTraversal());
		binaryTree.insert(212);
		assertEquals(createDisplay(23, 212, 18, 43, 21, 10), binaryTree.getPostOrderTraversal());
		binaryTree.insert(16);
		assertEquals(createDisplay(23, 212, 18, 43, 16, 21, 10), binaryTree.getPostOrderTraversal());
		binaryTree.insert(55);
		assertEquals(createDisplay(55, 23, 212, 18, 43, 16, 21, 10), binaryTree.getPostOrderTraversal());
		binaryTree.insert(17);
		assertEquals(createDisplay(55, 23, 212, 18, 17, 43, 16, 21, 10), binaryTree.getPostOrderTraversal());
	}

	private static String createDisplay(Object... objects) {
		StringBuilder display = new StringBuilder();
		for (Object object : objects) {
			display.append(object).append(DISPLAY_SEAPARATOR);
		}
		return display.toString();
	}
}
