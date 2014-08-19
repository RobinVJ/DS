package com.robin.ds;

/**
 * Class implements a Binary SearchTree //ordered binary tree
 * 
 * @author robin
 * 
 */
//any class that extends Comparable interface
//with Comparable Type being E or super-class of E 
public class BST<E extends Comparable<? super E>> {

   /**
    * Private Inner class used to represent a Node in the tree
    * 
    * @author robin
    * 
    */
   private class Node {
      E data;
      Node left, right;

      public Node(E data) {
         this.data = data;
      }
   }

   private Node root; // The Root of the tree

   /**
    * Default constructor -creates a 0 sized tree
    */
   public BST() {
      root = null;
   }

   /**
    * Creates a tree with the passed value as root. Null values will result in
    * UnsupportedOperationException.
    * 
    * @param rootValue
    */
   public BST(E rootValue) {
      if (null == rootValue) {
         throw new UnsupportedOperationException("Null values cannot be inserted in Tree");
      }
      root = new Node(rootValue);
   }

   /**
    * Creates a tree from the supplied array of values. The value at 0th
    * location is root. Null values will result in UnsupportedOperationException
    * 
    * @param values
    */
   public BST(E[] values) {
      if (null == values || values.length == 0) {
         return;
      }
      for (E value : values) {
         if (null == value)
            throw new UnsupportedOperationException("Null values cannot be inserted in Tree");
      }
      root = new Node(values[0]);
      int arrayLen = values.length;
      for (int i = 1; i < arrayLen; i++) {
         insert(root, values[i]);
      }

   }

   /**
    * Method will insert a node in BST if it does not already exists.
    * 
    * @param value
    * @return true indicating unique value inserted, else false
    */
   public boolean insert(E value) {
      if (null == value) {
         throw new UnsupportedOperationException("Null values cannot be inserted in Tree");
      }
      if (null == root) {
         root = new Node(value);
         return true;
      } else {
         return insert(root, value);
      }
   }

   /**
    * Method is used to remove a value from tree if present
    * 
    * @param value
    * @return true if a value was found and removed.
    */
   public boolean remove(E value) {
      if (null == root || null == value) {
         return false;
      } else {
         return remove(root, value);
      }
   }

   /**
    * Will return the minimum value in BST
    * 
    * @return E
    */
   public E findMinima() {
      if (root == null) {
         return null;// no minima
      } else {
         return minima(root);
      }
   }

   /**
    * Method will return the value for left child node of node containing value.
    * If element not present than throw UnsupportedOperationException
    * 
    * @param value
    * @return Integer if such node exists else null
    */
   public E findLeftChild(E value) {
      return findNode(root, value, NODE_TYPE.LEFT_CHILD);
   }

   /**
    * Method will return the value for right child node of node containing
    * value. If element not present than throw UnsupportedOperationException
    * 
    * @param value
    * @return Integer if such node exists else null
    */
   public E findRightChild(E value) {
      return findNode(root, value, NODE_TYPE.RIGHT_CHILD);
   }

   /**
    * Method will return the value for parent node of node containing value. If
    * element not present than throw UnsupportedOperationException
    * 
    * @param value
    * @return Integer if such node exists else null
    */
   public E findParent(E value) {
      return findNode(root, value, NODE_TYPE.PARENT);
   }

   /**
    * Will return the maximum value in BST
    * 
    * @return E
    */
   public E findMaxima() {
      if (root == null) {
         return null;// no maxima
      } else {
         return maxima(root);
      }
   }

   /**
    * Method indicates if BST is empty
    * 
    * @return boolean
    */
   public boolean isEmpty() {
      return null == root;
   }

   /**
    * Method will empty the BST
    */
   public void makeEmpty() {
      root = null;
   }

   /**
    * Method is used to check if a value is present in BST.
    * 
    * @param value
    * @return boolean witn true indicating value present in BST
    */
   public boolean contains(E value) {
      if (null == root) {
         return false;
      } else {
         return contains(root, value);
      }
   }

   /**
    * Method will return the height of BST.
    * 
    * @return int
    */
   public int height() {
      if (null == root) {
         return 0;
      } else {
         return computeHt(root);
      }
   }

   /*
    * ----------------------PRIVATE METHODS-------------------------
    */

   private enum NODE_TYPE {
      LEFT_CHILD, RIGHT_CHILD, PARENT
   };

   /**
    * Method will compute the height of a tree recursively
    * 
    * @param root
    * @return int
    */
   private int computeHt(Node root) {
      if (root == null) {
         return 0;
      } else {
         int leftTreeHt = computeHt(root.left);
         int rightTreeHt = computeHt(root.right);
         return 1 + (leftTreeHt > rightTreeHt ? leftTreeHt : rightTreeHt);
      }
   }

   /**
    * Method will check if value present in BST with Node root as root of BST
    * 
    * @param root
    * @param value
    * @return boolean true if present, else false
    */
   private boolean contains(Node root, E value) {
      while (null != root) {
         int compare = root.data.compareTo(value);
         if (compare > 0) {
            root = root.left;
         } else if (compare < 0) {
            root = root.right;
         } else {
            return true;
         }

      }
      return false;
   }

   /**
    * Find the maximum value starting at a BST node
    * 
    * @param root
    * @return E
    */
   private E maxima(Node root) {
      while (null != root.right) {
         root = root.right;
      }
      return root.data;
   }

   /**
    * Find the maximum value starting at a BST node
    * 
    * @param root
    * @return E
    */
   private E minima(Node root) {
      while (null != root.left) {
         root = root.left;
      }
      return root.data;
   }

   /**
    * Method will remove value from a tree with node root as root.
    * 
    * @param root
    * @param value
    * @return true if value removed
    */
   private boolean remove(Node root, E value) {
      boolean remove = false;
      Node parent = root;
      while (root != null) {
         int compare = root.data.compareTo(value);
         if (compare > 0) {
            parent = root;
            root = root.left;
         } else if (compare < 0) {
            parent = root;
            root = root.right;
         } else {
            // node found - need to delete
            // Case 1: node is a leaf
            if (null == root.left && null == root.right) {
               parent.left = parent.left == root ? null : parent.left;
               parent.right = parent.right == root ? null : parent.right;
               remove = true;
               break;
            } else if (null != root.left && null != root.right) {
               // Case 2: node has both children
               // Step 1: Find the minimal value in right subtree - this is a
               // leaf
               E minimal = minima(root.right);
               // set this value to node targeted for deletion
               root.data = minimal;
               // remove the duplicate node in the right subtree
               remove(root.right, minimal);
               remove = true;
               break;
            } else {
               // One subtree is null
               Node child = root.left != null ? root.left : root.right;
               // correct subtree attached to parent
               parent.left = parent.left == root ? child : parent.left;
               parent.right = parent.right == root ? child : parent.right;
               remove = true;
               break;
            }
         }
      }
      return remove;
   }

   /**
    * Method will insert a value in BST with node root as root.
    * 
    * @param root
    * @param value
    * @return true if value inserted
    */
   private boolean insert(Node root, E value) {
      boolean insert = false;
      while (root != null) {
         int compare = root.data.compareTo(value);
         if (compare > 0) {
            // put in left subtree
            if (root.left != null) {
               root = root.left;
            } else {
               root.left = new Node(value);
               insert = true;
               break;
            }
         } else if (compare < 0) {
            // put in left subtree
            if (root.right != null) {
               root = root.right;
            } else {
               root.right = new Node(value);
               insert = true;
               break;
            }
         } else {
            // duplicate node, return
            break;// no insertion performed
         }
      }
      return insert;
   }

   /**
    * Method returns the value associated with the node of type nodeType for
    * node with passed value If the value is not found in BST than an exception
    * is thrown
    * 
    * @param root
    * @param value
    * @param nodeType
    * @return the value, if no node matching the relation than null.
    */
   private E findNode(Node root, E value, NODE_TYPE nodeType) {
      Node parent = root;
      while (null != root) {
         int compare = root.data.compareTo(value);
         if (0 == compare) {
            E target = null;
            switch (nodeType) {
            case PARENT:
               target = parent.data;
               break;
            case LEFT_CHILD:
               target = root.left != null ? root.left.data : null;
               break;
            case RIGHT_CHILD:
               target = root.right != null ? root.right.data : null;
               break;
            }
            return target;
         } else if (compare > 0) {
            parent = root;
            root = root.left;
         } else {
            parent = root;
            root = root.right;
         }
      }
      throw new UnsupportedOperationException(value + " not found in BST");
   }
}
