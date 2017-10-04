package edu.kings.cs448.fall2017.MaloneySean.hw02;


/**
 * Class to represent a state in an EightQueensProblem.
 * @author Sean Maloney
 */
public class EightQueensState {

	/**
	 * A 2 dimensional array of Chars where a Q is placed anywhere a queen is.
	 */
	private char[][] board;
	
	/**
	 * Constructor that takes a 2 dimensional array of chars as the only parameter.
 	 * @param gameBoard Value for board to be set to.
	 */
	public EightQueensState(char[][] gameBoard) {
		board = gameBoard;
	}
	
	/**
	 * Method to get a copy of the current state of the game board.
	 * @return The copy of the game board.
	 */
	public char[][] getBoardCopy(){
		char[][] copy = new char[board.length][board[0].length];
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				copy[i][j] = board[i][j];
			}
		}
		
		return copy;
	}
	
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				result.append(board[i][j]);
			}
			result.append("\n");
		}
		
		return result.toString();
	}
	
	/**
	 * Determines whether two EightPuzzleStates are equal or not.
	 * 
	 * @param o The other state.
	 * @return Whether or not the states are equal.
	 */
	@Override
	public boolean equals(Object o) {
		boolean result;
		if(this == o) {
			result = true;
		}
		else {
			if(o == null) {
				result = false;
			}
			else {
				if(o instanceof EightQueensState) {
					EightQueensState otherState = (EightQueensState) o;
					char[][] otherConfig = otherState.getBoardCopy();
					
					result = true;
					
					for(int i = 0; i < board.length; i++) {
						for(int j = 0; j < board[0].length; j++) {
							if(board[i][j] != otherConfig[i][j]) {
								result = false;
							}
						}
					}
						
				}
				else {
					result = false;
				}
			}
		}
		
		return result;
	}
	
	@Override
	public int hashCode() {
		int result = 00000000;
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				char current = board[i][j];
				
				if(current == 'Q') {
					int shift = i;
					shift *= (10 ^ j);
					
					result += shift;
				}
				
			}
		}
		
		return result;
	}
}
