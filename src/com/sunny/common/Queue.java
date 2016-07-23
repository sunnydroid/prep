package com.sunny.common;

import java.util.List;

/**
 * Created by sunshah on 7/7/16.
 */
public class Queue<E> {

    NodeLink root;
    NodeLink tail;

    public Queue() {
        root = null;
        tail = null;
    }

    public boolean isEmpty() {
        return root == null ? true : false;
    }

    /**
     * Push element into the queue
     * @param element
     */
    public void enqueue (E element) {
        if(root == null) {
            /* linked list is empty, root and tail should point to the same NodeLink */
            root = new NodeLink(element);
            tail = root;
        } else {
            /* Queue new node and update tail pointer */
            NodeLink newLink = new NodeLink(element);
            newLink.setPrev(tail);
            tail.setNext(newLink);
            tail = newLink;
        }
    }

    /**
     * Pop element from the queue
     * @return
     */
    public E dequeue() {
        E element;
        if(root == null) {
            element = null;
        } else if(root.getNext() == null) {
            /* we are on the last/only element */
            element = root.getData();
            tail = root = null;
        } else {
            element = tail.getData();
            /* set the next node for the previous link to null */
            tail.getPrev().setNext(null);
            /* update tail to point to the previous link */
            tail = tail.getPrev();
        }

        return element;
    }

    public void enqueue(List<E> items) {
        if(items == null || items.size() < 1) {
            Logger.log("List must not be empty to enqueue items");
            return;
        }

        for(E element: items) {
            enqueue(element);
        }
    }

    /**
     * Private data structure to maintain linked list for queue
     */
    private class NodeLink {
        private E data;
        private NodeLink next;
        private NodeLink prev;

        public NodeLink(E node) {
            data = node;
            next = null;
            prev = null;
        }

        public E getData() {
            return data;
        }


        public NodeLink getNext() {
            return next;
        }

        public void setNext(NodeLink next) {
            this.next = next;
        }

        public NodeLink getPrev() {
            return prev;
        }

        public void setPrev(NodeLink prev) {
            this.prev = prev;
        }
    }
}
