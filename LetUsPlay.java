// -------------------------------------------------------
// Assignment 4
// Written by: Anh Tuan Nguyen Tran 40068891
// For COMP 248 Section (P) – Fall 2019
// Description: This is a board game, with the use of arrays, 
// with a Dice Class and Player Class. 
// --------------------------------------------------------

package assignment4;

import java.util.Scanner;
import java.util.Random;

// Create LetUsPlay Class
public class LetUsPlay {

	public static void main(String[] args) {
		
		// Welcome Banner 
		for(int i = 0;i<23;i++){
            System.out.print("* ");
        }
        System.out.println();
        for(int i = 0;i<22;i++){
            System.out.print(" -");
        }
        System.out.println();
        System.out.print("*");
        for(int i = 0;i<46;i++){
            System.out.print(" ");
        }
        System.out.println("*");
        System.out.print("*\tWELCOME to Nancy's 3D Warrior Game!\t*");
        System.out.print("\n*");
        for(int i = 0;i<46;i++){
            System.out.print(" ");
        }
        System.out.println("*");
        for(int i = 0;i<23;i++){
            System.out.print("* ");
        }
        System.out.println();
        for(int i = 0;i<22;i++){
            System.out.print(" -");
        }
        System.out.println();
		
		// Prompt user for board size 
		System.out.println("The default game board has 3 levels and each level has a 4x4 board.");
		System.out.println("You can use this default board size or change the size");
		System.out.println("0 to use the default board ");
		System.out.println("-1 to to enter your own size ");
		
		Scanner scanner = new Scanner(System.in);
		Board board = new Board();
		int choice1 = -2;
		
		// Choosing board size and levels, using while and if statements
		while (choice1 != 0 || choice1 != -1) {
			choice1 = scanner.nextInt();
			if(choice1 == 0) {
				board = new Board();
				break;
			} else if(choice1 == -1){ 
				System.out.print("How many levels would you like? (minimum size 3, max 10)");
				int choice2 = 2;
				while(choice2 < 3) {
					choice2 = scanner.nextInt();
					if(choice2 > 2) {
						break;
					}
					System.out.println("Sorry but " + choice2 + " is not a legal choice.");
				}
				int choice3 = 3;
				System.out.print("What size do you want the nxn boards on each level to be? ");
				while(choice3 < 4) {
					choice3 = scanner.nextInt();
					if (choice3 > 3) {
						break;
					}
					System.out.println("Sorry but " + choice3 + " is not a legal choice.");
				}
				board = new Board(choice2, choice3);
			}
		}
		
		// Displaying the game board
		System.out.print("Your 3D board has been set up and looks like this: ");
		System.out.println(board.toString());
		
		// Get player names
		System.out.print("What is player 0's name (one word only): " );
		System.out.println();
		Player player0 = new Player();
		player0.setName(scanner.next());
		
		System.out.print("What is player 1's name (one word only): " );
		
		Player player1 = new Player();
		player1.setName(scanner.next());
		
		System.out.println();
		
		// Game Starts
		System.out.println("The game has started " + player0.name + " goes first");
		System.out.println("================================");
		Dice dice = new Dice();
		
		// While loop until someone wins
		while (!player0.won(board) && !player1.won(board)) {
			Player player = player0;
			Player other = player1;
			for (int i = 0; i < 2; i++) {
				if(i % 2 == 1) {
					player = player1;
					other = player0;
				}
				System.out.println();
				System.out.println("It is " + player.getName() + "'s turn");
				
				// If statement if current player is too weak
				if(player.getEnergy()<= 0) {
					System.out.println("\tYou are too weak, roll dice to gain energy.");
					for(int j = 0; j < 3 && player.getEnergy() <= 0; j++) {
						// Roll Dice
						dice.rollDice();
						// If roll is a double, increase energy by 2
						if(dice.isDouble()) {
							player.setEnergy(player.getEnergy()+2);
						}
					}
					continue;
				}
				
				dice.rollDice();
				System.out.println("\t" + player.name + " you rolled " + dice.toString());
				
				// Calculating the new location 
				int position = player.getY()*board.getSize() + player.getX();
				position += dice.die1 + dice.die2;
				int oldX = player.getX();
				int oldY = player.getY();
				int oldLevel = player.getLevel();
				if (position >= board.getSize()*board.getSize()) {
					// If the resulting level is >= the number of levels, then player stays put and loses 2 energy units
				    if (player.getLevel() == board.getLevel()-1) {
				        System.out.println("!!! Sorry you need to stay where you are - that throw takes you off the grid and you lose 2 units of energy.");
				        player.setEnergy(player.getEnergy()-2);
				    } else {
				    	// If x is off the board, move up one level, and determine resulting x
				        player.setLevel(player.getLevel()+1);
				        position = position % (board.getSize()*board.getSize());
				        player.setY(position / board.getSize());
				        player.setX(position % board.getSize());
				    }
				    // If roll a double, add 2 energy units
				    if (dice.isDouble()) {
				        System.out.println("\tCongratulations you rolled double " + dice.die1 + ". Your energy went up 2 units");
				        player.setEnergy(player.getEnergy()+2);
				    }
				} else {
					// Calculate new coordinates
			        player.setY(position / board.getSize());
			        player.setX(position % board.getSize());
				}
				// Challenge when both players are at the same location
				if (player.getX() == other.getX() && player.getY() == other.getY() && player.getLevel() == other.getLevel()) {
					// Prompt user to choose to forfeit or challenge
				    System.out.println("Player " + other.name + " is at your new location");
				    System.out.println("What do you want to do? ");
				    System.out.println("\t 0 - Challenge and risk loosing 50% of your energy units if you lose\nor move to new location and get 50% of other players energy units");
				    System.out.println("\t 1 - to move down one level or move to (0,0) if at level 0 and lose 2 energy");
				    int choice2 = -1;
				    
				    // While statement, input is not permitted
				    while (choice2 !=0 && choice2 !=1) {
				    	choice2 = scanner.nextInt();
				    	if(choice2 !=0 && choice2 !=1) {
				    		System.out.println("Sorry but " + choice2 + " is not a legal choice.");
				    	}
				    }
				    
				    // If statement to determine if challenge won or lost
				    if(choice2 == 0) {
				    	Random rand = new Random();
				    	// Lost challenge
				    	if (rand.nextInt() % 10 + 1 < 6) {
				    		System.out.println("\tSorry you lost the challenge.");
				    		int energy = player.getEnergy() /2;
				    		player.setEnergy(player.getEnergy()-energy);
				    		player.setX(oldX);
				    		player.setY(oldY);
				    		player.setLevel(oldLevel);
				    	} else {
				    		// Won Challenge 
				    		System.out.println("\tBravo!! You won the challenge.");
				    		int energy = other.getEnergy() /2;
				    		player.setEnergy(player.getEnergy()+energy);
				    		other.setEnergy(other.getEnergy()-energy);
				    	}
				    } else {
				    	// Forfeit
				    	if(player.getLevel() > 0) {
				    		player.setLevel(player.getLevel()-1);
				    	} else {
				    		player.setX(0);
				    		player.setY(0);
				    	}
				    }
				    
				}
				// Adjusting energy at the end of the round 
				int energy = board.getEnergyAdj(player.getLevel(), player.getX(), player.getY());
				player.setEnergy(player.getEnergy()+energy);
				System.out.println("Your energy was adjusted " + energy + " for landing at (" + player.getX() +"," + player.getY() + ") at level " + player.getLevel());
			}
			// Display end of the round 
			System.out.println();
			System.out.println("At the end of this round, ");
			// Display current locations of players
			System.out.println(player0.getName() + " is on level " + player0.getLevel() + " at location (" + player0.getX() + ", " + player0.getY() + ") and has " + player0.getEnergy() + " units of energy. ");
			System.out.println(player1.getName() + " is on level " + player1.getLevel() + " at location (" + player1.getX() + ", " + player1.getY() + ") and has " + player1.getEnergy() + " units of energy. ");
			System.out.println("Press any key to continue...");
			// Press any key to continue
			try{ 
			System.in.read();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		// When a player wins, display the winner.
		if(player0.won(board)) {
			System.out.println("The winner is player " + player0.getName() + ". Well done!!!");
		} else {
			System.out.println("The winner is player " + player1.getName() + ". Well done!!!");
		}
		//Closing message
		System.out.println("Thank you for playing! end of game");
	}
}
