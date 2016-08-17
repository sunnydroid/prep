package com.sunny.threads;

import java.util.Random;

import com.sunny.common.Logger;

public class Producer implements Runnable {

	private IntegerStack stack;
	private Random randomGenerator;

	public Producer(IntegerStack stack) {
		this.stack = stack;
		this.randomGenerator = new Random();
	}

	public void run() {
		while (true) {
			produce();
		}
	}

	private void produce() {
		if (!stack.isFull()) {
			Integer randomInteger = generateRandomNumber(20);
			stack.push(randomInteger);
			Logger.log(Thread.currentThread().getName() + " : Pushed "
					+ randomInteger + " into the stack. Stack size = "
					+ stack.getCount() + ". Yielding...");
			Thread.yield();
		} else {
			try {
				Logger.log(Thread.currentThread().getName()
						+ " : Stack full, waiting on Consumer, sleeping...");
				Thread.sleep(1000);
			} catch (InterruptedException ie) {
				Logger.log("Caught Interrupted Exception", ie.getCause());
			}
		}
	}

	private Integer generateRandomNumber(int max) {
		return new Integer(randomGenerator.nextInt(max));
	}
}
