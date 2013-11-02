package com.sunny.common;

import java.util.ArrayList;
import java.util.List;

public abstract class Stack<E> {
	int top;
	private List<E> stack;

	public Stack() {
		stack = new ArrayList<E>();
		top = 0;
	}

	public void push(E object) {
		stack.add(object);
		top++;
	}

	public E pop() {
		top--;
		return stack.remove(top);
	}

	public E peek() {
		if (!stack.isEmpty()) {
			return stack.get(stack.size() - 1);
		}
		return null;
	}

	public int getCount() {
		return top;
	}
}
