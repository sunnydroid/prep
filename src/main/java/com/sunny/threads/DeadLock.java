package com.sunny.threads;

import java.util.Random;

import com.sunny.common.Logger;

public class DeadLock implements Runnable {
	
	private StringBuffer sharedBuffer = new StringBuffer();
	private StringBuffer sharedBuffer2 = new StringBuffer();
	private Random randomGenerator = new Random();
	
	public static void main(String[] args) {
		DeadLock r1 = new DeadLock();
		
		Thread t1 = new Thread(r1);
		t1.setName("t1");
		Thread t2 = new Thread(r1);
		t2.setName("t2");
		
		t1.start();
		t2.start();
	}
	
	public void run() {
		while (true) {
			writeBuffer();
			readBuffer();
		}
	}
	
	private void readBuffer() {
		synchronized (sharedBuffer) {
			synchronized (sharedBuffer2) {
				Logger.log("Thread: " + Thread.currentThread().getName() + " reading buffer");
				Logger.log(sharedBuffer.toString());
				Logger.log(sharedBuffer2.toString());
			}
		}
	}
	
	private void writeBuffer() {
		synchronized (sharedBuffer2) {
			synchronized (sharedBuffer) {
				Logger.log("Thread: " + Thread.currentThread().getName() + " writing to buffer");
				sharedBuffer2.append(randomGenerator.nextInt() + ", ");
				sharedBuffer.append(randomGenerator.nextInt() + ", ");
			}
		}
	}
}
