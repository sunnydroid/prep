package com.sunny.threads;

import com.sunny.common.Logger;

public class Consumer implements Runnable {

	private IntegerStack stack;
	
	public Consumer(IntegerStack stack) {
		this.stack = stack;
	}
	
	public void run() {
		while(true) {
			consume();
		}
	}
	
	public void consume() {
		if(!stack.isEmpty()) {
			Integer popedInteger = stack.pop();
			Logger.log(Thread.currentThread().getName() + " : consumed " + popedInteger + ". Stack size : " + stack.getCount() + ". Yielding..");
			Thread.yield();
		} else {
			try {
				Logger.log(Thread.currentThread().getName() + ": stack currently empty, waiting on producer, sleeping...");
				Thread.sleep(1000);
			} catch (InterruptedException ie) {
				Logger.log("Caught Interrupted Exception", ie.getCause());
			}
		}
	}
}
