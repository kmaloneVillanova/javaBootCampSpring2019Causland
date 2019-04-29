package com.example.cardgame;

public class Card {
	char suit;
	int number;	


	Card(char s, int n){
		suit = s;
		number = n;
	}

	public int getNumber() {
		return number;
	}
	
	public String getStringNumber() {
		if(number == 11){
			return "JACK";
		}
		else if(number == 12) {
			return "QUEEN";
		}
		else if(number == 13) {
			return "KING";
		}
		else if(number == 14) {
			return "ACE";
		}
		else {
			return String.valueOf(number);
		}
	}
	
	public String getShortName() {
		if(number == 11){
			return (suit + "j");
		}
		else if(number == 12) {
			return (suit + "q");
		}
		else if(number == 13) {
			return (suit + "k");
		}
		else if(number == 14) {
			return (suit + "a");
		}
		else {
			return (suit + String.valueOf(number));
		}
	}
	
	public String getSuit() {
		switch(this.suit) {
		case 'h':
			return "Hearts";
		case 'd':
			return "Diamonds";
		case 'c':
			return "Clubs";
		case 's':
			return "Spades";
		default:
			return "NO SUIT";
		}
	}

		
	public String toString() {
		return this.getStringNumber() + " of " + this.getSuit();
	}

	public boolean isEqual(Card c){
		if(this.toString().equals(c.toString())){
			return true;
		}
		else{
			return false;
		}
	}
	

}