package com.sunny.queue;

/**
 * Created by sunshah on 1/23/16.
 */
public interface IQueue<E>{

    /**
     * Returns true if object is successfully added to the queue
     * @param type
     * @return
     */
    boolean enqueue(E type);

    E dequeue();

    boolean isEmpty();

    int size();

    int length();
}
