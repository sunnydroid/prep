package com.sunny.trees;

import com.sunny.common.Logger;

import java.util.*;

/**
 * Created by sunshah on 7/29/17.
 */
public class BTreeParlante {


    public static void main(String[] args) {
        testLookup();
    }

    /**
     * Inserts a new node in a BST format
     * @param root
     * @param value
     * @return newly added node
     */
    private static Node insert(Node root, int value) {
        /* base case, empty tree */
        if (root == null) {
            return new Node(value);
        }

        /* case 2: check if we are at a leaf node,
         * if so, create new node and update parent's pointer
         * otherwise recurse with new node
         */
        if (value <= root.value) {
            if (root.leftNode == null) {
                Node newNode = new Node(value);
                root.leftNode = newNode;
                return newNode;

            } else {
                return insert(root.leftNode, value);
            }
        } else {
            if (root.rightNode == null) {
                Node newNode = new Node(value);
                root.rightNode = newNode;
                return newNode;

            } else {
                return insert(root.rightNode, value);
            }
        }
    }

    /**
     * Returns first node containing the lookupValue, null if not found
     * @param node
     * @param lookupValue
     * @return
     */
    private static Node lookup(Node node, int lookupValue) {
        /* base base, empty tree */
        if (node == null) {
            return null;
        }

        if (node.value == lookupValue) {
            return node;
        }

        if (lookupValue < node.value) {
            /* recurse with left child node */
            return lookup(node.leftNode, lookupValue);
        } else {
            /* recurse with right child node */
            return lookup(node.rightNode, lookupValue);
        }
    }

    /**
     * Builds a basic BST with the following structure
     *       5
     *     /   \
     *   3      7
     *  / \    /  \
     * 1   4  6    9
     * @return
     */
    private static Node buildBasicTree() {
        Node root = insert(null, 5);
        int[] nodeValues = {7, 3, 4, 1, 6, 9};
        for(int value : nodeValues) {
            insert(root, value);
        }

        return root;
    }

    /**
     * Prints elements of the tree in BFS pattern
     * @param root
     */
    private static void printBFS(Node root) {
        if (root == null) {
            Logger.log("Empty tree");
        }

        /* Do not use an iterator of a list to access and modify the list as the currentIndex of
         * the iterator is updated with each add operation. The Queue interface is much better suited
         * for this.
         */
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        /* print current value and add children node to stack */
        while (!queue.isEmpty()) {
            Node currentNode = queue.remove();
            Logger.log(currentNode.value);
            if(currentNode.leftNode != null) {
                queue.add(currentNode.leftNode);
            }
            if (currentNode.rightNode != null) {
                queue.add(currentNode.rightNode);
            }
        }
    }

    /**
     * Test insert and print operations
     */
    private static void testInsert() {
        Node root = buildBasicTree();
        printBFS(root);
    }

    private static void testLookup() {
        Node root = buildBasicTree();
        int lookupValue = 9;
        Node searchNode = lookup(root, lookupValue);

        if(searchNode == null) {
            Logger.log("Lookup value: " + lookupValue + " not found in tree");
        } else {
            Logger.log("Lookup value: " + lookupValue + " found in tree");
        }
    }



    private static class Node {
        Node leftNode;
        Node rightNode;
        int value;

        public Node(int value) {
            this.value = value;
            leftNode = null;
            rightNode = null;
        }

        public void setLeftNode(Node leftNode) {
            this.leftNode = leftNode;
        }

        public void setRightNode(Node rightNode) {
            this.rightNode = rightNode;
        }
    }
}
