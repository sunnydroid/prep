package com.sunny.programmingChallenge;

import java.util.Stack;

/**
 * Given a binary tree, print it in spiral order
 *           2    print ->
 *         /   \
 *        3     4   <- print
 *      /  \    /  \
 *     5    6   7    8  -> print
 *    / \  / \ / \  / \
 *   9  1 2  4 5 6 7  0  <- print
 *
 *
 *  output: 2, 4, 3, 5, 6, 7, 8, 0, 7, 6, 5, 4, 2, 1, 9
 *
 */
public class PrintSpiralBtree {

    /**
     * Approach: We can use a stack to add nodes for the next level in the reverse order and recurse with the stack.
     *  If the level is even (0, 2, 4...) add nodes for the next level from left to right, else add nodes from
     *  right to left.
     *  Alternatively, we can use two stacks if we need to reverse the order, of nodes added. Nodes added will need to
     *  be flipped for every alternate level i.e. for every odd level (1, 3, 5). Popping from one stack onto another
     *  will effectively reverse the ordering of a stack.
     *
     * Algorithm:
     *  if stack is empty:          // base case
     *      return
     *  stack <- new stack of nodes
     *  while current stack is not empty:
     *      current node <- pop stack
     *      print value
     *      if level is even:
     *          add left child then right to stack
     *      else:
     *          add right child then left to stack
     *
     *  recurse with stack and level + 1
     *
     * @param root
     */
    public static void printSpiral(Node root) {
        /** add root node to a stack and call helper */
        Stack<Node> stack = new Stack<>();
        stack.add(root);

        printSpiralHelper(stack, 0);
    }

    public static void printSpiralHelper(Stack<Node> currentStack, int level) {
        /** base case to exit recursion */
        if (currentStack == null || currentStack.isEmpty()) {
            return;
        }

        Stack<Node> newStack = new Stack<>();

        while (!currentStack.isEmpty()) {
            Node currentNode = currentStack.pop();
            if (currentNode == null) {
                continue;
            }
            System.out.print(currentNode.value + ", ");

            if (level % 2 == 0) {
                /** add left child then right */
                newStack.add(currentNode.left);
                newStack.add(currentNode.right);
            } else {
                newStack.add(currentNode.right);
                newStack.add(currentNode.left);
                /** add right child then left */
            }
        }

        /** recurse with new stack and level + 1 */
        printSpiralHelper(newStack, level+1);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        Node seven = new Node(7);
        Node eight = new Node(8);
        Node nine = new Node(9);
        Node ten = new Node(10);
        Node eleven = new Node(11);
        Node twelve = new Node(12);
        Node thirteen = new Node(13);
        Node fourteen = new Node(14);
        Node fifteen = new Node(15);

        root.left = two;
        root.right = three;

        two.left = four;
        two.right = five;

        three.left = six;
        three.right = seven;

        four.left = eight;
        four.right = nine;

        five.left = ten;
        five.right = eleven;

        six.left = twelve;
        six.right = thirteen;

        seven.left = fourteen;
        seven.right = fifteen;

        printSpiral(root);
    }

    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }
}
