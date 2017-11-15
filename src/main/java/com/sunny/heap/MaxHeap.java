package com.sunny.heap;

import java.util.ArrayList;
import java.util.List;


/**
 * Heap is a data structure that maintains a particular order (max/min) of elements
 * Insertion and removal of elements are followed by the heapify up/down operations
 */
public class MaxHeap {

    /**
     * Binary tree represented using an array allows for faster access
     * to elements and more compact representation.
     */
    List<Integer> heap;

    public MaxHeap(int size) {
        heap = new ArrayList<>(size);
    }

    /**
     * Swaps values of two indices. Used during the heapify operations
     * @param index first index to swap
     * @param swapIndex second index to swap
     */
    private void swap(int index, int swapIndex) {
        int temp = heap.get(index);
        /**
         * Do not use the add method while swapping values.
         * ArrayList.add() adds the element at the current index by shifting remaining elements to the right
         */
        heap.set(index, heap.get(swapIndex));
        heap.set(swapIndex, temp);
    }

    /**
     * Add a new element to the heap. Insertion has to ALWAYS follow the following rules:
     *  1) The last level on the tree must always have the left-most node filled. This rule
     *  stipulates the insertion order, from left to right as the spots fill up at current depth of the tree
     *
     *  2) All the levels of the tree must be completely filled before adding a new level. The
     *  last level is the only one which may be incomplete
     *
     * @param value value of element to be added
     */
    public void insert(int value) {
        heap.add(heap.size(), value);
        heapifyUp(heap.size() - 1);
    }

    /**
     * Removes highest order element, which is the first element since this is a max heap
     * @return  first element from the heap
     */
    public int remove() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("empty heap, cannot remove");
        }

        /**
         * remove first element to be returned
         */
        int maxValue = heap.get(0);
        /**
         * move last element from heap to first position followed by heapify down
         */
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        heapifyDown(0);

        return maxValue;
    }


    /**
     * For the heapifyDown operations, children node can be found based on the index of the current
     * node. The array contains the parent node followed by left then right node.
     *
     * The following formula is used to calculate the children nodes
     * children
     *  (index * 2) + 1 = left child
     *  (index * 2) + 2 = right child
     *
     * @param index index to heapify up from
     */
    public void heapifyDown(int index) {
        /**
         * find children, determine node with the highest element
         */
        int leftChildIndex = (index * 2) + 1;
        int rightChildIndex = (index * 2) + 2;
        int largerValueIndex;

        /**
         * if we reach leaf nodes, return else we run int index out of bounds exception
         * if left child index is not present, right child index will not be present but the
         * reverse is not true
         */
        if (leftChildIndex >= heap.size()) {
            return;
        }

        /**
         * if left child node is present but not right, determine if need to swap with left child else
         * find larger value of the two children
         */
        if (rightChildIndex >= heap.size()) {
            largerValueIndex = leftChildIndex;
        } else {
            largerValueIndex = heap.get(leftChildIndex) > heap.get(rightChildIndex) ? leftChildIndex : rightChildIndex;
        }

        /**
         * swap with larger child and recurse else return
         */

        if (heap.get(index) < heap.get(largerValueIndex)) {
            swap(index, largerValueIndex);
            heapifyDown(largerValueIndex);
        }
    }

    /**
     * For the heapifyUp operations, parent node can be found based on the index of the current
     * node. The array contains the parent node followed by left then right node.
     *
     * The following formula is used to calculate the parent node
     *
     * parent
     *  if index is odd, it is the left child
     *      (index - 1) / 2 = parent
     *  if index is even, it is the right child
     *      (index -2) / 2 = parent
     *
     * since integers round off to the floor,
     *  parent = (index - 1) / 2
     *
     * @param index index to heapify down from
     */
    public void heapifyUp(int index) {

        /**
         * reached the top of the tree, cannot heapify higher
         */
        if (index <= 0) {
            return;
        }

        /**
         * Determine parent of index and swap accordingly
         */
        int parentIndex = (index - 1)/2;
        if (heap.get(parentIndex) < heap.get(index)) {
            swap(parentIndex, index);
            heapifyUp(parentIndex);
        }
    }

    public void print() {
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        int level = 0;

        while (index < heap.size()) {
            int printCountOnCurrentLine = (int) Math.pow(2, level);
            StringBuilder stringBuilderCurrent = new StringBuilder();
            while (index < heap.size() && printCountOnCurrentLine > 0) {
                stringBuilderCurrent.append(heap.get(index) + "\t");
                index++;
                printCountOnCurrentLine--;
            }
            /**
             * line break for the next level
             */
            stringBuilderCurrent.append("\n");
            level++;
            stringBuilder.append(stringBuilderCurrent.toString());
        }

        System.out.println(stringBuilder.toString());
    }

    public void printRaw() {
        System.out.println(heap);
    }

    public int heapSize() {
        return heap.size();
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(5);
        maxHeap.insert(30);
        maxHeap.insert(35);
        maxHeap.insert(20);
        maxHeap.insert(40);
        maxHeap.insert(10);
        maxHeap.insert(15);
        maxHeap.insert(45);
//        maxHeap.printRaw();
//        System.out.println("heap size = " + maxHeap.heapSize());
//        System.out.println("Max value = " + maxHeap.remove());
        maxHeap.printRaw();
        maxHeap.print();
    }
}
