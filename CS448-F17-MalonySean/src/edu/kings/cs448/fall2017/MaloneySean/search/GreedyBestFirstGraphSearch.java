package edu.kings.cs448.fall2017.MaloneySean.search;


import java.util.HashMap;
import java.util.TreeSet;

/**
 * Class used to perform a Greedy Best First Search on a Graph.
 * @author Sean Maloney
 *
 * @param <S> The type of states in the problem.
 * @param <A> The type of actions in the problem.
 */
public class GreedyBestFirstGraphSearch<S, A> extends GraphSearch<S, A> {
	
	/** The collection of nodes that have not yet been explored. */
	private TreeSet<SearchNode<S, A>> frontier;
	
	/**
	 * A Map used to quickly search the same elements that are in the frontier based on their associated state.
	 */
	private HashMap<S, SearchNode<S,A>> frontierMap;
	
	
	/**
	 * Constructs a new DepthFirstGraphSearch.
	 */
	public GreedyBestFirstGraphSearch() {
		frontier = null;
		frontierMap = new HashMap<>();
	}
	
	@Override
	public SearchNode<S, A> searchFrontier(S state) {
		SearchNode<S,A> result = frontierMap.get(state);
		return result;
	}

	@Override
	public void initializeFrontier(SearchProblem<S, A> problem) {
		frontier = new TreeSet<>(new HeuristicComparator<S, A>(problem));
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
		SearchNode<S,A> result = frontier.pollFirst();
		
		//System.out.println(firstEntry.getKey());
		
		frontierMap.remove(result.getState());
		return result;
	}

	@Override
	public void addToFrontier(SearchNode<S, A> node) {
		//Put problem in field when frontier is initalized?
		//1 2 0 3 7 8 4 5 6
	
		frontier.add(node);
		frontierMap.put(node.getState(), node);	
	}

}
