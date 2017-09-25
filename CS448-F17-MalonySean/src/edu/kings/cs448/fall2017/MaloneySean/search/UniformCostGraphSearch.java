package edu.kings.cs448.fall2017.MaloneySean.search;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map.Entry;

/**
 * Class used to find a solution to a problem using a Uniform Cost Graph Search.
 * @author Sean Maloney
 *
 * @param <S> The type of states in the problem.
 * @param <A> The type of actions in the problem.
 */
public class UniformCostGraphSearch<S, A> extends GraphSearch<S, A> {

	/** The collection of nodes that have not yet been explored. */
	private TreeMap<Integer, SearchNode<S, A>> frontier;
	
	/**
	 * A Map used to quickly search the same elements that are in the frontier based on their associated state.
	 */
	private HashMap<S, SearchNode<S,A>> frontierMap;
	
	/**
	 * Constructs a new DepthFirstGraphSearch.
	 */
	public UniformCostGraphSearch() {
		frontier = new TreeMap<>();
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
		
		int initalPathCost = initialNode.getPathCost();
		
		frontier.put(initalPathCost, initialNode);
		frontierMap.put(initialNode.getState(), initialNode);		
	}

	@Override
	public boolean isFrontierEmpty() {
		return frontier.isEmpty();
	}

	@Override
	public SearchNode<S, A> selectNode(SearchProblem<S, A> problem) {
		Entry<Integer, SearchNode<S, A>> firstEntry = frontier.firstEntry();
		SearchNode<S,A> result = firstEntry.getValue();
		
		//System.out.println(firstEntry.getKey());
		
		frontierMap.remove(result.getState());
		return result;
	}

	@Override
	public void addToFrontier(SearchNode<S, A> node) {
		int pathCost = node.getPathCost();
		//Put problem in field when frontier is initalized?
		System.out.println(pathCost);
		
		frontier.put(pathCost, node);
		frontierMap.put(node.getState(), node);	
	}

}
