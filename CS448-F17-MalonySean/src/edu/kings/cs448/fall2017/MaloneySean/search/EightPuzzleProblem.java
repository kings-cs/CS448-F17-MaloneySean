package edu.kings.cs448.fall2017.MaloneySean.search;

import java.util.HashSet;
import java.util.Set;

/**
 * An Eight Puzzle Problem.
 * @author Sean Maloney
 */
public class EightPuzzleProblem  implements SearchProblem<EightPuzzleState, EightPuzzleAction> {

	/**
	 * The goal configuration of any EightPuzzleState.
	 */
	private static final int[][] GOAL_CONFIG = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
	
	
	/**
	 * The initial state of the problem.
	 */
	private EightPuzzleState initialState;
	
	
	/**
	 * Constructs an Eight Puzzle problem.
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
	public EightPuzzleProblem(int zero, int one, int two, int three, int four, 
			int five, int six, int seven, int eight) {
		
		initialState = new EightPuzzleState(zero, one, two, three, four,
				five, six, seven, eight);
	}
	
	/**
	 * Gets the initial state of the problem.
	 * 
	 * @return The initial state.
	 */
	@Override
	public EightPuzzleState getInitialState() {
		return initialState;
	}

	@Override
	public Set<EightPuzzleAction> getActions(EightPuzzleState theState) {
		Set<EightPuzzleAction> actions = new HashSet<EightPuzzleAction>();
		
		int x = theState.getBlankX();
		int y = theState.getBlankY();
		
		if(x != 0) {
			actions.add(EightPuzzleAction.UP);
		}
		if(x != 2) {
			actions.add(EightPuzzleAction.DOWN);
		}
		if(y != 0) {
			actions.add(EightPuzzleAction.LEFT);
		}
		if(y != 2) {
			actions.add(EightPuzzleAction.RIGHT);
		}
		
		
		return actions;
	}

	@Override
	public EightPuzzleState getResultingState(EightPuzzleAction theAction, EightPuzzleState theState) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns whether or not a given state meets the problem's goal.
	 * 
	 * @return Whether or not the given state is the goal.
	 */
	@Override
	public boolean meetsGoal(EightPuzzleState theState) {
		boolean result = false;
		if(theState.getConfiguration().equals(GOAL_CONFIG)) {
			result = true;
		}
		
		return result;
	}

	/**
	 * Returns the cost of a given action in the current state.
	 * 
	 * @return The cost of the action.
	 */
	@Override
	public int getStepCost(EightPuzzleAction theAction, EightPuzzleState theState) {
		return 1;
	}

	
	@Override
	public int heuristicForState(EightPuzzleState theState) {
		//Just going to return 0 for now.
		return 0;
	}

}
