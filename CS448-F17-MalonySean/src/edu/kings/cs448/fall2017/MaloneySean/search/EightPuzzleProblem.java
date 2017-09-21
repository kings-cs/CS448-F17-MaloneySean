package edu.kings.cs448.fall2017.MaloneySean.search;

import java.util.Set;

/**
 * An Eight Puzzle Problem.
 * @author Sean Maloney
 */
public class EightPuzzleProblem  implements SearchProblem<EightPuzzleState, EightPuzzleAction>{

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EightPuzzleState getResultingState(EightPuzzleAction theAction, EightPuzzleState theState) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean meetsGoal(EightPuzzleState theState) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getStepCost(EightPuzzleAction theAction, EightPuzzleState theState) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int heuristicForState(EightPuzzleState theState) {
		// TODO Auto-generated method stub
		return 0;
	}

}
