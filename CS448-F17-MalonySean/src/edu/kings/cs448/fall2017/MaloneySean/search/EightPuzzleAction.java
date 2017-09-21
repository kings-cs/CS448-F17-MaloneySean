package edu.kings.cs448.fall2017.MaloneySean.search;

/**
 * An action in the Eight Puzzle.
 * @author Sean Maloney
 */
public enum EightPuzzleAction {	
	/**
	 * Move the blank to the left.
	 */
	LEFT("left"),
	
	/**
	 * Move the blank to the right.
	 */
	RIGHT("right"),
	
	/**
	 * Move the blank up.
	 */
	UP("up"),
	
	/**
	 * Move the blank down.
	 */
	DOWN("down");
	
	/** The name of this EightPuzzleAction. */
	private String name;
	
	/**
	 * Constructs a new EightPuzzleAction.
	 * 
	 * @param theName The name of the action.
	 */
	private EightPuzzleAction(String theName) {
		name = theName;
	}
	
	/**
	 * Returns the name of the action.
	 */
	@Override
	public String toString() {
		String result = "Action: " + name;
		return result;
	}
	
}
