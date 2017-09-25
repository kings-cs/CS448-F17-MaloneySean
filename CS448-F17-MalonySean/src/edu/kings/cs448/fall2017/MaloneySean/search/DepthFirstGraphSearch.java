package edu.kings.cs448.fall2017.MaloneySean.search;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

/**
 * Class used to perform a DepthFirstSearch on a Graph.
 * @author Sean Maloney
 *
 * @param <S> The type of states in the problem.
 * @param <A> The type of actions in the problem.
 */
public class DepthFirstGraphSearch<S, A> extends GraphSearch<S, A> {

	/** The collection of nodes that have not yet been explored. */
	private Deque<SearchNode<S, A>> frontier;
	
	/**
	 * A Map used to quickly search the same elements that are in the frontier based on their associated state.
	 */
	private HashMap<S, SearchNode<S,A>> frontierMap;
	
	/**
	 * Constructs a new DepthFirstGraphSearch.
	 */
	public DepthFirstGraphSearch() {
		frontier = new ArrayDeque<>();
		frontierMap = new HashMap<>();
	}
	
	@Override
	public SearchNode<S, A> searchFrontier(S state) {
		SearchNode<S,A> result = frontierMap.get(state);
		return result;
	}

	@Override
	public void initializeFrontier(SearchProblem<S, A> problem) {
		SearchNode<S,A> initialNode = new SearchNode<S, A>(null, 0, problem.getInitialState(), null);
		frontier.push(initialNode);
		frontierMap.put(initialNode.getState(), initialNode);
		
	}

	@Override
	public boolean isFrontierEmpty() {
		return frontier.isEmpty();
	}

	@Override
	public SearchNode<S, A> selectNode(SearchProblem<S, A> problem) {
		SearchNode<S,A> result = frontier.pop();
		frontierMap.remove(result.getState());
		return result;
	}

	@Override
	public void addToFrontier(SearchNode<S, A> node) {
		frontier.push(node);
		frontierMap.put(node.getState(), node);	
	}

}
