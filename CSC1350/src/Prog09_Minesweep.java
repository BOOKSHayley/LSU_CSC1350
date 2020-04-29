import java.util.Scanner;

/**
 * This program is the basis for a game called Minesweep
 * where there is a matrix of squares that may or may not contain a bomb.
 * This program makes the matrix and prints it out.
 * 
 * CSC 1350 Programming Project #9
 * Section 2
 * 
 * @author hrobe39
 * @since 18 November 2019
 *
 */
public class Prog09_Minesweep {
	
	/**
	 * This method makes sure the user input is an integer greater than a minimum
	 * Developed: 18 November 2019
	 * @author hrobe39
	 * 
	 * @param userInput, Scanner object
	 * @param output, String value to prompt user for information
	 * @param min, integer value inclusive
	 * @return the integer greater than min that the user entered
	 */
	public static int validInt(Scanner userInput, String output, int min) {
		int retVal = 0;
		boolean valid = false;
		do {
			System.out.println(output);
			
			if(userInput.hasNextInt()) {
				retVal = userInput.nextInt();
				if(retVal >= min) {
					valid = true;
				} else {
					System.out.printf("Error: %d is not greater than %d.\n", retVal, min);
				}
			} else {
				String badInput = userInput.next();
				System.out.printf("Error: %s is not a valid input.\n", badInput);
			}
		} while(!valid);
		
		return retVal;
	}
	
	/**
	 * checkNeighbors looks at adjacent values and adds up the number of bombs nearby
	 * Developed: 18 November 2019
	 * @author hrobe39
	 * 
	 * @param matrix, 2-D array of integers
	 * @param curRow, integer current row location
	 * @param curCol, integer current column location
	 * @param maxRow, integer max number of rows
	 * @param maxCol, integer max number of columns
	 * @return the number of bombs adjacent to the current position
	 */
	//Step 4 : make method to check number of bombs in adjacent squares
	public static int checkNeighbors(int[][] matrix, int curRow, int curCol, int maxRow, int maxCol) {
		int bombCount = 0; //variabke to keep count of bombs nearby
		
		//handle corners first
		if(curRow == 0 && curCol == 0) {
			bombCount = isBomb(matrix, 0, 1) + isBomb(matrix, 1, 0) + isBomb(matrix, 1, 1);
		} else if(curRow == maxRow && curCol == 0) {
			bombCount = isBomb(matrix, maxRow-1, 0) + isBomb(matrix, maxRow-1, 1) + isBomb(matrix, maxRow, 1);
		} else if(curRow == 0 && curCol == maxCol) {
			bombCount = isBomb(matrix, 0, maxCol-1) + isBomb(matrix, 1, maxCol-1) + isBomb(matrix, 1, maxCol);
		} else if(curRow == maxRow && curCol == maxCol) {
			bombCount = isBomb(matrix, maxRow, maxCol-1) + isBomb(matrix, maxRow-1, maxCol-1) + isBomb(matrix, maxRow-1, maxCol);
		} else if(curRow == 0) { //handle edges next
			bombCount = isBomb(matrix, 0, curCol-1) + isBomb(matrix, 1, curCol-1) + isBomb(matrix, 1, curCol) + isBomb(matrix, 1, curCol+1) + isBomb(matrix, 0, curCol+1);
		} else if(curRow == maxRow) {
			bombCount = isBomb(matrix, maxRow, curCol-1) + isBomb(matrix, maxRow-1, curCol-1) + isBomb(matrix, maxRow-1, curCol) + isBomb(matrix, maxRow-1, curCol+1) + isBomb(matrix, maxRow, curCol+1);
		} else if(curCol == 0) {
			bombCount = isBomb(matrix, curRow-1, 0) + isBomb(matrix, curRow-1, 1) + isBomb(matrix, curRow, 1) + isBomb(matrix, curRow+1, 1) + isBomb(matrix, curRow+1, 0);
		} else if(curCol == maxCol) {
			bombCount = isBomb(matrix, curRow-1, maxCol) + isBomb(matrix, curRow-1, maxCol-1) + isBomb(matrix, curRow, maxCol-1) + isBomb(matrix, curRow+1, maxCol-1) + isBomb(matrix, curRow+1, maxCol);
		} else { //if not a corner or an edge, then square must be in the middle 
			bombCount = isBomb(matrix, curRow-1, curCol-1) + isBomb(matrix, curRow, curCol-1) + isBomb(matrix, curRow+1, curCol-1); //add everything in column before current
			bombCount += isBomb(matrix, curRow-1, curCol) + isBomb(matrix, curRow+1, curCol);  //add everything in current column
			bombCount += isBomb(matrix, curRow-1, curCol+1) + isBomb(matrix, curRow, curCol+1) + isBomb(matrix, curRow+1, curCol+1); //add everything in column after current
		} 
		
		return bombCount;
	}
	
	/**
	 * This method finds whether or not there is a bomb in the current position
	 * Developed: 18 November 2019
	 * @author hrobe39
	 * 
	 * @param matrix, 2-D integer array
	 * @param curRow, integer
	 * @param curCol, integer
	 * @return 1 if there is a bomb and 0 otherwise
	 */
	//Step 4.5: make helper method, so that can add adjacent values without worrying about adding a number that is not 0 or 1
	//if didn't have this method, then changing the matrix would not work bc then adding values stored in squares around current location
	public static int isBomb(int[][] matrix, int curRow, int curCol) {
		int bomb = 0;
		if(matrix[curRow][curCol] == -1) {
			bomb = 1;
		}
		return bomb;
	}
	
	/**
	 * This method checks to see if more than one bomb was placed in a square
	 * Developed: 18 November 2019
	 * @author hrobe39
	 * 
	 * @param matrix, 2-D integer array
	 * @param rows, integer
	 * @param cols, integer
	 * @param numBombs, integer number of bombs that are supposed to be there
	 * @return boolean whether or not the correct number of bombs are in the matrix
	 */
	public static boolean checkForDoubleBombs(int[][] matrix, int rows, int cols, int numBombs) {
		int bombCount = 0;
		boolean correctNumber = false; //assume incorrect number of bombs placed
		
		//loop through matrix and count total number of bombs
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				if(matrix[i][j] == -1) {
					bombCount++;
				}
			}
		}
		
		if(bombCount == numBombs) { //should never be >, and < is incorrect number of bombs.  == works bc integer values
			correctNumber = true;
		}
		
		return correctNumber;
		
	}
	
	/**
	 * This method prints a certain number of dashes
	 * Developed: 18 November 2019
	 * @author hrobe39
	 * 
	 * @param n, integer number of dashes to be printed
	 */
	public static void printNDashes(int n) {
		for(int i = 0; i < n; i++) {
			System.out.print("-");
		}
		System.out.println();
	}

	public static void mainMethod() {
		//Step 1: Make Scanner object and declare variables
		Scanner userInput = new Scanner(System.in);
		
		//take in size of matrix from user and make the matrix
		int rows = validInt(userInput, "Enter number of rows in matrix (must be more than 4):", 4);
		int cols = validInt(userInput, "Enter number of columns in matrix (must be more than 4):", 4);
		
		//getting out-of-bounds errors bc 0-indexed
		// so make variables 
		int actualRows = rows-1;
		int actualCols = cols-1;
		
		int[][] board = new int[rows][cols];
		
		//Step 2: find number of squares in board and 10% of them
		int numSquares = rows * cols;
		int tenPercent = (int) (numSquares * 0.1); //cast as integer bc no partial bombs
		
		//Step 3: randomly assign squares to a bomb (-1); need to make sure random squares do not repeat so use while loop
		do {
			int randomRow = (int) (Math.random() * (actualRows + 1)); //random number [0, actualRows]
			int randomCol = (int) (Math.random() * (actualCols + 1)); //random number [0, actualCols]
			
			board[randomRow][randomCol] = -1; //-1 denotes a bomb. Other spaces are 0 by default
			
		} while(!checkForDoubleBombs(board, rows, cols, tenPercent));
		
		//Step 5: make matrix reflect number of bombs nearby
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				if(board[i][j] != -1) { //there is no bomb at current location
					board[i][j] = checkNeighbors(board, i, j, actualRows, actualCols);
				}
			}
		}
		
		//Step 6: print matrix in boxes
		int numDashes = actualCols * 5; //5 dashes per column
		for(int i = 0; i < rows; i++) {
			printNDashes(numDashes); //print n dashes between each row
			for(int j = 0; j < cols; j++) {
				if(board[i][j] == -1) {
					System.out.printf("|%2s ", "*");
				} else {
					System.out.printf("|%2d ", board[i][j]);
				}
			}
			System.out.println("|"); //print out last bar then go to next line
		}
		
		printNDashes(numDashes); //print last bar of dashes
		
		//Step 7: close Scanner so no resource leak
		userInput.close();
		
	}

}
