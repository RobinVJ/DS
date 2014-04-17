package com.robin.ds;


/**
 * Class represents a Queue created using generics.
 * 
 * @author robin
 * 
 * @param <E>
 */
public class Queue<E> {

   private int size = 0;
   private Node front, rear;

   class Node {
      E data;
      Node next;
   }

   /**
    * Constructor (Default)
    */
   public Queue() {

   }

   /**
    * Inserts the specified element into this queue.
    * 
    * @param data
    *           - can be null too
    */
   public void add(E data) {
      if (size == 0) {
         // queue is empty
         rear = front = new Node();
         rear.data = data;
      } else {
         rear.next = new Node();
         rear.next.data = data;
         rear = rear.next;
      }
      size++;
   }

   /**
    * Retrieves and removes the head of this queue.
    * 
    * @return the head of this queue
    * @throws IllegalStateException
    *            if this queue is empty
    */
   public E remove() {
      if (size == 0) {
         throw new IllegalStateException("Cannot remove from an empty queue");
      }
      E data = front.data;
      if (size == 1) {
         // remove the only node in Queue
         rear = front = null;
      } else {
         Node newFront = front.next;
         front.next = null;
         front = newFront;
      }
      size--;
      return data;
   }

   /**
    * Retrieves, but does not remove, the head of this queue. This method throws
    * an exception if this queue is empty.
    * 
    * @return the head of this queue
    * @throws IllegalStateException
    *            if this queue is empty
    */
   public E element() {
      if (size == 0) {
         throw new IllegalStateException("Cannot perform operation for an empty queue");
      }
      return front.data;
   }

   /**
    * Method indicates if queue is empty
    * 
    * @return boolean value
    */
   public boolean empty() {
      return (size == 0);
   }
}
