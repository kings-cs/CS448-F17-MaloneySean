package edu.kings.cs448.fall2017.MaloneySean.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

/**
 * An algorithm that solves problems by using an IterativeDeepeningTreeSearch.
 * 
 * @author Sean Maloney
 * @version 2017
 * @param <S> The type of states in the problem.
 * @param <A> The type of actions in the problem.
 */
public class IterativeDeepeningTreeSearch<S, A> extends TreeSearch<S, A> {
	
	/**
	 * Limit for how deep the iterative search is allowed to go.
	 */
	private static final int DEPTH_LIMIT = 100;
	
	/**
	 * The collection of nodes that have not yet been explored.
	 */
	private Stack<SearchNode<S, A>> frontier;
	
	/**
	 * Constructs a new DepthFirstTreeSearch.
	 */
	public IterativeDeepeningTreeSearch() {
		frontier = new Stack<>();
	}
	
	@Override
	public void initializeFrontier(SearchProblem<S, A> problem) {
		frontier = new Stack<>();
	}

	@Override
	public boolean isFrontierEmpty() {
		return frontier.isEmpty();
	}

	@Override
	public SearchNode<S, A> selectNode(SearchProblem<S, A> problem) {
		return frontier.pop();
	}

	@Override
	public void addToFrontier(SearchNode<S, A> node) {
		frontier.add(node);
	}
	
	/**
	 * Performs a DepthFirstSearch that will only go as a deep as a specified limit.
	 * @param problem The problem to be solved.
	 * @param limit The depth limit.
	 * @return The set of actions that leads to a solution or null if none is found.
	 */
	public ArrayList<A> depthLimitedSearch(SearchProblem<S, A> problem, int limit) {
		SearchNode<S, A> start = new SearchNode<S, A>(null, 0, problem.getInitialState(), null);
		
		return recursiveDLS(start, problem, limit);
	}
	
	/**
	 * Performs a recursive depth limited search.
	 * @param node The current node.
	 * @param problem The problem to be solved.
	 * @param limit The depth limit.
	 * @return The set of actions that leads to a solution or null if none is found. 
	 */
	public ArrayList<A> recursiveDLS(SearchNode<S, A> node, SearchProblem<S, A> problem, int limit) {
		ArrayList<A> solution = null;
		
		if(problem.meetsGoal(node.getState())) {
			solution = generateSolution(node);
		}
		else if(limit == 0) {
			//Represent cut off as an empty solution?
			solution = new ArrayList<A>();
		}
		else {
			
			boolean cutoffOccured = false;
			
			
			S currentState = node.getState();
			Set<A> actions = problem.getActions(node.getState());
			Iterator<A> iter = actions.iterator();
			while(!cutoffOccured && iter.hasNext()) {
				A action = iter.next();
				S nextState = problem.getResultingState(action, currentState);
				SearchNode<S, A> child = new SearchNode<>(node,
						node.getPathCost() + problem.getStepCost(action, currentState),
						nextState, action);
				
				ArrayList<A> result = recursiveDLS(child, problem, limit - 1);
				if(result.isEmpty()) {
					cutoffOccured = true;
				}
				else if(result != null) {
					//Do nothing
				}
			}
			
		}
		
		return solution;
	}

	/**
	 * Iteratively searches for solutions progressively going to deeper depths.
	 * @param problem The problem to be solved.
	 * @return The set of actions that leads to a solution or null if none is found.
	 */
	public ArrayList<A> iterativeDeepingSearch(SearchProblem<S, A> problem){
		ArrayList<A> result = null;
		int count = 0;
		boolean done = false;
		
		while(!done && count < DEPTH_LIMIT) {
			result = depthLimitedSearch(problem, count);
			
			if(result != null) {
				if(!result.isEmpty()) {
					done = true;
				}
			}

			count++;
		}
		
		
	
		return result;
	}
	
}
