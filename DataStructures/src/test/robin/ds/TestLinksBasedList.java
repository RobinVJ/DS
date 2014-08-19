package test.robin.ds;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.robin.ds.LinksBasedList;

public class TestLinksBasedList {

	@Test(expected = IllegalArgumentException.class)
	public void createExceptionV1() {
		Integer[] array = null;
		new LinksBasedList<>(array);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createExceptionV2() {
		LinksBasedList<Integer>[] list = null;
		new LinksBasedList<>(list);
	}

	@Test
	public void create() {
		assertEquals(0, new LinksBasedList<>(new Integer[] {}).size());
		assertEquals(0, new LinksBasedList<>(new LinksBasedList<>()).size());
		LinksBasedList<Integer> integers = new LinksBasedList<>(new Integer[] { 1, 2, 3 });
		assertTrue(!integers.isEmpty());
		LinksBasedList<Number> numbers = new LinksBasedList<Number>(integers);
		assertEquals(3, numbers.size());
	}

	@Test
	public void getSet() {
		LinksBasedList<Integer> integers = new LinksBasedList<>(new Integer[] { 1, 2, 3 });
		assertEquals(Integer.valueOf(1), integers.get(0));
		assertEquals(Integer.valueOf(2), integers.get(1));
		assertEquals(Integer.valueOf(3), integers.get(2));
		integers.set(0, -1);
		assertEquals(Integer.valueOf(-1), integers.get(0));

		integers = new LinksBasedList<>(new Integer[] { 1, 2, 3, 4, 5, 6 });
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
		LinksBasedList<Integer> integers = new LinksBasedList<>();
		assertTrue(integers.isEmpty());
		integers.set(0, 1);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void setInvalidCase2() {
		LinksBasedList<Integer> integers = new LinksBasedList<>();
		integers.set(-11, 1);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void setInvalidCase3() {
		LinksBasedList<Integer> integers = new LinksBasedList<>(new Integer[] { 1, 2, 3 });
		assertEquals(Integer.valueOf(3), Integer.valueOf(integers.size()));
		integers.set(3, 3);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void addInvalidCase1() {
		LinksBasedList<Integer> integers = new LinksBasedList<>(new Integer[] { 1, 2 });
		integers.add(3, 1);
	}

	@Test
	public void addAtEnd() {
		LinksBasedList<Integer> integers = new LinksBasedList<>();
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
		LinksBasedList<String> integers = new LinksBasedList<>();
		integers.add(0, "0");
		integers.remove(0);
		assertTrue(integers.isEmpty());
	}

}
