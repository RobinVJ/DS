package test.robin.ds;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.robin.ds.SinglyLinkedList;

public class TestSinglyLinkedList {
	
	@Test
	public void create() {
		SinglyLinkedList<Integer> integers = new SinglyLinkedList<>(new Integer[] { 1, 2, 3 });
		assertTrue(!integers.isEmpty());
		SinglyLinkedList<Number> numbers = new SinglyLinkedList<Number>(integers);
		assertEquals(3, numbers.size());
	}

	@Test
	public void getSet() {
		SinglyLinkedList<Integer> integers = new SinglyLinkedList<>(new Integer[] { 1, 2, 3 });
		assertEquals(Integer.valueOf(1), integers.get(0));
		assertEquals(Integer.valueOf(2), integers.get(1));
		assertEquals(Integer.valueOf(3), integers.get(2));
		integers.set(0, -1);
		assertEquals(Integer.valueOf(-1), integers.get(0));
		integers = new SinglyLinkedList<>(new Integer[] { 1, 2, 3, 4, 5, 6 });
		assertEquals(Integer.valueOf(6), integers.get(5));
		assertEquals(Integer.valueOf(5), integers.get(4));
		integers.set(5, -6);
		integers.set(4, -5);
		assertEquals(Integer.valueOf(-6), integers.get(5));
		assertEquals(Integer.valueOf(-5), integers.get(4));
		integers.set(2, 2);
		assertEquals(Integer.valueOf(2), integers.get(2));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void setInvalidCase1() {
		SinglyLinkedList<Integer> integers = new SinglyLinkedList<>();
		assertTrue(integers.isEmpty());
		integers.set(0, 1);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void setInvalidCase2() {
		SinglyLinkedList<Integer> integers = new SinglyLinkedList<>();
		integers.set(-11, 1);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void setInvalidCase3() {
		SinglyLinkedList<Integer> integers = new SinglyLinkedList<>(new Integer[] { 1, 2, 3 });
		assertEquals(Integer.valueOf(3), Integer.valueOf(integers.size()));
		integers.set(3, 3);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void addInvalidCase1() {
		SinglyLinkedList<Integer> integers = new SinglyLinkedList<>(new Integer[] { 1, 2 });
		integers.add(3, 1);
	}

	@Test
	public void addAtEnd() {
		SinglyLinkedList<Integer> integers = new SinglyLinkedList<>();
		integers.add(0, 1);// add in end
	}

	@Test
	public void addRemove() {
		SinglyLinkedList<Integer> integers = new SinglyLinkedList<>(new Integer[] { 1, 2, 3, 4, 5, 6 });
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
		SinglyLinkedList<String> integers = new SinglyLinkedList<>();
		integers.add(0, "0");
		integers.remove(0);
		assertTrue(integers.isEmpty());
	}
}