package com.robin.ds;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a Binary Tree for Comparable objects. (The comparable interface was
 * needed to support getMax and getMin methods - they can be moved lower into
 * the hierarchy and the constraint can then be removed)
 * 
 * @author rjv130130
 *
 * @param <E>
 */
public class BinaryTree<E extends Comparable<E>> {

	private static final String DISPLAY_SEPARATOR = "  ";

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

		@Override
		public String toString() {
			return "[ data : " + this.data + ",Left : " + (left == null ? " -" : left.data) + ", Right : "
					+ (right == null ? " -" : right.data) + " ]";
		}
	}

	private Node root; // The Root of the tree
	private int size;

	/**
	 * Default constructor -creates a 0 sized tree
	 */
	public BinaryTree() {

	}

	/**
	 * Pre-fills the tree with data from the array
	 * 
	 * @param data
	 */
	public BinaryTree(E[] data) {
		if (null == data || data.length == 0) {
			return;
		}
		for (E item : data) {
			insert(item);
		}
	}

	/**
	 * Method will insert data in the tree. It attempts to grow the tree in a
	 * balanced manner, giving preference to left side. No value based comparison
	 * here.
	 * 
	 * @param data
	 */
	public void insert(E data) {
		if (data == null) {
			// still deciding on this
			throw new IllegalArgumentException("Null Data not allowed");
		}
		if (root == null) {
			root = new Node(data);
		} else {
			insertNode(root, data);
		}
		size++;
	}

	/**
	 * Returns the inorder traversal for the tree
	 * 
	 * @return {@link String}
	 */
	public String getInOrderTraversal() {
		StringBuilder traversalPath = new StringBuilder();
		Node rootNode = root;
		traverseInOrder(traversalPath, rootNode);
		return traversalPath.toString();
	}

	/**
	 * Returns the preorder traversal for the tree
	 * 
	 * @return {@link String}
	 */
	public String getPreOrderTraversal() {
		StringBuilder traversalPath = new StringBuilder();
		Node rootNode = root;
		traversePreOrder(traversalPath, rootNode);
		return traversalPath.toString();
	}

	/**
	 * Method gets the postOrder traversal for the tree
	 * 
	 * @return {@link String}
	 */
	public String getPostOrderTraversal() {
		StringBuilder traversalPath = new StringBuilder();
		Node rootNode = root;
		traversePostOrder(traversalPath, rootNode);
		return traversalPath.toString();
	}

	/**
	 * Method returns the maximum valued element in the tree.
	 * 
	 * @return E
	 */
	public E getMaxElement() {
		if (root == null) {
			return null;
		}
		return getMax(root);
	}

	/**
	 * Method returns the minimum valued element in the tree.
	 * 
	 * @return E
	 */
	public E getMinElement() {
		if (root == null) {
			return null;
		}
		return getMin(root);
	}

	/**
	 * Contains method checks if element is present in the tree.
	 * 
	 * @param data
	 * @return boolean value
	 */
	public boolean contains(E data) {
		return containsElem(root, data);
	}

	/**
	 * Gives the Height of the Tree. For empty tree its 0 and then 1 and so on
	 * 
	 * @return int value
	 */
	public int getTreeDepth() {
		return computeTreeDepth(root);
	}

	/**
	 * Method gives the list of all root to leaf path in tree.
	 * 
	 * @return {@link List} of {@link String}
	 */
	public List<String> getRootToLeafPaths() {
		List<String> paths = null;
		if (root != null) {
			paths = new ArrayList<String>();
			getAllRootToLeafPaths(root.data + DISPLAY_SEPARATOR, root, paths);
		}
		return paths;
	}

	public BinaryTree<E> getMirror() {
		BinaryTree<E> binaryTree = new BinaryTree<E>();
		if (root == null) {
			return binaryTree;
		}
		binaryTree.root = new Node(root.data);
		createMirror(binaryTree.root, root);
		binaryTree.size = size;
		return binaryTree;
	}

	/**
	 * Method gives the size of tree.
	 * 
	 * @return int
	 */
	public int size() {
		return size;
	}

	/**
	 * Method indicates if tree is empty
	 * 
	 * @return boolean value
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/*
	 * -------------------------------------PRIVATE
	 * METHODS------------------------------------------------------
	 */

	/**
	 * Return the maximum element
	 * 
	 * @param rootElem
	 * @return E
	 */
	private E getMax(BinaryTree<E>.Node rootElem) {
		if (rootElem == null) {
			return null;
		} else {
			E leftTreeMax = getMax(rootElem.left);
			E rightTreeMax = getMax(rootElem.right);
			E data = rootElem.data;
			if (leftTreeMax != null && data.compareTo(leftTreeMax) < 0) {
				data = leftTreeMax;
			}
			if (rightTreeMax != null && data.compareTo(rightTreeMax) < 0) {
				data = rightTreeMax;
			}
			return data;
		}
	}

	/**
	 * Return the minimum element
	 * 
	 * @param rootElem
	 * @return E
	 */
	private E getMin(BinaryTree<E>.Node rootElem) {
		if (rootElem == null) {
			return null;
		} else {
			E leftTreeMin = getMax(rootElem.left);
			E rightTreeMin = getMax(rootElem.right);
			E data = rootElem.data;
			if (leftTreeMin != null && data.compareTo(leftTreeMin) > 0) {
				data = leftTreeMin;
			}
			if (rightTreeMin != null && data.compareTo(rightTreeMin) > 0) {
				data = rightTreeMin;
			}
			return data;
		}
	}

	/**
	 * Execute inorder traversal
	 * 
	 * @param traversalPath
	 *           - holds all the paths
	 * @param rootNode
	 */
	private void traverseInOrder(StringBuilder traversalPath, Node rootNode) {

		if (rootNode != null) {
			// Go left
			traverseInOrder(traversalPath, rootNode.left);
			// Display
			traversalPath.append(rootNode.data).append(DISPLAY_SEPARATOR);
			// Go right
			traverseInOrder(traversalPath, rootNode.right);
		}
	}

	/**
	 * Execute preorder traversal
	 * 
	 * @param traversalPath
	 *           - holds all the paths
	 * @param rootNode
	 */
	private void traversePreOrder(StringBuilder traversalPath, Node rootNode) {

		if (rootNode != null) {
			// Display
			traversalPath.append(rootNode.data).append(DISPLAY_SEPARATOR);
			// Go left
			traversePreOrder(traversalPath, rootNode.left);
			// Go right
			traversePreOrder(traversalPath, rootNode.right);
		}
	}

	/**
	 * Execute postorder traversal
	 * 
	 * @param traversalPath
	 *           - holds all the paths
	 * @param rootNode
	 */
	private void traversePostOrder(StringBuilder traversalPath, Node rootNode) {

		if (rootNode != null) {
			// Go left
			traversePostOrder(traversalPath, rootNode.left);
			// Go right
			traversePostOrder(traversalPath, rootNode.right);
			// Display
			traversalPath.append(rootNode.data).append(DISPLAY_SEPARATOR);
		}
	}

	/**
	 * Method will insert a node in tree traversing recursively until correct
	 * location found.
	 * 
	 * @param rootElem
	 * @param data
	 */
	private void insertNode(BinaryTree<E>.Node rootElem, E data) {
		if (rootElem.left == null) {
			// insert
			rootElem.left = new Node(data);
		} else if (rootElem.right == null) {
			// insert
			rootElem.right = new Node(data);
		} else { // down the tree
			int leftTreeHt = computeTreeDepth(rootElem.left);
			int rightTreeHt = computeTreeDepth(rootElem.right);
			if (rightTreeHt < leftTreeHt) {
				insertNode(rootElem.right, data);
			} else if (rightTreeHt > leftTreeHt) {

				insertNode(rootElem.left, data);
			} else {
				// if equal then put in tree with lower nodes
				int leftTreeCount = countNodes(rootElem.left);
				int rightTreeCount = countNodes(rootElem.right);
				if (rightTreeCount < leftTreeCount) {
					insertNode(rootElem.right, data);
				} else {
					insertNode(rootElem.left, data);
				}
			}
		}

	}

	/**
	 * Method will count the nodes in a tree with specified root
	 * 
	 * @param rootElem
	 * @return int
	 */
	private int countNodes(BinaryTree<E>.Node rootElem) {
		if (rootElem == null) {
			return 0;
		} else {
			int leftTreeCount = countNodes(rootElem.left);
			int rightTreeCount = countNodes(rootElem.right);
			return leftTreeCount + rightTreeCount + 1;
		}
	}

	/**
	 * Compute the depth of tree traversing recursively
	 * 
	 * @param rootElem
	 * @return int
	 */
	private int computeTreeDepth(BinaryTree<E>.Node rootElem) {
		if (rootElem == null) {
			return 0;
		} else {
			int leftTreeDepth = computeTreeDepth(rootElem.left);
			int rightTreeDepth = computeTreeDepth(rootElem.right);
			return 1 + (leftTreeDepth > rightTreeDepth ? leftTreeDepth : rightTreeDepth);
		}
	}

	/**
	 * Get all the paths traversing recursively
	 * 
	 * @param path
	 * @param rootElem
	 * @param paths
	 */
	private void getAllRootToLeafPaths(String path, Node rootElem, List<String> paths) {
		if (rootElem.left == null && rootElem.right == null) {
			paths.add(path);
			return;
		} else {
			if (rootElem.left != null) {
				getAllRootToLeafPaths(path + rootElem.left.data + DISPLAY_SEPARATOR, rootElem.left, paths);
			}
			if (rootElem.right != null) {
				getAllRootToLeafPaths(path + rootElem.right.data + DISPLAY_SEPARATOR, rootElem.right, paths);
			}
		}
	}

	/**
	 * Method will recursively search the tree for the specified node.
	 * 
	 * @param rootElem
	 * @param data
	 * @return boolean
	 */
	private boolean containsElem(BinaryTree<E>.Node rootElem, E data) {
		if (rootElem == null) {
			return false;
		} else {
			boolean recordFound = data.equals(rootElem.data) || containsElem(rootElem.left, data)
					|| containsElem(rootElem.right, data);
			return recordFound;
		}
	}

	/**
	 * Method will create a Mirror for every level in tree
	 * 
	 * @param mirrorRootElem
	 * @param rootElem
	 */
	private void createMirror(BinaryTree<E>.Node mirrorRootElem, BinaryTree<E>.Node rootElem) {
		if (rootElem == null) {
			return;
		}
		if (rootElem.left != null) {
			mirrorRootElem.right = new Node(rootElem.left.data);
			createMirror(mirrorRootElem.right, rootElem.left);
		}
		if (rootElem.right != null) {
			mirrorRootElem.left = new Node(rootElem.right.data);
			createMirror(mirrorRootElem.left, rootElem.right);
		}

	}
}
