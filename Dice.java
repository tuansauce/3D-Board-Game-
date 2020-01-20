// -------------------------------------------------------
// Assignment 4
// Written by: Anh Tuan Nguyen Tran 40068891
// For COMP 248 Section (P) – Fall 2019
// Description: This is a board game, with the use of arrays, 
// with a Dice Class and Player Class. 
// --------------------------------------------------------

package assignment4;

// Need this to general random numbers
import java.util.Random;

// Creating Dice Class
public class Dice {
	
	// Defining variables
	int die1, die2;
	Random rand;
	
	// Default constructor 
	public Dice() {
		rand = new Random();
		rollDice();
	}
	
	// rollDice method 
	public int rollDice() {
		die1 = rand.nextInt(7);
		die2 = rand.nextInt(7);
		
		return die1+die2;
	}
	
	// isDouble method 
	public boolean isDouble() {
		return die1 == die2;
	}
	
	// toString method 
	public String toString() {
		return "Die1: " + die1 + " Die2: " + die2;
	}
}
