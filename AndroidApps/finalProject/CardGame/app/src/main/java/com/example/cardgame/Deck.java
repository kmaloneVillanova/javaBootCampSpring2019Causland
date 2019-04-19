package com.example.cardgame;

import com.example.cardgame.Card;

import java.util.ArrayList;

public class Deck {
	final char[] suits = {'h', 'd', 's', 'c'};
	ArrayList<Card> deck;
	
	
	Deck(int option){
		deck = new ArrayList<Card>();

		switch(option) {
		case 1: //Default Deck
			standardDeck();
			break;
		case 2:
			doubleDeck(); // Double Size Deck
			break;
		default:
			standardDeck();
			break;
		}
		
		shuffle();
	}
	
	public void standardDeck() {
		for(char suit : suits) {
			for(int i=2;i<15;i++) {
				Card temp = new Card(suit, i);
				deck.add(temp);
			}
		}
	}
	
	public void doubleDeck() {
		for(int j=0;j<2;j++) {
			for(char suit : suits) {
				for(int i=2;i<15;i++) {
					Card temp = new Card(suit, i);
					deck.add(temp);
				}
			}
		}
	}
	
	public ArrayList<Card> getDeck(){
		return this.deck;
	}
	
	public Card dealCard() {
		if(!deck.isEmpty()) {
			Card temp = deck.get(0);
			deck.remove(0);
			return temp;
		}
		else {
			return null;
		}
	}
	
	public void shuffle() {
		for(int i=0;i<deck.size();i++) {
			int rand = (int) (Math.random()*deck.size());
			Card temp = deck.get(rand);
			deck.set(rand, deck.get(i));
			deck.set(i, temp);
		}
		
	}
}
