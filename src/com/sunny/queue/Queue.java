package com.sunny.queue;

import java.util.NoSuchElementException;

import com.sunny.common.Logger;

public class Queue {
	Node head;
	Node tail;
	int size;
	int max;

	public Queue(int max) throws IllegalArgumentException {
		if (max < 1) {
			throw new IllegalArgumentException();
		}
		this.max = max;
		size = 0;
	}

	public static void main(String[] args) {
		Queue queue = new Queue(10);
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		queue.enqueue(5);
		queue.enqueue(6);
		queue.enqueue(7);
		queue.enqueue(9);
		queue.enqueue(10);
		queue.enqueue(11);
		queue.enqueue(12);
		queue.printQueue();
		Logger.log("Peeking : " + queue.peek());
		Logger.log("Dequeuing : " + queue.dequeue());
		Logger.log("Dequeuing : " + queue.dequeue());
		Logger.log("Dequeuing : " + queue.dequeue());
		Logger.log("Dequeuing : " + queue.dequeue());
		Logger.log("Dequeuing : " + queue.dequeue());
		Logger.log("Dequeuing : " + queue.dequeue());
		Logger.log("Dequeuing : " + queue.dequeue());
		Logger.log("Dequeuing : " + queue.dequeue());
		Logger.log("Dequeuing : " + queue.dequeue());
		Logger.log("Dequeuing : " + queue.dequeue());
		Logger.log("Dequeuing : " + queue.dequeue());

	}

	public void enqueue(int num) throws IllegalStateException {

		if (size < max) {

			Node newNode = new Node(num);
			/* first entry */
			if (head == null) {
				head = newNode;
				tail = newNode;
				size++;

				return;
			}

			tail.next = newNode;
			tail = newNode;
			size++;
		} else {
			throw new IllegalStateException();
		}

	}

	public int dequeue() throws NoSuchElementException {
		if (isEmpty() || head == null) {
			throw new NoSuchElementException();
		}

		Node temp = head;
		head = head.next;
		/* detach temp's next pointer so it can be GC */
		temp.next = null;
		size--;

		return temp.data;

	}

	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}

		return false;
	}

	public int size() {
		return size;
	}

	public int peek() throws NoSuchElementException {
		if (isEmpty() || head == null) {
			throw new NoSuchElementException();
		}

		return head.data;
	}

	public void printQueue() {
		Node currentNode = head;
		while (currentNode != null) {
			Logger.log(currentNode.data + " -> ");
			currentNode = currentNode.next;
		}
	}

	private static class Node {
		Node next;
		int data;

		public Node(int data) {
			this.data = data;
			next = null;
		}
	}
}
