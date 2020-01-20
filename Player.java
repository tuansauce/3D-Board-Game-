// -------------------------------------------------------
// Assignment 4
// Written by: Anh Tuan Nguyen Tran 40068891
// For COMP 248 Section (P) – Fall 2019
// Description: This is a board game, with the use of arrays, 
// with a Dice Class and Player Class. 
// --------------------------------------------------------

package assignment4;
// Create Player class
public class Player {
	// Defining variables
	int level, x, y, energy; 
	String name;
	
	// Default constructor 
	public Player() {
		name = "";
		energy = 10;
		level = 0;
		x = 0; 
		y = 0;
	}
	
	// Constructor 
	public Player(int level, int x, int y) {
		this.level = level;
		this.x = x; 
		this.y = y;
		name = "";
	}
	
	// Copy Constructor
	public Player(Player other) {
		level = other.level;
		x = other.x;
		y = other.y;
		name = "";
	}
	// Mutator Methods 
	public int getLevel() {
		return level;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y; 
	}
	public int getEnergy() {
		return energy;
	}
	public String getName() {
		return name;
	}
	
	// Accessor methods 
	public void setLevel(int l) {
		level = l;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setEnergy(int e) {
		this.energy = e;
	}
	public void setName(String n) {
		this.name = n;
	}
	
	// moveTo(Player p) method
	public void moveTo(Player p) {
		level = p.level;
		x = p.x;
		y = p.y;
	}
	
	// won(Board b) method 
	public boolean won(Board b) {
		return level == b.level -1 && x == b.size -1 && y == b.size -1;
	}
	
	// equals(Player p) method 
	public boolean equals(Player p) {
		return x == p.x && y == p.y && level == p.level;
	}
	
	// toString method 
	public String toString() {
		return "Name: " + name + " Level: " + level + " x: " + x + " y: " + y + " Energy: " + energy;
	}
}
