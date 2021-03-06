package edu.kings.cs448.fall2017.MaloneySean.search;

import java.util.HashSet;
import java.util.Set;



/**
 * An Eight Puzzle Problem.
 * @author Sean Maloney
 */
public class EightPuzzleProblem  implements SearchProblem<EightPuzzleState, EightPuzzleAction> {

//	/**
//	 * The goal configuration of any EightPuzzleState.
//	 */
//	private static final int[][] GOAL_CONFIG = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
	
	
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
	 * Alternate constructor taking an array representing the puzzle and the coordinates of the blank.
	 * @param config The arrangment of the puzzle.
	 * @param x The x coordinate of the blank.
	 * @param y The y cooredinate of the blank.
	 */
	public EightPuzzleProblem(int[][] config, int x, int y) {
		initialState = new EightPuzzleState(config, x, y);
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

	/**
	 * Determines what the next state will be given a certain action and the current state.
	 * 
	 * @return The new State.
	 */
	@Override
	public EightPuzzleState getResultingState(EightPuzzleAction theAction, EightPuzzleState theState) {
		EightPuzzleState result = null;
		
		int[][] config = theState.getConfiguration();
		int oldX = theState.getBlankX();
		int oldY = theState.getBlankY();
		
		int newX = oldX;
		int newY = oldY;
		
		int[][] newConfig = new int[3][3];
		
		for(int i = 0; i < config.length; i++) {
			for(int j = 0; j < config[0].length; j++) {
				newConfig[i][j] = config[i][j];	
			}
		}
		
		if(theAction.equals(EightPuzzleAction.UP)) {
			newX--;
		}
		else if(theAction.equals(EightPuzzleAction.DOWN)) {
			newX++;
		}
		else if(theAction.equals(EightPuzzleAction.LEFT)) {
			newY--;
		}
		else { //RIGHT
			newY++;
		}
		
		//1 2 5 3 4 0 6 7 8
		int trav = newConfig[newX][newY];
		
		
		newConfig[newX][newY] = 0;
		newConfig[oldX][oldY] = trav;
		
		

		result = new EightPuzzleState(newConfig, newX, newY);
		
		

		
		return result;
	}

	/**
	 * Returns whether or not a given state meets the problem's goal.
	 * 
	 * @return Whether or not the given state is the goal.
	 */
	@Override
	public boolean meetsGoal(EightPuzzleState theState) {
		boolean result = false;
		
		
		if(theState.toString().equals("0 1 2 3 4 5 6 7 8")) {
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
		int heuristic = 0;
		
		int[][] stateConfiguration = theState.getConfiguration();
		
		if(stateConfiguration[0][0] != 0) {
			heuristic++;
		}
		if(stateConfiguration[0][1] != 1) {
			heuristic++;
		}
		if(stateConfiguration[0][2] != 2) {
			heuristic++;
		}
		if(stateConfiguration[1][0] != 3) {
			heuristic++;
		}
		if(stateConfiguration[1][1] != 4) {
			heuristic++;
		}
		if(stateConfiguration[1][2] != 5) {
			heuristic++;
		}
		if(stateConfiguration[2][0] != 6) {
			heuristic++;
		}
		if(stateConfiguration[2][1] != 7) {
			heuristic++;
		}
		if(stateConfiguration[2][2] != 8) {
			heuristic++;
		}
		
		return heuristic;
	}
	
//	@Override
//	public int heuristicForState(EightPuzzleState theState) {
//		//Just going to return 0 for now.
//		
//		int[][] stateConfiguration = theState.getConfiguration();
//		
//		int heuristic = 0;
//		
//		for(int i = 0; i < stateConfiguration.length; i++) {
//			for(int j = 0; j < stateConfiguration[0].length; j++) {
//				int current = stateConfiguration[i][j];
//				
//				int distanceAway = 0;
//				int xDist = 0;
//				int yDist = 0;
//				
//				if(current == 1) {
//					xDist = i - 0;
//					yDist = j - 1;
//					
//					distanceAway = xDist + yDist;
//				}
//				else if(current == 2) {
//					xDist = i - 0;
//					yDist = j - 2;
//					
//					distanceAway = xDist + yDist;
//				}
//				else if(current == 3) {
//					xDist = i - 1;
//					yDist = j - 0;
//					
//					distanceAway = xDist + yDist;
//				}
//				else if(current == 4) {
//					xDist = i - 1;
//					yDist = j - 1;
//					
//					distanceAway = xDist + yDist;
//				}
//				else if(current == 5) {
//					xDist = i - 1;
//					yDist = j - 2;
//					
//					distanceAway = xDist + yDist;
//				}
//				else if(current == 6) {
//					xDist = i - 2;
//					yDist = j - 0;
//					
//					distanceAway = xDist + yDist;
//				}
//				else if(current == 7) {
//					xDist = i - 2;
//					yDist = j - 1;
//					
//					distanceAway = xDist + yDist;
//				}
//				else if(current == 8) {
//					xDist = i - 2;
//					yDist = j - 2;
//					
//					distanceAway = xDist + yDist;
//				}
//				
//				heuristic += distanceAway;
//			}
//		}
//		
//
//		return heuristic;
//	}

}
