package com.sunny.threads;

public class DeadLock2 {
	
	public static void main(String[] args) {
		BankAccount ba1 = new BankAccount(10);
		BankAccount ba2 = new BankAccount(20);
		
		Thread t1 = new Thread(new Runnable() {
			
			public void run() {
				while(true) {
					ba1.transfer(ba2, 5);
				}
			}
		});
			
		}
	

	private static class BankAccount {
		int amount;
		
		public BankAccount(int amount) {
			this.amount = amount;
		}
		
		public void transfer(BankAccount to, int amount) {
			synchronized (this) {
				synchronized (to) {
					if(this.amount >= amount) {
						this.amount -= amount;
						to.amount += amount;
					}
				}
			}
		}
		
	}
}
