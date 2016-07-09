package com.sunny.queue;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by sunshah on 1/23/16.
 */
public class GenericQueue<E> implements IQueue, Iterable {

    private final E[] list;
    /* current index of the queue */
    private int index;

    public GenericQueue(int size) throws IllegalArgumentException {
        if(size < 1) {
            throw new IllegalArgumentException("Queue size has to be greater than 1");
        }

        this.list = (E[]) new Object[size];
        index = -1;
    }

    @Override
    public boolean enqueue(Object type) {
        /* Check if queue is full */
        if (index == list.length - 1) {
            System.out.println("Can't add to full queue");
            return false;
        }

        index++;
        list[index] = (E) type;
        return true;
    }

    @Override
    public Object dequeue() {
        /* Check if queue is empty */
        if (index < 0) {
            System.out.println("Can't dequeue from an empty list");
            return null;
        }

        return list[index--];
    }

    @Override
    public boolean isEmpty() {
        boolean empty = index < 0 ? true : false;
        if(empty) {
            System.out.println("Queue is empty");
        } else {
            System.out.println("Queue is NOT empty");
        }
        return empty;
    }

    @Override
    public int size() {
        System.out.println("Queue size is " + list.length);
        return list.length;
    }

    @Override
    public int length() {
        System.out.println("Queue length is " + index);
        return index;
    }

    @Override
    public Iterator iterator() {
        return Arrays.asList(list).iterator();
    }
}
