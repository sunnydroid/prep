package com.sunny.threads;

import java.util.ArrayList;
import java.util.Random;

import com.sunny.common.Logger;

public class ProducerConsumer {

	int MAX = 10;
	ArrayList<Integer> stack = new ArrayList<Integer>();
	Random random = new Random();

	public static void main(String[] args) {
		ProducerConsumer test = new ProducerConsumer();
		test.consume("1");
		test.produce("1");
		
		test.consume("2");
		test.produce("2");
	}

	public void produce(String name) {
		Thread producer = new Thread(new Runnable() {

			public void run() {

				while (true) {
					
					synchronized(stack) {
						if (stack.size() < MAX - 1) {
							Integer pushed = new Integer(getRandom());
							stack.add(pushed);
							Logger.log("pushed : " + pushed);
							Logger.log(Thread.currentThread().getName() + ": notifying consumer.");
							stack.notify();
						}

						try {
							Logger.log(Thread.currentThread().getName() + ": waiting...");
							stack.wait();
						} catch (InterruptedException ie) {
							Logger.log("Caught interrupted exception : "
									+ ie.getMessage());
						}
					}
				}
			}
		});
		
		producer.setName("Producer " + name);
		producer.start();
	}
	
	public void consume(String name) {
		Thread consumer = new Thread(new Runnable() {

			public void run() {

				while (true) {
					synchronized(stack) {
						if (stack.size() > 0) {
							Integer poped = stack.remove(stack.size() - 1);
							Logger.log("poped : " + poped);
							Logger.log(Thread.currentThread().getName() + ": notifying producer.");
							stack.notify();
						}
						
						try {
							Logger.log(Thread.currentThread().getName() + ": waiting...");
							stack.wait();
						} catch (InterruptedException ie) {
							Logger.log("Caught interrupted exception : "
									+ ie.getMessage());
						}
					}
				}
			}
		});
		consumer.setName("Consumer " + name);
		consumer.start();
	}

	private int getRandom() {
		return random.nextInt(100);
	}
	
	private void checkSize() {
		
	}
}
