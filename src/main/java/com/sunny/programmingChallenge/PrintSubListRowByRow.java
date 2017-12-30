package com.sunny.programmingChallenge;

import com.sunny.common.Logger;

/**
 * Given a data structure with the following layout, print each of the elements in the sublist, line by line
 *
 * A -> B -> C -> D -> E -> F -> G -> NULL
 * |	|	 |	   |   |         |
 * 8	12	 17   19   24	     27
 * |	|	 |    |    |		 |
 * 9	13   18   20   25		 28
 * |	|	 |     |   |		 |
 * 10	14	NULL   21  NULL		 29
 * |	|		   |			 |
 * 11	15		   22			 30
 * |	|		   |             |
 * NULL 16		   23            NULL
 *      |          |
 *      NULL       NULL
 *
 * output: 8, 12, 17, 19, 24, 27, 9, 13, 18, 20, 25, 28.....16, 23
 */
public class PrintSubListRowByRow {

    /**
     * Approach:
     *  This problem can be simplified using an array of sub nodes which holds the current sub node to be printed.
     *  As soon as the sub node value is printed, the value at the current array index can be updated to be the sub
     *  node point to by the current sub node. If the value of sub node at current index is null, move to the next
     *  index. Repeat this process until all values in the array are next.
     *
     *  The initial size of the array to be allocated can be calculated by traversing from A->G
     *
     * Algorithm:
     *  array size to allocate -> 0
     *  current node -> root node
     *  while current node not null:
     *      array size++
     *      current node = current node .next
     *
     *  array of sub nodes = new array[array size]
     *  current node -> root node
     *  index -> 0
     *  number of null sub nodes = 0
     *  while current node node null:
     *      sub nodes[index] -> current node.sub node
     *      if current node.sub node is null
     *          number of null sub nodes++
     *      index++
     *      current node = current node.next
     *
     *  while number of null sub nodes < array size:
     *      index = index % array size
     *      current sub node = sub nodes[index]
     *      if current sub node equals null:
     *          continue
     *      print value of current sub node
     *      if current sub node.next equals null:
     *          number of null sub nodes ++
     *      sub nodes[index] -> current sub node.next
     *
     *  next
     * @param root
     */
    public static void printRowByRow(Node root) {

        int listWidth = 0;
        Node currentNode = root;
        /** find width of the top level linked list so we know what size array to allocate */
        while (currentNode != null) {
            listWidth++;
            currentNode = currentNode.next;
        }

        /** create a new array of sub nodes which will hold the current sub node for each list node */
        SubNode[] subNodes = new SubNode[listWidth];

        int numberOfNullSubNodes = 0;
        int index = 0;
        currentNode = root;
        /** Populate array of sub nodes with the first sub node for each list node.
         * Keep track of index to put the sub node, also keep track of number of null sub nodes so we know when
         * to stop printing sub node values when all indices of the sub node array are null
         */
        while (currentNode != null) {
          subNodes[index] = currentNode.subNode;
          /** keep track of number of sub nodes that are null */
          if (currentNode.subNode == null) {
              numberOfNullSubNodes++;
          }
          index++;
          currentNode = currentNode.next;
        }

        /** reset index */
        index = 0;

        /** keep printing value of sub node till all elements in sub node array are null */
        while (numberOfNullSubNodes < listWidth) {
            /** index needs to loop around so we can print line by line, use the length of array and modulo operator
             * to get the current index
             * */
            index = index % listWidth;
            SubNode currentSubNode = subNodes[index];
            if (currentSubNode == null) {
                /** don't forget to increment the index else it will be an infinite loop */
                index++;
                continue;
            }
            /** if sub node is not null, print the value and replace value at array index with the next sub node */
            Logger.log(currentSubNode.value);
            subNodes[index] = currentSubNode.next;
            /** if the next sub node is null, update the count of number of null sub nodes */
            if (currentSubNode.next == null) {
                numberOfNullSubNodes++;
            }
            index++;
        }
    }

    public static void main(String[] args) {
        int[] subNodeA = {8, 9, 10, 11};
        int[] subNodeB = {12, 13, 14, 15, 16};
        int[] subNodeC = {17, 18};
        int[] subNodeD = {19, 20, 21, 22, 23};
        int[] subNodeE = {24, 25};
        int[] subNodeF = {};
        int[] subNodeG = {26, 27, 28, 29, 30};

        Node nodeA = new Node();
        Node nodeB = new Node();
        Node nodeC = new Node();
        Node nodeD = new Node();
        Node nodeE = new Node();
        Node nodeF = new Node();
        Node nodeG = new Node();

        nodeA.next = nodeB;
        nodeA.subNode = buildSubNodes(subNodeA);
        nodeB.next = nodeC;
        nodeB.subNode = buildSubNodes(subNodeB);
        nodeC.next = nodeD;
        nodeC.subNode = buildSubNodes(subNodeC);
        nodeD.next = nodeE;
        nodeD.subNode = buildSubNodes(subNodeD);
        nodeE.next = nodeF;
        nodeE.subNode = buildSubNodes(subNodeE);
        nodeF.next = nodeG;
        nodeF.subNode = buildSubNodes(subNodeF);
        nodeG.next = null;
        nodeG.subNode = buildSubNodes(subNodeG);

        printRowByRow(nodeA);

    }

    public static SubNode buildSubNodes(int[] subNodes) {
        if (subNodes.length < 1) {
            return null;
        }
        /** keep track of root to return */
        SubNode root = new SubNode();
        SubNode currentNode = root;
        SubNode nextNode = null;

        currentNode.value = subNodes[0];

        for (int i = 1; i < subNodes.length; i++) {
            nextNode = new SubNode();
            nextNode.value = subNodes[i];
            nextNode.next = null;
            currentNode.next = nextNode;
            currentNode = nextNode;
        }

        return root;
    }

    public static class Node {
        Node next;
        SubNode subNode;
    }

    public static class SubNode {
        SubNode next;
        int value;
    }
}
