package edu.kings.cs448.fall2017.MaloneySean.search;

import java.util.HashMap;

/**
 * Class that will solve a problem based on a Graph based search.
 * @author Sean Maloney
 * @param <S> The type of states in the problem to solve.
 * @param <A> The type of actions in the problem to solve.
 */
public abstract class GraphSearch<S, A> extends SearchAlgorithm<S, A> {
	
	/**
	 * The Set of states that have already been explored.
	 */
	private HashMap<S, SearchNode<S,A>> exploredSet;
	

	
	/**
	 * Abstract method to search the frontier for a node.
	 * 
	 * @param state The state to be searched to.
	 * @return The node with the corresponding state.
	 */
	public abstract SearchNode<S, A> searchFrontier(S state);
	
	/**
	 * Searches the explored set for the node.
	 * 
	 * @param state The state to be searched for.
	 * @return The node with the corresponding state.
	 */
	public SearchNode<S, A> searchExplored(S state) {
		return exploredSet.get(state);
	}

	@Override
	public void initializeExplored() {
		exploredSet = new HashMap<S, SearchNode<S,A>>();
	}

	@Override
	public void processChild(SearchNode<S, A> node) {
		
		SearchNode<S, A> exploredNode = exploredSet.get(node);
		if(exploredNode == null) {
			SearchNode<S, A> frontierNode = searchFrontier(node.getState());
			if(frontierNode != null) {
				//Is in the Frontier
				int currentPathCost = frontierNode.getPathCost();
				int newPathCost = node.getPathCost();
				
				if(newPathCost < currentPathCost) {
					frontierNode.setParent(node.getParent());
					frontierNode.setAction(node.getAction());
					frontierNode.setPathCost(node.getPathCost());
				}
			}
			else {
				//In Neither Frontier Nor Explored
				addToFrontier(node);
			}
		}
		else {
			//Is in the explored set
			int currentPathCost = exploredNode.getPathCost();
			int newPathCost = node.getPathCost();
			
			if(newPathCost < currentPathCost) {
				exploredNode.setParent(node.getParent());
				exploredNode.setAction(node.getAction());
				exploredNode.setPathCost(node.getPathCost());
			}
		}		
	}

	@Override
	public void addToExplored(SearchNode<S, A> node) {
		S state = node.getState();
		exploredSet.put(state, node);
	}
	

}
