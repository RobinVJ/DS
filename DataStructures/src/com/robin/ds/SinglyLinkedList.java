package com.robin.ds;

/***
 * Class is a Linked List based implementation
 * 
 * @author robin
 * 
 * @param <E>
 */
public class SinglyLinkedList<E> implements MyList<E> {

	private class Link {
		E data;
		Link next;

		@Override
		public String toString() {
			return "Link [ data : " + data + ", next : " + next + " ]";
		}
	}

	private Link header;
	private int size = 0; // no of elements in the list

	/**
	 * Default Constructor
	 */
	public SinglyLinkedList() {

	}

	/**
	 * Constructor
	 * 
	 * @param data
	 */
	public SinglyLinkedList(E[] data) {
		// public <T extends E> SinglyLinkedList(T[] data) {
		// we need not wildcard in case of array. We can directly pass sub types
		// of E here
		int dataLen = data.length;
		if (dataLen == 0) {
			return;
		}
		header = new Link();
		header.data = data[0];
		Link tail = header;
		for (int i = 1; i < dataLen; i++) {
			Link newElem = new Link();
			newElem.data = data[i];
			tail.next = newElem;
			tail = newElem;
		}
		size = dataLen;
	}

	/**
	 * Constructor
	 * 
	 * @param data
	 */
	public SinglyLinkedList(MyList<? extends E> data) {
		int dataLen = data.size();
		if (dataLen == 0) {
			return;
		}
		header = new Link();
		header.data = data.get(0);
		Link tail = header;
		for (int i = 1; i < dataLen; i++) {
			Link newElem = new Link();
			newElem.data = data.get(i);
			tail.next = newElem;
			tail = newElem;
		}
		size = dataLen;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public E get(int index) {
		checkValidAccess(index);
		Link link = getLinkAt(index);
		return link.data;
	}

	@Override
	public E set(int index, E newVal) {
		checkValidAccess(index);
		Link link = getLinkAt(index);
		E oldVal = link.data;
		link.data = newVal;
		return oldVal;
	}

	@Override
	public void add(int index, E newVal) {
		if (index > size) {
			throw new UnsupportedOperationException("Attempt to use an invalid Index " + index + " max value " + size);
		}
		Link newLink = new Link();
		newLink.data = newVal;
		if (index == 0) {
			// add at head
			newLink.next = header;
			header = newLink;
		} else {
			insertValue(index, newLink);
		}
		size++;
	}

	@Override
	public E remove(int index) {
		E val = null;
		checkValidAccess(index);
		if (index == 0) {
			val = header.data;
			header = header.next;
		} else {
			Link prevLink = getLinkAt(index - 1);
			Link crtLink = prevLink.next;
			prevLink.next = crtLink.next;
			val = crtLink.data;
		}
		size--;
		return val;
	}

	/*
	 * ----------------------PRIVATE METHODS-------------------------
	 */

	/**
	 * Check if the location is a valid one.
	 * 
	 * @param index
	 */
	private void checkValidAccess(int index) {
		if (index < 0 || index > size - 1) {
			throw new UnsupportedOperationException("Attempt to use an invalid Index " + index + " with size from 0 to "
					+ (size - 1));
		}
	}

	/**
	 * Method will insert a Link in the List at the specified location.
	 * 
	 * @param index
	 * @param newLink
	 */
	private void insertValue(int index, SinglyLinkedList<E>.Link newLink) {
		Link prevLink = getLinkAt(index - 1);
		Link crtLink = prevLink.next;
		prevLink.next = newLink;
		newLink.next = crtLink;

	}

	/**
	 * Method will return link at the specified location.
	 * 
	 * @param index
	 * @return {@link SinglyLinkedList}
	 */
	private SinglyLinkedList<E>.Link getLinkAt(int index) {
		Link link = header;
		for (int i = 0; i < index; i++) {
			link = link.next;
		}
		return link;
	}

}
