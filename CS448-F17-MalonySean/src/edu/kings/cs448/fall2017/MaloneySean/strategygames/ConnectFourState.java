package edu.kings.cs448.fall2017.MaloneySean.strategygames;

import java.util.Arrays;

/**
 * A state in the game Connect Four.
 * @author Sean Maloney
 */
public class ConnectFourState {

	/**
	 * The game board.
	 */
	private char[][] gameBoard;
	
	/** The number of spots Player.MAX has filled. */
	private int maxCount;
	/** The number of spots Player.MIN has filled. */
	private int minCount;
	
	/**
	 * Constructs a new Connect Four state for the beginning of the game.
	 */
	public ConnectFourState() {
		gameBoard = new char[6][7];
		maxCount = 0;
		minCount = 0;
		
		for(int row = 0; row < gameBoard.length; row++) {
			for(int col = 0; col < gameBoard[row].length; col++) {
				gameBoard[row][col] = ' ';
			}
		}
	}
	
	/**
	 * Constructs a new Connect Four state based on an existing array.
	 * 
	 * @param board
	 *            A 2-d array of characters.
	 */
	public ConnectFourState(char[][] board) {
		gameBoard = new char[6][7];
		maxCount = 0;
		minCount = 0;
		
		for(int row = 0; row < gameBoard.length; row++) {
			for(int col = 0; col < gameBoard[row].length; col++) {
				char player = board[row][col];
				gameBoard[row][col] = player;
				if (player == GamePlayer.MAX.getSymbol()) {
					maxCount++;
				} else if (player == GamePlayer.MIN.getSymbol()) {
					minCount++;
				}
			}
		}
	}
	
	/**
	 * Gets a deep copy of the board for this state.
	 * 
	 * @return A deep copy of the board for this state.
	 */
	public char[][] getGameBoardCopy() {
		char[][] copy = new char[6][7];
		for (int row = 0; row < copy.length; row++) {
			for (int col = 0; col < copy[row].length; col++) {
				copy[row][col] = gameBoard[row][col];
			}
		}
		return copy;
	}
	
	/**
	 * Gets the character at a specified row and column.
	 * 
	 * @param row
	 *            The row number.
	 * @param col
	 *            The column number.
	 * @return The character at that location on the board.
	 */
	public char getSymbol(int row, int col) {
		return gameBoard[row][col];
	}
	
	/**
	 * Gets the GamePlayer whose turn it is in this TicTacToeState.
	 * 
	 * @return The GamePlayer whose turn it is in this TicTacToeState.
	 */
	public GamePlayer getCurrentPlayer() {
		GamePlayer result = GamePlayer.MAX;

		if (maxCount > minCount) {
			result = GamePlayer.MIN;
		}

		return result;
	}
	
	/**
	 * Gets whether or not the board is completely full in this TicTacToeState.
	 * 
	 * @return Whether or not the board is completely full.
	 */
	public boolean isFull() {
		boolean result = false;
		if (maxCount + minCount == 42) {
			result = true;
		}
		return result;
	}
	
	/**
     * Gets an estimate of the utility that would eventually be reached from this state.
     * 
	 * @return An estimate of the utility that will be reached from this state with both players playing optimally.
	 */
	public int evaluate() {
		int result = 0;
		//TODO: FINISH THIS
		//ROWS
		for(int i = 0; i < gameBoard.length; i++) {
			int maxCount = 0;
			int minCount = 0;
			for(int j = 1; j < gameBoard[0].length; j++) {
				char current = gameBoard[i][j];
				char left = gameBoard[i][j - 1];
				if(current == GamePlayer.MAX.getSymbol() && left == GamePlayer.MAX.getSymbol()) {
					maxCount++;
				}
				else if(current == GamePlayer.MIN.getSymbol() && left == GamePlayer.MAX.getSymbol()) {
					minCount++;
				}
//				else {
//					//TODO: Should I remove this?
//					maxCount--;
//					minCount++;
//				}
				if(maxCount < 0) {
					maxCount = 0;
				}
				if(minCount > 0) {
					minCount = 0;
				}
			}
			result += maxCount;
			result -= minCount;
		}
		
		//COLUMNS
		for(int i = 1; i < gameBoard[0].length; i++) {
			int maxCount = 0;
			int minCount = 0;
			for(int j = 0; j < gameBoard.length; j++) {
				char current = gameBoard[j][i];
				char down = gameBoard[j][i - 1];
				if(current == GamePlayer.MAX.getSymbol() && down == GamePlayer.MAX.getSymbol()) {
					maxCount++;
				}
				else if(current == GamePlayer.MIN.getSymbol() && down == GamePlayer.MAX.getSymbol()) {
					minCount++;
				}
//				else {
//					//TODO: Should I remove this?
//					maxCount--;
//					minCount++;
//				}
				if(maxCount < 0) {
					maxCount = 0;
				}
				if(minCount > 0) {
					minCount = 0;
				}
			}
			result += maxCount;
			result -= minCount;
		}
		
		//LEFT DIAGONAL
		for(int i = 1; i < 2 * gameBoard[0].length; i++) {
			int maxCount = 0;
			int minCount = 0;
			for(int j = 1; j < gameBoard.length; j++) {
				int coord = i - j; 
				if(coord > 0 && coord < gameBoard[0].length) {

					char current = gameBoard[j][coord];
					char prev = gameBoard[j - 1][coord - 1];
					if(current == GamePlayer.MAX.getSymbol() && prev == GamePlayer.MAX.getSymbol()) {
						maxCount++;
					}
					else if(current == GamePlayer.MIN.getSymbol() && prev == GamePlayer.MAX.getSymbol()) {
						minCount++;
					}
//					else {
//						//TODO: Should I remove this?
//						maxCount--;
//						minCount++;
//					}
					if(maxCount < 0) {
						maxCount = 0;
					}
					if(minCount > 0) {
						minCount = 0;
					}
				}
			}
			result += maxCount;
			result -= minCount;
		}
		
		
		//RIGHT DIAGONAL
		for(int i = 2 * gameBoard[0].length; i > -gameBoard.length; i--) {
			int maxCount = 0;
			int minCount = 0;
			for(int j = 1; j < gameBoard.length; j++) {
				int coord = i + j;
				if(coord >= 0 && coord < gameBoard[0].length) {

					char current = gameBoard[j][coord];
					char prev = ' ';
					if(coord + 1 > gameBoard[0].length) {
						prev = gameBoard[j - 1][coord + 1];
					}
					
					if(current == GamePlayer.MAX.getSymbol() && prev == GamePlayer.MAX.getSymbol()) {
						maxCount++;
					}
					else if(current == GamePlayer.MIN.getSymbol() && prev == GamePlayer.MAX.getSymbol()) {
						minCount++;
					}
//					else {
//						//TODO: Should I remove this?
//						maxCount--;
//						minCount++;
//					}
					if(maxCount < 0) {
						maxCount = 0;
					}
					if(minCount > 0) {
						minCount = 0;
					}
				}
			}
			result += maxCount;
			result -= minCount;
		}
		

		return result;
	}	
	
	/**
	 * Gets whether or not a given GamePlayer has won in this state.
	 * 
	 * @param thePlayer The GamePlayer who might have won.
	 * @return Whether or not they won.
	 */
	public boolean didPlayerWin(GamePlayer thePlayer) {
		//TODO: TEST THIS
		boolean result = false;
		//check rows
		int i = 0;
		int connectCount = 0;
		while (i < gameBoard.length && !result) {
			int j = 0;
			while(j < gameBoard[i].length && !result) {
				char current = gameBoard[i][j];
				if(current == thePlayer.getSymbol()) {
					connectCount++;
				}
				else {
					connectCount = 0;
				}
				
				if(connectCount == 4) {
					result = true;
				}
				j++;
			}
			i++;
			connectCount = 0;
		}
		
		//Check Columns
		i = 0;
		connectCount = 0;
		while (i < gameBoard[0].length && !result) {
			int j = 0;
			while(j < gameBoard.length && !result) {
				char current = gameBoard[j][i];
				if(current == thePlayer.getSymbol()) {
					connectCount++;
				}
				else {
					connectCount = 0;
				}
				
				if(connectCount == 4) {
					result = true;
				}
				j++;
			}
			connectCount = 0;
			i++;
		}
		
		
		//left diagonal
		if(!result) {
			i = 0;
			connectCount = 0;
			
			while(i < 2 * gameBoard[0].length) {
				int j = 0;
				while(j < gameBoard.length) {
					int coord = i - j;
			        if(coord >= 0 && coord < gameBoard[0].length) {
			        	char current = gameBoard[j][coord];
			        	if(current == thePlayer.getSymbol()) {
			        		connectCount++;
			        	}	
			        	else {
							connectCount = 0;
						}
						
						if(connectCount == 4) {
							result = true;
						}
			        }
			       j++; 
				}
				connectCount = 0;
				i++;
			}
		}
		//right diagonal
		
		if(!result) {
			i = 2 * gameBoard[0].length;
			connectCount = 0;
			
			while(i > -gameBoard.length) {
				
				int j = 0;
				while(j < gameBoard.length) {
					int coord = i + j;
			        if(coord >= 0 && coord < gameBoard.length) {
			        	char current = gameBoard[j][coord];
			        	if(current == thePlayer.getSymbol()) {
			        		connectCount++;
			        	}	
			        	else {
							connectCount = 0;
						}
						
						if(connectCount == 4) {
							result = true;
						}
			        }
			        j++;
				}
				connectCount = 0;
				i--;
			}
		}

		return result;
	}
	
	
	
	
	//******************Over Ride Stuff**************************
	@Override
	public String toString() {
		StringBuffer seanBuffer = new StringBuffer();
		seanBuffer.append("---------------");
		seanBuffer.append("\n");
		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[row].length; col++) {
				seanBuffer.append(gameBoard[row][col]);
				if (col <= 5) {
					seanBuffer.append("|");
				}
			}
			seanBuffer.append("\n");
			//if (row <= 5) {
				seanBuffer.append("---------------");
				seanBuffer.append("\n");
			//}
		}
		return seanBuffer.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		boolean result;
		if (o == null) {
			result = false;
		} else if (this == o) {
			result = true;
		} else if (!(o instanceof ConnectFourState)) {
			result = false;
		} else {
			ConnectFourState other = (ConnectFourState) o;
			result = Arrays.deepEquals(gameBoard, other.gameBoard);
		}
		return result;
	}
	
	@Override
	public int hashCode() {
		int result = 0;
		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[row].length; col++) {
				result += gameBoard[row][col];
				result = result << 7;
			}
		}
		return result;
	}

//	/**
//	 * Used for testing.
//	 * @param args Not used.
//	 */
//	public static void main(String[] args) {
//		char[][] testBoard = {{'X', '2', '3', '4', 'X', '6', '7'},
//							  {'X', 'X', '4', 'X', '6', '7', '5'}, 
//							  {'3', 'X', 'X', '6', '7', '5', '4'},
//							  {'4', 'X', 'X', '7', '5', '4', '3'},
//							  {'5', '6', '7', ' ', '4', '3', '2'},
//							  {'6', '7', '5', '4', '3', '2', '1'}};
//		
////		char[][] testBoard = {{' ', ' ', ' ', ' ', ' ', ' ', ' '},
////		  {' ', ' ', ' ', ' ', ' ', ' ', ' '}, 
////		  {' ', ' ', ' ', ' ', ' ', ' ', ' '},
////		  {' ', ' ', ' ', ' ', ' ', ' ', ' '},
////		  {' ', ' ', ' ', ' ', ' ', ' ', ' '},
////		  {' ', ' ', ' ', ' ', ' ', ' ', ' '}};
//		
//		ConnectFourState test = new ConnectFourState(testBoard);
//		System.out.println(test.didPlayerWin(GamePlayer.MAX));
//		System.out.println(test.toString());
//	}
}
