package edu.kings.cs448.fall2017.MaloneySean.search;

import java.util.ArrayList;


/**
 * An algorithm that solves problems by using an IterativeDeepeningTreeSearch.
 * 
 * @author Sean Maloney
 * @version 2017
 * @param <S> The type of states in the problem.
 * @param <A> The type of actions in the problem.
 */
public class IterativeDeepeningTreeSearch<S, A> implements SearchSolver<S, A> {
	
	
	/**
	 * The Farthest Depth That The Algorithm is allowed to search.
	 */
	public static final int DEPTH_LIMIT = 32;

	/**
	 * The number of nodes that was expanded while searching for a solution.
	 */
	private int numExpandedNodes;

	@Override
	public int getNumExpandedNodes() {
		return numExpandedNodes;
	}
	
	/**
	 * Iteratively searches for solutions progressively going to deeper depths.
	 * @param problem The problem to be solved.
	 * @return The set of actions that leads to a solution or null if none is found.
	 */
	public ArrayList<A> iterativeDeepeningSearch(SearchProblem<S, A> problem){
		//Different class to implement search solver, create instances in for loop and call solve with depth limit
		
		numExpandedNodes = 0;
		
		ArrayList<A> result = null;
		int currentDepth = 0;
		boolean done = false;
		while(!done && currentDepth < DEPTH_LIMIT) {
			System.out.println(currentDepth);
			DepthFirstTreeSearch<S, A> currentSearch = new DepthFirstTreeSearch<S, A>();
			result = currentSearch.solve(problem, currentDepth);
			
			
			numExpandedNodes += currentSearch.getNumExpandedNodes();
			
			if(result != null) {
				done = true;
			}
			
			currentDepth++;
		}
		
		
	
		return result;
	}

	@Override
	public ArrayList<A> solve(SearchProblem<S, A> problem) {
		return iterativeDeepeningSearch(problem);
	}

}
