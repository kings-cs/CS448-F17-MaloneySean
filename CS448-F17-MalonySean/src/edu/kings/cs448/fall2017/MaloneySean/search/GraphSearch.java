package edu.kings.cs448.fall2017.MaloneySean.search;

/**
 * Class that will solve a problem based on a Graph based search.
 * @author Sean Maloney
 * @param <S> The type of states in the problem to solve.
 * @param <A> The type of actions in the problem to solve.
 */
public abstract class GraphSearch<S, A> extends SearchAlgorithm<S, A> {
	
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
		return null;
	}
}
