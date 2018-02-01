package com.sunny.leetcode;

import com.sunny.common.Logger;


public class BinaryTreeMaxPathSum {

    /**
     * Given a binary tree, find the maximum path sum from root to leaf.
     *
     * For this problem, a path is defined as any sequence of nodes from root node to any leaf node in the tree.
     *
     * For example:
     * Given the below binary tree,
     *
     *      1
     *     / \
     *    2   3
     *   / \   \
     *  4   7   5
     *
     * Return 10.
     */

    /**
     * Approach: In order to find the path with the highest sum from a given node, we need to compare the
     * sums from the left and right nodes and float it back up to the root in a recursive DFS manner:
     *  Base case: If root node is empty, return currentSum
     *  Else:
     *      calculate left sum recursively with left node and currentSum + value of current node
     *      calculate right sum recursively with right node and currentSum + value of current node
     *
     *  return higher of left and right sum
     *
     * Algorithm:
     *  if currentNode is null:
     *      return current sum
     *
     *  left sum = recurse(left child node, current sum + current node's value)
     *  right sum = recurse(right child node, current sum + current node's value)
     *
     *  return max of (left sum, right sum)
     *
     * @param currentNode
     * @return
     */
    public static int maxPathSumFrom(TreeNode currentNode, int currentSum) {
        if (currentNode == null) {
            return currentSum;
        }

        int leftSum = maxPathSumFrom(currentNode.left, currentSum + currentNode.val);
        int rightSum = maxPathSumFrom(currentNode.right, currentSum + currentNode.val);

        return Math.max(leftSum, rightSum);
    }


    /**
     * Given a binary tree, find the maximum path sum.
     *
     * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along
     * the parent-child connections. The path must contain at least one node and does not need to go through the root.
     *
     * For example:
     * Given the below binary tree,
     *
     *      1
     *     / \
     *    2   3
     *   / \   \
     *  4   7   5
     *
     * Return 18 (7+2+1+3+5).
     */

    /** global variable that is updated at each sub tree level */
    static int maxTreeSum = Integer.MIN_VALUE;

    /**
     * Approach: Similar to the max sum path from a particular node, we float sums calculated for each subtree
     * back up. At each parent, we need to make a decisions about which values to float up. We need to calculate
     *  1) left, right subtree and find out max
     *  2) current sub tree max - i.e. maximum sum for that sub tree and compare it to a global max.
     *  Initially set the global max to INT.MIN
     *
     * Algorithm:
     *  set global max = INT.MIN
     *  if currentNode is null:
     *      return 0
     *  left sum = Max(0, recurse(left child node))
     *  right sum = Max(0, recurse(right child node))
     *  global max = Max(global max, left sum + right sum + current node value)
     *
     *  return Max(left sum, right sum) + current node value // float max child + current node value to parent
     *
     * @param currentNode
     * @return
     */
    public static int maxPathSum(TreeNode currentNode) {
        /** Reached end of tree, max sum till now is 0 */
        if (currentNode == null) {
            return 0;
        }
        /** Calculate max left child path and max right child path. If path sum is less than 0, no need to
         * float it up
         */
        int leftSum = Math.max(0, maxPathSum(currentNode.left));
        int rightSum = Math.max(0, maxPathSum(currentNode.right));

        /** check if the current sub tree sub is greater than global max */
        maxTreeSum = Math.max(maxTreeSum, leftSum + rightSum + currentNode.val);

        /** return max of current node value + max child sum */
        return currentNode.val + Math.max(leftSum, rightSum);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     *      1
     *     / \
     *    2   3
     *   / \   \
     * -4  7    4
     * @param args
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode leftChild = new TreeNode(2);
        TreeNode rightChild = new TreeNode(3);

        root.left = leftChild;
        root.right = rightChild;

        leftChild.left = new TreeNode(-4);
        leftChild.right = new TreeNode(7);

        rightChild.right = new TreeNode(4);

        Logger.log("Max path sum from root = " + maxPathSumFrom(root, 0));
        Logger.log("Max path sum is = " + maxPathSum(root));
    }
}
