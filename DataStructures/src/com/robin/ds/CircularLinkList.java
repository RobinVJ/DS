package com.robin.ds;

/**
 * Class is a simplified array based list. it grows dynamically as per needs.
 * 
 * @author robin
 * 
 * @param <E>
 */
public class CircularLinkList<E> implements MyList<E> {

   private class Link {
      E data;
      Link next, prev;

      @Override
      public String toString() {
         return "Link [ data : " + data + ", prev : " + prev + ", next : " + next + " ]";
      }
   }

   private Link header;

   private int size = 0; // no of elements in the list

   /**
    * Default constructor
    */
   public CircularLinkList() {

   }

   /**
    * Adds the values in the passed array to this {@link CircularLinkList}
    * 
    * @param initialValues
    */
   public CircularLinkList(E[] initialValues) {
      Link lastLink = null;
      for (E value : initialValues) {
         Link newLink = new Link();
         newLink.data = value;
         if (null == header) {
            header = newLink;
            newLink.prev = newLink.next = newLink;// everything points to header
            lastLink = header;
         } else {
            lastLink.next = newLink;
            newLink.prev = lastLink;
            newLink.next = header;// last node pointing to header
            header.prev = newLink;
            lastLink = newLink;
         }
      }
      size = initialValues.length;
   }

   /**
    * Copy Constructor
    * 
    * @param initialValues
    */
   public CircularLinkList(MyList<? extends E> initialValues) {
      int paramSize = initialValues.size();
      Link lastLink = null;
      for (int i = 0; i < paramSize; i++) {
         Link newLink = new Link();
         newLink.data = initialValues.get(i);
         if (null == header) {
            header = newLink;
            newLink.prev = newLink.next = newLink;// everything points to header
            lastLink = header;
         } else {
            lastLink.next = newLink;
            newLink.prev = lastLink;
            newLink.next = header;// last node pointing to header
         }
      }
      size = paramSize;

   }

   /**
    * Method will return number of elements in the array.
    * 
    * @return int value
    */
   public int size() {
      return size;
   }

   /**
    * Method indicates if array is empty
    * 
    * @return boolean value
    */
   public boolean isEmpty() {
      return 0 == size;
   }

   /**
    * Method is used to get value at a particular index
    * 
    * @param index
    * @return previous value
    */
   public E get(int index) {
      checkValidAccess(index);
      E data = null;

      Link tempLink = null;
      if (index > this.size() / 2) {
         // get value from reverse
         tempLink = header.prev;
         for (int i = this.size() - 1; i > index; i--) {
            tempLink = tempLink.prev;
         }
      } else {
         tempLink = header;
         for (int i = 0; i < index; i++) {
            tempLink = tempLink.next;
         }
      }
      // if (null != tempLink) {
      data = tempLink.data;
      // }
      return data;
   }

   /**
    * Method is used to set a value at a selected index
    * 
    * @param index
    * @param newVal
    * @return value
    */
   // can only set for values from start to end
   public E set(int index, E newVal) {
      checkValidAccess(index);
      E oldValue = null;

      Link tempLink = null;
      if (index > this.size() / 2) {
         // get value from reverse
         tempLink = header.prev;
         for (int i = this.size() - 1; i > index; i--) {
            tempLink = tempLink.prev;
         }
      } else {
         tempLink = header;
         for (int i = 0; i < index; i++) {
            tempLink = tempLink.next;
         }
      }
      // if (null != tempLink) {
      oldValue = tempLink.data;
      tempLink.data = newVal;
      // }
      return oldValue;
   }

   /**
    * Method is used to add a value at a particular index (add maximum upto
    * index = size)
    * 
    * @param index
    * @param newVal
    */
   public void add(int index, E newVal) {
      if (index > size) {
         throw new UnsupportedOperationException("Attempt to use an invalid Index " + index + " max value " + size);
      }
      if (index < size) {
         insertValue(index, newVal);
      } else {// insert at end or at locn 0
         Link newLink = new Link();
         newLink.data = newVal;
         if (null == header) {
            header = newLink;// first node
            newLink.prev = newLink.next = header;
         } else {
            newLink.prev = header.prev;
            newLink.next = header;
            header.prev.next = newLink;
            header.prev = newLink;

         }
      }
      size++;
   }

   /**
    * Method is used to remove an element at specified index
    * 
    * @param index
    * @return removed value
    */
   public E remove(int index) {
      checkValidAccess(index);
      E oldValue = null;
      if (index == 0) {
         oldValue = header.data;
         header = header.next;
      } else if (index == this.size() - 1) {
         Link secondLastLink = header.prev.prev;
         oldValue = header.prev.data;
         header.prev = secondLastLink;
         secondLastLink.next = header;
      } else {
         // non zero length - remove from middle
         Link linkToRemove = null;
         if (index > this.size() / 2) {
            // get value from reverse
            linkToRemove = header.prev;
            for (int i = this.size() - 1; i > index; i--) {
               linkToRemove = linkToRemove.prev;
            }
         } else {
            linkToRemove = header;
            for (int i = 0; i < index; i++) {
               linkToRemove = linkToRemove.next;
            }
         }

         linkToRemove.next.prev = linkToRemove.prev;
         linkToRemove.prev.next = linkToRemove.next;
         oldValue = linkToRemove.data;
      }
      size--;
      return oldValue;
   }

   /*
    * ----------------------PRIVATE METHODS-------------------------
    */

   /**
    * Method will check if the request is valid for a given index, if not it
    * will throw an exception.
    * 
    * @param index
    */
   private void checkValidAccess(int index) {
      if (index < 0 || index > size - 1) {
         throw new UnsupportedOperationException("Attempt to use an invalid Index " + index + " with size  from 0 to "
               + (size - 1));
      }

   }

   /**
    * Method will insert a new value inside the list
    * 
    * @param index
    * @param newVal
    */
   private void insertValue(int index, E newVal) {
      Link tempLink = null;
      if (index > this.size() / 2) {
         // get value from reverse
         tempLink = header.prev;
         for (int i = this.size() - 1; i > index - 1; i--) {
            tempLink = tempLink.prev;
         }
      } else {
         tempLink = header;
         for (int i = 0; i < index - 1; i++) {
            tempLink = tempLink.next;
         }
      }
      Link newlink = new Link();
      newlink.data = newVal;
      newlink.next = tempLink.next;
      tempLink.next.prev = newlink;
      newlink.prev = tempLink;
      tempLink.next = newlink;
   }

}
