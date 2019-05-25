import java.util.Scanner;

public class TicTacToe {
	
	static char [][] gameBoard = new char[3][3];
	
	public static void initalizeBoard() {
		
		/*
		 * Initializes our gameBoard array with '-'s.
		 */	
		
		for(int row = 0; row < 3; ++row) {
			for(int col = 0; col < 3; ++col) {
				gameBoard[row][col] ='-';
			}
		}
		
	}
	
	public static void printBoard() {
		
		/*
		 * Prints our gameBoard array to the screen
		 * (with additional spacing for readability).
		 */
		
		for(int row = 0; row < 3; ++row) {
			System.out.println();
			for (int col = 0; col < 3; ++col) {
				System.out.print(gameBoard[row][col]);
				System.out.print("    ");
			}
		}
		System.out.println();
		System.out.println();
	}
	
	public static String getUserMove() {
		
		/*
		 * Receives our user's input and assigns its value to
		 * userMove. userMove is then passed to the checkMove()
		 * method to test for validity.
		 * 
		 */
		
		Scanner scnr = new Scanner(System.in);
		String userMove = "";
		boolean isMoveValid = false;
		
		while(!isMoveValid) {
			System.out.println("Your move is:");
			userMove = scnr.next();
			isMoveValid = checkMove('X', userMove);
		}
		return userMove;
	}
 
	
	public static boolean checkMove(char player, String move) {
		
		/*
		 * Receives user's string userMove and converts
		 * it into integers as rows and columns. After, it 
		 * checks to make sure the input is within bounds of 
		 * the gameBoard array.
		 */
	
		
		boolean isValidMove = false;
		int row = Character.getNumericValue(move.charAt(1));
		int col = Character.getNumericValue(move.charAt(4));
		
		
		// If user's move is outside of gameBoard array index,
		// return isValidMove as false.
		if(row < 0 || row > 2 || col < 0 || col > 2) {
			System.out.println("Invalid move. Try again.");
			return isValidMove;
		}
		
		// If user's move has already been chosen,
		// print error to screen.
		if(gameBoard[row][col] == '-' ) {
			isValidMove = true;
		}
		else {
			System.out.println("Invalid move. Try again.");
		}
		
		return isValidMove;
	
	}
	
	public static void updateBoard(char player, String move) {
		
		/*
		 * Reprints the gameBoard array after updating the index with the
		 * player or computer's move.
		 */
		
		int row = Character.getNumericValue(move.charAt(1));
		int col = Character.getNumericValue(move.charAt(4));
		
		gameBoard[row][col] = player;
		
		printBoard();
	}
	
	public static String checkGameStatus(char[][] gameBoard) {
		
		/*
		 * Checks that gameBoard array for a winning condition.
		 * If the condition is met will return a string that
		 * will end the main loop.
		 */
		
		boolean userWins = checkForWinner('X', gameBoard);
		boolean computerWins = checkForWinner('O', gameBoard);
		
		if(userWins) {
			return "You win!";
		}
		if(computerWins) {
			return "I win!";
		}
		
		
		return "Ongoing";
	}
	
	public static boolean checkForWinner(char player, char[][] inputBoard) {
		
		/*
		 * Loops through our gameBoard array index and checks to see if Xs or Os
		 * are in the order for winning the game and returns true to checkGameStatus()
		 * if a winner is found.
		 */
		
		// Check for winner horizontally
		for(int i = 0; i < 3; ++i) {
			if(inputBoard[i][0] == player && inputBoard [i][1] == player && inputBoard [i][2] == player) {
				return true;
			}
		}
		
		// Check for winner vertically
		for(int i = 0; i < 3; ++i) {
			if(inputBoard[0][i] == player && inputBoard [1][i] == player && inputBoard [2][i] == player) {
				return true;
			}
		}
		
		// Check for winner diagonally
		if(inputBoard[0][0] == player && inputBoard [1][1] == player && inputBoard [2][2] == player) {
			return true;
		}
		if(inputBoard [0][2] == player && inputBoard [1][1] == player && inputBoard [2][0] == player) {
			return true;
		}
		
		
		
		return false;
	}
	
	public static String getComputerMove() {
		
		/*
		 * Checks for first available '-' in the array
		 * and returns its index.
		 */
		
		for(int row = 0; row < 3; ++row) {
			for(int col = 0; col < 3; ++col) {
				if(gameBoard[row][col] == '-')
				return "[" + row + "][" + col + "]";
			}
		}

		return "No move found.";
	}
	
	public static void main(String[] args) {
		
		initalizeBoard();
		printBoard();
		
		// Determines if we will continue looping or break the loop to end the game.
		String gameStatus = "Ongoing";
		
		System.out.println("Let's play Tic-Tac-Toe.");
		System.out.println("Enter your move in format: [row][column].");
		
		while(true) {
			
			// Get user input
			String userMove = getUserMove();
			
			// Print game board with user input
			updateBoard('X', userMove);
			
			// Check for winner
			gameStatus = checkGameStatus(gameBoard);
			
			// If Winner is found, break loop
			if(!gameStatus.equals("Ongoing")) {
				break;
			}
			
			// Computer makes a move
			String computerMove = getComputerMove();
			System.out.println("My move is: " + computerMove);
			
			// Print updated board with computer's move
			updateBoard('O', computerMove);
			
			// Check for winner
			gameStatus = checkGameStatus(gameBoard);
			
			// If Winner is found, break loop
			if(!gameStatus.equals("Ongoing")) {
				break;
			}
		}
		
		System.out.println(gameStatus);
	}
}