package com.sunny.threads;

public class TestProducerConsumerThreads {

	public static void main(String[] args) {
		IntegerStack stack = new IntegerStack(10);
		Runnable producer = new Producer(stack);
		Runnable consumer = new Consumer(stack);
		
		for(int i = 0; i < 10; i ++) {
			Thread consumerThread = new Thread(consumer);
			consumerThread.setName("Consumer " + i);
			consumerThread.start();
		}
		
		for(int i = 0; i < 2; i ++) {
			Thread producerThread = new Thread(producer);
			producerThread.setName("Producer " + i);
			producerThread.start();
		}
	}
}
