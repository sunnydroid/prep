package com.sunny.trees;



public class RedBlackTrees {
	
	private enum Color {RED, BLACK}
	private Node root;
	
	public void RedBlackTree() {
		root = null;
	}
	
	public static void main(String[] args) {
		
	}
	
	public static Node insert(Node root, int value) {
		if(root == null) {
			Node newNode = new Node(value);
			newNode.color = Color.BLACK;
			return newNode;
		}
		/* recursive insert */
		if(value <= root.data) {
			root.left = insert(root.left, value);
		} else {
			root.right = insert(root.right, value);
		}
		/* balance the tree */
		if(isRed(root.left)) {
			if(isRed(root.right)) {
				
			}
		}
		
		return root;
	}

	public static boolean isRed(Node node) {
		if(node == null) {
			return false;
		}

		if(node.color == Color.RED) {
			return true;
		}
		
		return false;
	}
	
	private static class Node {
		int data;
		Node left;
		Node right;
		Color color;
		
		public Node(int value) {
			this.data = value;
			this.left = null;
			this.right = null;
			this.color = Color.RED;
		}
	}
}
