package com.sunny.threads;

import com.sunny.common.Logger;
import com.sunny.common.Stack;

public class IntegerStack extends Stack<Integer> {
	
	private int size;
	
	public IntegerStack(int size) {
		this.size = size;
	}
	
	@Override
	public void push(Integer integer) {
		if(getCount() > size) {
			Logger.log( Thread.currentThread().getName() + " : *** Race condition:  stack is full, push() should not have been called ***");
			return;
		} else {
			super.push(integer);
		}
	}
	
	@Override
	public Integer pop() {
		Integer popped = null;
		
		if(getCount() < 0) {
			Logger.log( Thread.currentThread().getName() + " : *** Race condition:  stack is empty, pop() should not have been called ***");
		} else {
			popped = super.pop();
		}
		
		return popped;
	}
	
	public boolean isFull() {
		if(getCount() == size) {
			return true;
		}
		return false;
	}
	
	public boolean isEmpty() {
		if(getCount() == 0) {
			return true;
		}
		
		return false;
	}
}
