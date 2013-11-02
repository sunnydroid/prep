package com.sunny.linkedList;

import com.sunny.common.Logger;

/* circular binder that holds address cards in alphabetical order */

public class Rolodex {
	
	private Card head;
	
	public void Rolodex() {
		
	}
	
	public static void main(String[] args) {
		Rolodex rolodex = new Rolodex();
		
		rolodex.insert("Jane", "Smith");
		rolodex.insert("Sunny", "Shah");
		rolodex.insert("Dan", "Brown");
		rolodex.insert("John", "Doe");
		rolodex.insert("Zoe", "Joe");
		
		rolodex.print();
	}
	
	public void insert(String name, String last) {
		
		Card card = new Card(name, last);
		
		if(card == null) {
			return;
		}
		
		if(head == null) {
			head = card;
			head.next = head;
			head.prev = head;
			
			return;
		}
		
		Card currentCard = head;
		while(currentCard.next != head && currentCard.isLessThan(card)) {
			currentCard = currentCard.next;
		}
		
		card.next = currentCard.next;
		card.prev = currentCard;
		
		currentCard.next.prev = card;
		currentCard.next = card;
	}
	
	public Card findCard(String name) {
		Card foundCard = null;
		
		return foundCard;
	}
	
	public void print() {
		Card currentCard = head;
		
		do {
			Logger.log(currentCard.toString());
			currentCard = currentCard.next;
		}
		while(currentCard != head);
		
	}
	
	private static class Card {
		private String name;
		private String last;
		private String phone;
		private int age;
		
		/* doubly linked list */
		Card next;
		Card prev;
		
		public Card(String name, String last) {
			this.name = name;
			this.last = last;
		}
		
		public int compareTo(Card target) {
			return this.name.compareTo(target.name);
		}
		
		public boolean isLessThan(Card card) {
			if(this.compareTo(card) < 0) {
				return true;
			}
			return false;
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("Name: " + this.name);
			sb.append(", Last Name : " + this.last);
			
			return sb.toString();
		}
	}
}
