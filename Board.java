package assignment4;
// Create the Board Class
public class Board {
	// Defining variables
	int[][][] board;
	static int MIN_LEVEL, MIN_SIZE = 3;
	int level, size;
	// Default constructor with set values, and calling method createBoard
	public Board() {
		level = 3;
		size = 4;
		createBoard(level, size);
	}
	// Constructor with number of levels and number of rows
	public Board(int l, int x) {
		level = l;
		size = x;
		createBoard(level, size);
	}
	// createBoard method
	private void createBoard(int level, int size) {
		board = new int[level][size][size];
		for(int l = 0; l < level; l++) {
			for( int x = 0; x < size; x++) {
				for (int y = 0; y < size; y++) { 
					if (board[l][x][y] %3 == 0) {
						board[l][x][y] = -3;
					} else if (board[l][x][y] %5 == 0) {
						board[l][x][y] = -2;
					} else if (board[l][x][y] %7 == 0) {
						board[l][x][y] = 2;
					} else {
						board[l][x][y] = 0;
					}
					
				}
			}
		}
	}
	// Accessor methods 
	public int getLevel() {
		return level;
	}
	public int getSize() {
		return size;
	}
	// getEnergyAdj Method 
	public int getEnergyAdj(int l, int x, int y) {
		return board[l][x][y];
	}
	// toString Method
	public String toString() {
		String output = "";
		for (int l = 0; l < level; l++) { 
			output += "Level " + l +  "\r\n";
			output += "--------\r\n";
			for (int x = 0; x < size; x++) {
				for(int y = 0; y < size; y++) {
					output += String.format("|%5d|", board[l][x][y]);
				}
				output += "\r\n";
			}
			output += "\r\n";
		}
		return output;
	}
}