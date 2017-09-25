package edu.kings.cs448.fall2017.MaloneySean.search;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map.Entry;

/**
 * Class used to find a solution to a problem using an A* Search on a Graph.
 * @author Sean Maloney
 *
 * @param <S> The type of states in the problem.
 * @param <A> The type of actions in the problem.
 */
public class AStarGraphSearch<S, A> extends GraphSearch<S, A> {

	/** The collection of nodes that have not yet been explored. */
	private TreeMap<Integer, SearchNode<S, A>> frontier;
	
	/**
	 * A Map used to quickly search the same elements that are in the frontier based on their associated state.
	 */
	private HashMap<S, SearchNode<S,A>> frontierMap;
	
	/**
	 * The problem that is going to be solved.
	 */
	private SearchProblem<S, A> startProblem;
	
	/**
	 * Constructs a new DepthFirstGraphSearch.
	 */
	public AStarGraphSearch() {
		frontier = new TreeMap<>();
		frontierMap = new HashMap<>();
		startProblem = null;
	}
	
	@Override
	public SearchNode<S, A> searchFrontier(S state) {
		SearchNode<S,A> result = frontierMap.get(state);
		return result;
	}

	@Override
	public void initializeFrontier(SearchProblem<S, A> problem) {
		SearchNode<S,A> initialNode = new SearchNode<S, A>(null, 0, problem.getInitialState(), null);
		
		int initalKey = problem.heuristicForState(initialNode.getState()) + initialNode.getPathCost();
		
		frontier.put(initalKey, initialNode);
		frontierMap.put(initialNode.getState(), initialNode);	
		
		startProblem = problem;
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
		int keyValue = startProblem.heuristicForState(node.getState()) + node.getPathCost();
		//Put problem in field when frontier is initalized?
		
		frontier.put(keyValue, node);
		frontierMap.put(node.getState(), node);	
	}

}
