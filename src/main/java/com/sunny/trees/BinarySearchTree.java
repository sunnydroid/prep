package com.sunny.trees;

import com.sunny.common.Logger;
import com.sunny.common.Queue;

public class BinarySearchTree {

	private Node root;
	
	public BinarySearchTree() {
		
	}
	
	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();
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

        Logger.log("Size of tree: " + sizeIterative(bst.getRoot()));
		
		Node[] paths = new Node[10];
		printPaths(bst.getRoot(), paths, 0);
		
		bst.setTree(delete(bst.getRoot(), 5));
		Logger.log("Paths after deletion: ");
		printPaths(bst.getRoot(), paths, 0);
		
//		mirror(bst.getRoot());
//		Logger.log("Tree after mirror, i.e. reverse order: ");
//		printInOrderTree(bst.getRoot());
	}

    /**
     * returns count of number of nodes in tree
     * Algorithm:
     *  sum = 0;
     *  if(root node is null)
     *      return sum
 *      Queue = nodeQueue()
     *  add root node -> nodeQueue
     *  while (currentNode = nodeQueue.pop() is not null)
     *  {
     *      sum++;
     *      if(left node of current node not null add to queue)
     *      if(right node of current node not null add to queue)
     *  }
     * @param node
     * @return
     */
    public static int sizeIterative(Node node) {
        int sum = 0;
        /* base case when BST is empty */
        if(node == null) {
            return sum;
        }
        /* use a data structure to queue up nodes */
        Queue<Node> queue = new Queue<>();
        queue.enqueue(node);
        Node currentNode;
        while((currentNode = queue.dequeue()) != null) {
           sum++;
            if(currentNode.getLeft() != null) {
                queue.enqueue(currentNode.getLeft());
            }
            if(currentNode.getRight() != null) {
                queue.enqueue(currentNode.getRight());
            }
        }
        return sum;
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
		root = insertRecursive(root, value);
//		root = insertIterative(root, value);
	}
	
	private Node insertRecursive(Node node, int value) {
        /* if input node is null, return new node
        this handles the base case;
         */
        if(node == null) {
            return new Node(value);
        }
       /* recursively set left/right node */
        if(node.getData() > value) {
            /* create node on left branch */
            node.setLeft(insertRecursive(node.getLeft(), value));
        } else {
            node.setRight(insertRecursive(node.getRight(), value));
        }

        return node;
	}
	
	private Node insertIterative(Node tree, int value) {
        Node currentNode = tree;
        Node newNode = new Node(value);
        // check for base case: empty tree
        if(currentNode == null) {
            return newNode;
        }

        // iterate until broken
        while(currentNode != null) {
            if(value > currentNode.getData()) {
                /* go right and iterate if right node already has value */
                if(currentNode.getRight() == null) {
                    currentNode.setRight(newNode);
                    /* we can break out at this point */
                    break;
                } else {
                    currentNode = currentNode.getRight();
                }
            } else {
                if(currentNode.getLeft() == null) {
                    currentNode.setLeft(newNode);
                    /* break out once setting new node */
                    break;
                } else {
                    currentNode = currentNode.getLeft();
                }
            }
        }
        return tree;
    }

    /**
     * In order traversal of the BST prints from the smallest root node
     * increasing up
     * @param node
     */
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
