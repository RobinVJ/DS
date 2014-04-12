package com.robin.ds;

/**
 * Class represents a Stack (linked list based implementation).
 * 
 * @author robin
 * 
 * @param <E>
 */
public class Stack<E> {

   private int size = 0;
   private Integer maxSize;
   private Node top;

   class Node {
      E data;
      Node prev;
   }

   /**
    * Creates an empty Stack.
    * 
    * @param maxSize
    *           - if specified than will put a cap on the max size the stack can
    *           go to (must be greater than 0). If null ignored
    */
   public Stack(Integer maxSize) {
      if (maxSize <= 0) {
         throw new IllegalArgumentException("Invalid size parameter - must be greater than 0");
      }
      this.maxSize = maxSize;
   }

   /**
    * Creates an empty Stack with no specified upper capacity
    */
   public Stack() {
      this.maxSize = null;
   }

   /**
    * Returns true if tree is full.
    * 
    * @return boolean value
    */
   public boolean full() {
      return this.overflowEnforced();
   }

   /**
    * Pushes an item onto the top of this stack.
    * 
    * @param item
    *           the item to be pushed onto this stack.
    */
   public void push(E item) {
      if (overflowEnforced()) {
         throw new IllegalStateException("OverFlow Error !!");
      }
      Node node = new Node();
      node.data = item;
      node.prev = top;
      top = node;
      size++;
   }

   /**
    * Removes the object at the top of this stack and returns that object as the
    * value of this function.
    * 
    * @return The object at the top of this stack
    */
   public E pop() {
      if (0 == this.size) {
         throw new IllegalStateException("UnderFlow Error !!");
      }
      E data = top.data;
      top = top.prev;
      size--;
      return data;
   }

   /**
    * Looks at the object at the top of this stack without removing it from the
    * stack.
    * 
    * @return the object at the top of this stack (the last item of the
    *         <tt>Vector</tt> object).
    * @throws EmptyStackException
    *            if this stack is empty.
    */
   public synchronized E peek() {
      if (null != top) {
         return top.data;
      }
      return null;
   }

   /**
    * Tests if this stack is empty.
    * 
    * @return true if and only if this stack contains no items;
    */
   public boolean empty() {
      return this.size == 0;
   }

   /**
    * Returns the 0-based position from stack top where an object is on this
    * stack.
    * 
    */
   public int search(E target) {
      int index = 0;
      Node temp = top;
      while (null != temp) {
         if ((target == null && temp.data == null) || (target != null && target.equals(temp.data))) {
            return index;
         }
         index++;
         temp = temp.prev;
      }
      return -1;
   }

   // ------------PRIVATE METHODS-----------------------
   /**
    * method checks if the overfow condition is true
    * 
    * @return boolean
    */
   private boolean overflowEnforced() {
      return null != this.maxSize && this.size == this.maxSize;
   }

}
