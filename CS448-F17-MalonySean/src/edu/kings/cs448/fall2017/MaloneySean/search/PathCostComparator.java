package edu.kings.cs448.fall2017.MaloneySean.search;

import java.util.Comparator;

/**
 * Comparator used to compare two SearchNodes based on their pathcost.
 * @author Sean Maloney
 *
 * @param <S> The type of States stored in the node.
 * @param <A> The type of Actions stored in the node.
 */
public class PathCostComparator<S, A> implements Comparator<SearchNode<S, A>> {

	@Override
	public int compare(SearchNode<S, A> o1, SearchNode<S, A> o2) {
		
		int costOne = o1.getPathCost();
		int costTwo = o2.getPathCost();
		
		 return costOne - costTwo;
	}


}
