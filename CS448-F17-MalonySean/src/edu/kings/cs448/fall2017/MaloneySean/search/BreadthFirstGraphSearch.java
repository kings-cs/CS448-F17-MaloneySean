package edu.kings.cs448.fall2017.MaloneySean.search;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class used to perform a BreadthFirstSearch on a Graph.
 * @author Sean Maloney
 *
 * @param <S> The type of states in the problem.
 * @param <A> The type of actions in the problem.
 */
public class BreadthFirstGraphSearch<S, A> extends GraphSearch<S, A> {

	/** The collection of nodes that have not yet been explored. */
	private Queue<SearchNode<S, A>> frontier;
	
	/**
	 * A Map used to quickly search the same elements that are in the frontier based on their associated state.
	 */
	private HashMap<S, SearchNode<S,A>> frontierMap;
	
	/**
	 * Constructs a new BreadthFirstGraphSearch.
	 */
	public BreadthFirstGraphSearch() {
		frontier = new LinkedList<>();
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
		frontier.add(initialNode);
		frontierMap.put(initialNode.getState(), initialNode);
		
	}

	@Override
	public boolean isFrontierEmpty() {
		return frontier.isEmpty();
	}

	@Override
	public SearchNode<S, A> selectNode(SearchProblem<S, A> problem) {
		SearchNode<S,A> result = frontier.poll();
		frontierMap.remove(result.getState());
		return result;
	}

	@Override
	public void addToFrontier(SearchNode<S, A> node) {
		frontier.add(node);
		frontierMap.put(node.getState(), node);	
	}

}
