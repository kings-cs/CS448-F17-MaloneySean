package edu.kings.cs448.fall2017.MaloneySean.search;

import java.util.Comparator;

/**
 * Comparator used to compare two SearchNodes based on their heuristic..
 * @author Sean Maloney
 *
 * @param <S> The type of States stored in the node.
 * @param <A> The type of Actions stored in the node.
 */
public class HeuristicComparator<S, A> implements Comparator<SearchNode<S, A>> {

	/**
	 * The problem that is going to be solved.
	 */
	private SearchProblem<S, A> startProblem;
	
	/**
	 * Constructs a new Heuristic Comparator using a given problem.
	 * @param problem THe problem used to calculated heuristics.
	 */
	public HeuristicComparator(SearchProblem<S, A> problem) {
		startProblem = problem;
	}
	
	@Override
	public int compare(SearchNode<S, A> o1, SearchNode<S, A> o2) {
		
		int heuresticOne = startProblem.heuristicForState(o1.getState());
		int heuresticTwo = startProblem.heuristicForState(o2.getState());
		
		return heuresticOne - heuresticTwo;
	}

}
