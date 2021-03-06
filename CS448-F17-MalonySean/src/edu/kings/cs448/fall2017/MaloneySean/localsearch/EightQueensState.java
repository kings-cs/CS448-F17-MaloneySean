package edu.kings.cs448.fall2017.MaloneySean.localsearch;

import java.util.HashSet;
import java.util.Set;

/**
 * Class to represent a state in an EightQueensProblem.
 * 
 * @author Sean Maloney
 */
public class EightQueensState {

	/**
	 * A 2 dimensional array of Chars where a Q is placed anywhere a queen is.
	 */
	private char[][] board;

	/**
	 * Constructor that takes a 2 dimensional array of chars as the only parameter.
	 * 
	 * @param gameBoard
	 *            Value for board to be set to.
	 */
	public EightQueensState(char[][] gameBoard) {
		board = gameBoard;
	}
	
	/**
	 * Constructor used to create an EightQueensState based on row coordinate for each of the eight Queens. 
	 * 
	 * @param rowOne The first row coordinate.
	 * @param rowTwo The second row coordinate.
	 * @param rowThree The third row coordinate.
	 * @param rowFour The fourth row coordinate. 
	 * @param rowFive The fifth row coordinate. 
	 * @param rowSix The sixth row coordinate. 
	 * @param rowSeven The seventh row coordinate. 
	 * @param rowEight The eighth row coordinate.
	 */
	public EightQueensState(int rowOne, int rowTwo, int rowThree, int rowFour, int rowFive, int rowSix, int rowSeven, int rowEight) {
		board = new char[8][8];
		
		board[rowOne][0] = 'Q';
		board[rowTwo][1] = 'Q';
		board[rowThree][2] = 'Q';
		board[rowFour][3] = 'Q';
		board[rowFive][4] = 'Q';
		board[rowSix][5] = 'Q';
		board[rowSeven][6] = 'Q';
		board[rowEight][7] = 'Q';
	}

	/**
	 * Method to get a copy of the current state of the game board.
	 * 
	 * @return The copy of the game board.
	 */
	public char[][] getBoardCopy() {
		char[][] copy = new char[board.length][board[0].length];

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				copy[i][j] = board[i][j];
			}
		}

		return copy;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				result.append(board[i][j]);
			}
			result.append("\n");
		}

		return result.toString();
	}

	/**
	 * Determines whether two EightPuzzleStates are equal or not.
	 * 
	 * @param o
	 *            The other state.
	 * @return Whether or not the states are equal.
	 */
	@Override
	public boolean equals(Object o) {
		boolean result;
		if (this == o) {
			result = true;
		} else {
			if (o == null) {
				result = false;
			} else {
				if (o instanceof EightQueensState) {
					EightQueensState otherState = (EightQueensState) o;
					char[][] otherConfig = otherState.getBoardCopy();

					result = true;

					for (int i = 0; i < board.length; i++) {
						for (int j = 0; j < board[0].length; j++) {
							if (board[i][j] != otherConfig[i][j]) {
								result = false;
							}
						}
					}

				} else {
					result = false;
				}
			}
		}

		return result;
	}

	@Override
	public int hashCode() {
		int result = 00000000;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				char current = board[i][j];

				if (current == 'Q') {
					int shift = i;
					shift *= (10 ^ j);

					result += shift;
				}

			}
		}

		return result;
	}
	
	/**
	 * Gets all states that would result from making a single move in this state.
	 * @return The set of states.
	 */
	public Set<EightQueensState> getChildren(){
		Set<EightQueensState> result = new HashSet<EightQueensState>();
		
		for (int i = 0; i < board.length; i++) {
			int queenLocation = findQueen(i);
			for (int j = 1; j < board[0].length; j++) {
				char[][] boardCopy = this.getBoardCopy();
				
				boardCopy[queenLocation][i] = ' ';
				
				int newQueen = queenLocation + j;
				
				if(newQueen >= board[0].length) {
					newQueen = newQueen % board[0].length;
				}
				
				boardCopy[newQueen][i] = 'Q';
				
				EightQueensState neighbor = new EightQueensState(boardCopy);
				result.add(neighbor);
			}
		}
		
		return result;
	}
	
	/**
	 * Private helper method to find the row coordinate of a queen in a given column.
	 * @param column The column the queen is in.
	 * @return The row coordinate of the queen.
	 */
	private int findQueen(int column) {
		int result = 0;
		int count = 0;
		boolean found = false;
		
		while(!found && count < board[0].length) {
			char current = board[count][column];
			
			if(current == 'Q') {
				found = true;
				result = count;
			}
			
			count++;
		}
		
		return result;
	}
	
	/**
	 * Method to make a mutation based off the current state.
	 * @param column The column to be mutated.
	 * @param newRow Where within the column to move the Queen to.
	 */
	public void mutateQueen(int column, int newRow) {	
		int currentRow = findQueen(column);
		int update = newRow;
		
		
		
		board[currentRow][column] = ' ';
		
		
		if(currentRow == newRow) {
			update = newRow + 1;
			if(update > 7) {
				update = update % 8;
			}
		}
		
		board[update][column] = 'Q';
	}
	

	/**
	 * Calculates the amount of pairs of queens that are attacking each other on a given board.
	 * @return The amount of attacking queen pairs.
	 */
	public int getQueensAttacking() {
		int queens = 0;

		// Checks for pairs within the same row
		for (int i = 0; i < board.length; i++) {
			char[] row = board[i];
			// Gets the amount of Queens in a row
			int rowCount = 0;
			for (int j = 0; j < row.length; j++) {
				char current = row[j];
				if (current == 'Q') {
					rowCount++;
				}
				
			}
			if(rowCount != 0) {
				queens += nChoose2(rowCount);
			}
		}
		
		
		//System.out.println("ROW: " + queens);

		// Checks for pairs within the same column
		for (int i = 0; i < board.length; i++) {
			int columnCount = 0;
			for (int j = 0; j < board[0].length; j++) {
				char current = board[j][i];
				if (current == 'Q') {
					columnCount++;
				}
			}
			if(columnCount != 0) {
				queens += nChoose2(columnCount);
			}
		}
		
		//System.out.println("ROW + COL: " + queens);
		
		//Checks for Queen Pairs on the left diagonal (/).
		for (int i = 0; i < 2 * board.length; i++) {
			int leftDiag = 0;

		    for (int j = 0; j < board[0].length; j++) {
		        int coord = i - j;
		        if(coord >= 0 && coord < board.length) {
		        	char leftCurrent = board[j][coord];
		        	if(leftCurrent == 'Q') {
		        		leftDiag++;
		        	}		  
		        }
		    }
		    if(leftDiag != 0) {
		    	queens += nChoose2(leftDiag);
		    }
		}
		
		//System.out.println("ROW + COL + /: " + queens);
		
		for (int i = 2 * board.length; i > 0; i--) {
			int rightDiag = 0;

		    for (int j = 0; j < board[0].length; j++) {
		        int coord = i - j;
		        if(coord >= 0 && coord < board.length) {
		        	char leftCurrent = board[j][coord];
		        	if(leftCurrent == 'Q') {
		        		rightDiag++;
		        	}		  
		        }
		    }
		    if(rightDiag != 0) {
		    	queens += nChoose2(rightDiag);
		    }
		}
		
		//Checks for Queens pairs on the left diagonal (/).
		

		//System.out.println("COL: " + queens);
		return queens;
	}

	/**
	 * Private helper method to calculate n choose 2.
	 * 
	 * @param count
	 *            n.
	 * @return The result of count choose 2.
	 */
	private int nChoose2(int count) {
		int result = 0;
		if(count != 2) {
			int countFact = count;
			int twoFact = 2;

			for (int i = 1; i < count; i++) {
				countFact = countFact * (count - i);
			}

			int nK = count - 2;
			int countSub = count - 2;
			for (int i = 1; i < nK; i++) {
				countSub = countSub * (nK - i);
			}

			result = countFact / (twoFact * countSub);
		}
		else {
			result = 1;
		}

		return result;
	}
	
//	/**
//	 * Main method used only for testing.
//	 * @param args Not used.
//	 */
//	public static void main(String[] args) {
//		char[][] testBoard = { { 'Q', ' ', ' ', 'Q', ' ', 'Q', ' ', 'Q' }, 
//							   { ' ', 'Q', ' ', ' ', ' ', ' ', ' ', ' ' },
//							   { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
//							   { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
//							   { ' ', ' ', ' ', ' ', 'Q', ' ', ' ', ' ' }, 
//							   { ' ', ' ', ' ', ' ', ' ', ' ', 'Q', ' ' },
//							   { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, 
//							   { ' ', ' ', 'Q', ' ', ' ', ' ', ' ', ' ' } };
//
//		char[][] testBoardTwo = { { '1', '2', '3', '4', '5', '6', '7', '8' }, 
//				{ '2', '1', '2', '3', '4', '5', '6', '7' },
//				{ '3', '2', '1', '2', '3', '4', '5', '6' }, 
//				{ '4', '3', '2', '1', '2', '3', '4', '5' },
//				{ '5', '4', '3', '2', '1', '2', '3', '4' }, 
//				{ '6', '5', '4', '3', '2', '1', '2', '3' },
//				{ '7', '6', '5', '4', '3', '2', '1', '2' }, 
//				{ '8', '7', '6', '5', '4', '3', '2', '1' } };
//		
//		
//
//		EightQueensState test = new EightQueensState(testBoard);
//		EightQueensState testTwo = new EightQueensState(testBoardTwo);
//
//		test.getChildren();
//	}
}
