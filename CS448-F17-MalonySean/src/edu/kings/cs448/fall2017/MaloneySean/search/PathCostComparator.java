package edu.kings.cs448.fall2017.MaloneySean.search;

import java.util.Comparator;

public class PathCostComparator<S, A> implements Comparator<SearchNode<S, A>>{

	@Override
	public int compare(SearchNode o1, SearchNode o2) {
		
		int costOne = o1.getPathCost();
		int costTwo = o2.getPathCost();
		
		 return costOne - costTwo;
	}


}
