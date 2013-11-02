package com.sunny.trees;

import com.sunny.common.Logger;

public class BinaryTree {

	private Node root;
	
	public BinaryTree() {
		
	}
	
	public static void main(String[] args) {
		BinaryTree bst = new BinaryTree();
		bst.insert(5);
		bst.insert(3);
		bst.insert(4);
		bst.insert(7);
		bst.insert(9);
		bst.insert(2);
		bst.insert(1);
		
		Logger.log("In order tree print: ");
		printInOrderTree(bst.getRoot());
		Logger.log("Minimum: " + getMin(bst.getRoot()));
		
		Node[] paths = new Node[10];
		printPaths(bst.getRoot(), paths, 0);
		
		bst.setTree(delete(bst.getRoot(), 5));
		Logger.log("Paths after deletion: ");
		printPaths(bst.getRoot(), paths, 0);
		
//		mirror(bst.getRoot());
//		Logger.log("Tree after mirror, i.e. reverse order: ");
//		printInOrderTree(bst.getRoot());
	}
	
	public static Node delete(Node root, int value) {
		/*
		 * if node with value is leaf node -> update parent's pointer to null and GC will clear out node
		 * if node with value has either a left or right child, update the parent to point to the child and 
		 * clear out current node's left/right pointers
		 * if node with value has both children, find the in order successor and replace current node
		 */
		Node parent = null;
		Node current = root;
		Node successor = null;
		
		while(current != null) { 
			if(current.data == value) {
				break;
			}
			parent = current;
			current = value < current.data ? current.left : current.right;
		}
		
		if(current == null) {
			Logger.log("Value " + value + " not present in tree");
			return root;
		}
		
		if(current.left == null && current.right == null) {
			/* no children, update parent */
			if(value < parent.data) {
				parent.left = null;
			} else {
				parent.right = null;
			}
			Logger.log("Deleted value " + value);
			return root;
		}
		
		if(current.left != null && current.right != null) {
			/* both children present, find in order successor */
			Node successorParent = current.right;
			successor = successorParent;
			while(successor.left != null) {
				successorParent = successor;
				successor = successor.left;
			}
			/* if successor is the only child, remove reference to it from current node */
			if(successor == successorParent) {
				current.right = successorParent.right;
			} else {
				/* if successor has any right children, attach them to it's parent */
				successorParent.left = successor.right;
			}
			
			/* remove reference to successor so that it can be garbage collected and copy it's value to current node*/
			current.data = successor.data;
			successor.left = null;
			successor.right = null;
			
			Logger.log("Deleted value " + value);
			return root;
		}
		
		/* node to be removed has only one child; update parent to point to child and free references to children */
		if(value < parent.data) {
			parent.left = current.left == null ? current.right : current.left;
		} else {
			parent.right = current.left == null ? current.right : current.left;
		}
		Logger.log("Deleted value " + value);
		return root;
	}
	
	public static void mirror(Node node) {
		/* base case, return if node is null */
		if(node == null) {
			return;
		}
		/* flip pointers */
		Node temp = node.getRight();
		node.setRight(node.getLeft());
		node.setLeft(temp);
		/* recurse */
		mirror(node.getLeft());
		mirror(node.getRight());
	}
	
	public static void printPaths(Node node, Node[] path, int pathLength) {
		if(node == null) {
			/* empty tree case */
			return;
		}
		if(node.getLeft() == null && node.getRight() == null) {
			path[pathLength] = node;
			pathLength++;
			printNodes(path, pathLength);
			return;
		}
		path[pathLength] = node;
		pathLength++;
		printPaths(node.getLeft(), path, pathLength);
		printPaths(node.getRight(), path, pathLength);
		
	}
	
	public static void printNodes(Node[] nodes, int length) {
		Logger.log("Path:");
		for(int i = 0; i < length; i++) {
			if(nodes[i] == null) {
				break;
			}
			Logger.log(nodes[i].getData());
		}
	}
	
	public static Integer getMin(Node node) {
		/* if empty tree is passed in, throw runtime exception*/
		if(node == null) {
			return null;
		}
		Node currentNode = node;
		while(currentNode.getLeft() != null) {
			currentNode = currentNode.getLeft();
		}
		
		return currentNode.getData();
	}
	
	public void insert(int value) {
//		tree = insertRecursive(this.tree, value);
		root = insertIterative(root, value);
	}
	
	private Node insertRecursive(Node node, int value) {
		if(node == null) {
			node = new Node(value);
			return node;
		}
		
		if(value <= node.getData()) {
			node.setLeft(insertRecursive(node.getLeft(), value));
		} else {
			node.setRight(insertRecursive(node.getRight(), value));
		}
		
		return node;
	}
	
	private Node insertIterative(Node tree, int value) {
		Node newNode = new Node(value);
		
		/*empty tree case */
		if(tree == null) {
			return newNode;
		} 
		
		Node currentNode = tree;
		
		while(currentNode != null) {
			if(currentNode.getData() <= value) {
				/* go left */
				if(currentNode.getRight() == null) {
					currentNode.setRight(newNode);
					break;
				}
				currentNode = currentNode.getRight();
			} else {
				/* go right */
				if(currentNode.getLeft() == null) {
					currentNode.setLeft(newNode);
					break;
				}
				currentNode = currentNode.getLeft();
			}
		}
		
		return tree;
	}
	
	public static void printInOrderTree(Node node) {
		if(node == null) {
			return;
		} 
		printInOrderTree(node.getLeft());
		Logger.log(node.getData());
		printInOrderTree(node.getRight());
	}
	

	public Node getRoot() {
		return root;
	}

	public void setTree(Node tree) {
		this.root = tree;
	}
	
	/*
	 * Binary tree node
	 */
	private static class Node {
		
		private int data;
		private Node left;
		private Node right;
		
		public Node(int data) {
			this.data = data;
			left = null;
			right = null;
		}
		
		public Node(int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node leftChild) {
			this.left = leftChild;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node rightChild) {
			this.right = rightChild;
		}
	}
}
