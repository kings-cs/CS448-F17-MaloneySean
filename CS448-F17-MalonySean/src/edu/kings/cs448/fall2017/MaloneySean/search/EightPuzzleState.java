package edu.kings.cs448.fall2017.MaloneySean.search;

/**
 * A state in an Eight Puzzle.
 * @author Sean Maloney
 *
 */
public class EightPuzzleState {
	
	/**
	 * Two dimensional array representing the state of an EightPuzzle.
	 */
	private int[][] configuration;
	
	/**
	 * The X coordinate of the blank.
	 */
	private int blankX;
	
	/**
	 * The Y coordinate of the blank.
	 */
	private int blankY;
	
	/**
	 * Constructs an Eight Puzzle state based off of input of what tile is in each position.
	 * 
	 * @param zero The first position in the puzzle.
	 * @param one The second position in the puzzle.
	 * @param two The third position in the puzzle.
	 * @param three The fourth position in the puzzle.
	 * @param four The fifth position in the puzzle.
	 * @param five The sixth position in the puzzle.
	 * @param six The seventh position in the puzzle.
	 * @param seven The eighth position in the puzzle.
	 * @param eight The ninth position in the puzzle.
	 */
	public EightPuzzleState(int zero, int one, int two, int three, int four, int five, int six, int seven, int eight) {
		configuration = new int[3][3];
		
		configuration[0][0] = zero;
		configuration[0][1] = one;
		configuration[0][2] = two;
		configuration[1][0] = three;
		configuration[1][1] = four;
		configuration[1][2] = five;
		configuration[2][0] = six;
		configuration[2][1] = seven;
		configuration[2][2] = eight;
		
		findBlank();
	}

	/**
	 * Constructs a new Eight Puzzle State from an Array and X & Y coordinates for the blank.
	 * @param config The array.
	 * @param x The x coordinate of the blank.
	 * @param y The y coordinate of the blank.
	 */
	public EightPuzzleState(int[][] config, int x, int y) {
		configuration = config;
		blankX = x;
		blankY = y;
	}
	
	/**
	 * Private helper method used to find the blank in the current state.
	 */
	private void findBlank() {
		boolean foundBlank = false;
		int i = 0;
		int j = 0;
		
		while(!foundBlank && i < configuration.length) {
			while(!foundBlank && j < configuration[0].length) {
				if(configuration[i][j] == 0) {
					blankX = i;
					blankY = j;
					
					foundBlank = true;
				}
				j++;
			}
			i++;
		}
	}
	
	/**
	 * Gets the configuration of the state.
	 * 
	 * @return The set up of the state.
	 */
	public int[][] getConfiguration(){
		return configuration;
	}
	
	/**
	 * Gets the X coordinate of the blank.
	 * @return The X coordinate of the blank.
	 */
	public int getBlankX() {
		return blankX;
	}
	
	/**
	 * Gets the Y coordinate of the blank.
	 * @return The Y coordinate of the blank.
	 */
	public int getBlankY() {
		return blankY;
	}
	
	/**
	 * Represents the current state as a string listing the entries from left to right.
	 * 
	 * @return The formatted String.
	 */
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append(configuration[0][0]).append(" ");
		result.append(configuration[0][1]).append(" ");
		result.append(configuration[0][2]).append(" ");
		result.append(configuration[1][0]).append(" ");
		result.append(configuration[1][1]).append(" ");
		result.append(configuration[1][2]).append(" ");
		result.append(configuration[2][0]).append(" ");
		result.append(configuration[2][1]).append(" ");
		result.append(configuration[2][2]);
		
		return result.toString();
	}
	
	/**
	 * Determines whether two EightPuzzleStates are equal or not.
	 * 
	 * @param o The other state.
	 * @return Whether or not the states are equal.
	 */
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
				if(o instanceof EightPuzzleState) {
					EightPuzzleState otherState = (EightPuzzleState)o;
					int[][] otherConfig = otherState.getConfiguration();
					if(otherConfig.equals(configuration)){
						result = true;
					}
					else {
						result = false;
					}
						
				}
				else {
					result = false;
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Creates a unique hashcode for the given state.
	 * 
	 * @return The hashcode.
	 */
	public int hashCode() {
		int result = 0;
		
		result += configuration[0][0] * 100000000;
		result += configuration[0][1] * 10000000;
		result += configuration[0][2] * 1000000;
		result += configuration[1][0] * 100000;
		result += configuration[1][1] * 10000;
		result += configuration[1][2] * 1000;
		result += configuration[2][0] * 100;
		result += configuration[2][1] * 10;
		result += configuration[2][2];	
		
		return result;
	}
}
