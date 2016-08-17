package com.sunny.queue;

import java.util.NoSuchElementException;

import com.sunny.common.Logger;

public class NodeQueue {
	Node head;
	Node tail;
	int size;
	int max;

	public NodeQueue(int max) throws IllegalArgumentException {
		if (max < 1) {
			throw new IllegalArgumentException();
		}
		this.max = max;
		size = 0;
	}

	public static void main(String[] args) {
		NodeQueue nodeQueue = new NodeQueue(10);
		nodeQueue.enqueue(1);
		nodeQueue.enqueue(2);
		nodeQueue.enqueue(3);
		nodeQueue.enqueue(4);
		nodeQueue.enqueue(5);
		nodeQueue.enqueue(6);
		nodeQueue.enqueue(7);
		nodeQueue.enqueue(9);
		nodeQueue.enqueue(10);
		nodeQueue.enqueue(11);
//		nodeQueue.enqueue(12);
		nodeQueue.printQueue();
		Logger.log("Peeking : " + nodeQueue.peek());
		Logger.log("Dequeuing : " + nodeQueue.dequeue());
		Logger.log("Dequeuing : " + nodeQueue.dequeue());
		Logger.log("Dequeuing : " + nodeQueue.dequeue());
		Logger.log("Dequeuing : " + nodeQueue.dequeue());
		Logger.log("Dequeuing : " + nodeQueue.dequeue());
		Logger.log("Dequeuing : " + nodeQueue.dequeue());
		Logger.log("Dequeuing : " + nodeQueue.dequeue());
		Logger.log("Dequeuing : " + nodeQueue.dequeue());
		Logger.log("Dequeuing : " + nodeQueue.dequeue());
		Logger.log("Dequeuing : " + nodeQueue.dequeue());
		Logger.log("Dequeuing : " + nodeQueue.dequeue());

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
