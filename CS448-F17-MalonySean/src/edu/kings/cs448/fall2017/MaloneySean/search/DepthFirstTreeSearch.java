package edu.kings.cs448.fall2017.MaloneySean.search;


import java.util.Stack;


/**
 * An algorithm that solves problems by searching through a tree in a depth-first manner.
 * 
 * @author Sean Maloney
 * @version 2017
 * @param <S> The type of states in the problem.
 * @param <A> The type of actions in the problem.
 */
public class DepthFirstTreeSearch<S, A> extends TreeSearch<S, A> {
	
	/**
	 * The collection of nodes that have not yet been explored.
	 */
	private Stack<SearchNode<S, A>> frontier;
	
	/**
	 * Constructs a new DepthFirstTreeSearch
	 */
	public DepthFirstTreeSearch() {
		frontier = new Stack<>();
	}
	
	@Override
	public void initializeFrontier(SearchProblem<S, A> problem) {
		frontier.push(new SearchNode<S, A>(null, 0, problem.getInitialState(), null));
	}

	@Override
	public boolean isFrontierEmpty() {
		boolean result = frontier.isEmpty();
		return result;
	}

	@Override
	public SearchNode<S, A> selectNode(SearchProblem<S, A> problem) {
		return frontier.pop();
	}

	@Override
	public void addToFrontier(SearchNode<S, A> node) {
		frontier.push(node);
	}

}
