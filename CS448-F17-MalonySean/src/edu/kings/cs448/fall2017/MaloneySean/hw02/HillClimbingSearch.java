package edu.kings.cs448.fall2017.MaloneySean.hw02;

import java.util.Iterator;
import java.util.Set;

/**
 * Class used to set up for a HillClimbingSearch.
 * @author Sean Maloney
 *
 * @param <S> The type of states.
 */
public class HillClimbingSearch<S> {
	
	/**
	 * The amount of moves taken in a hill climb.
	 */
	private int movesTaken;
	
	/**
	 * Attempts to solve a problem, returning the best state it finds.
	 * 
	 * @param problem The problem to be solved.
	 * @param maxSidewaysMoves The farthest distance it will go before stopping.
	 * @return THe best state it finds.
	 */
	public S solve(HillClimbingProblem<S> problem, int maxSidewaysMoves) {
		S best = null;
		movesTaken = 0;
		
		S current = problem.getRandomState();
		boolean moved = true;
		
		while(moved) {
			Set<S> neighbors = problem.getNeighbors(current);
		
			
			best = selectBest(neighbors, problem);
			
			int currentFitness = problem.getFitness(current);
			int bestFitness = problem.getFitness(best);
			
			if(bestFitness > currentFitness) {
				current = best;
			}
			else if(bestFitness == currentFitness && getMovesTaken() < maxSidewaysMoves) {
				current = best;
			}
			else {
				moved = false;
			}
			movesTaken++;
		}
		
		return best;
	}
	
	/**
	 * Private helper method used to select the best state from a set of neighbors.
	 * @param neighbors The neighbors of the state.
	 * @param problem The problem to be solved.
	 * @return The best neighbor.
	 */
	private S selectBest(Set<S> neighbors, HillClimbingProblem<S> problem) {
		Iterator<S> iter = neighbors.iterator();
		S currentBest = iter.next();
		
		while(iter.hasNext()) {
			S next = iter.next();
			
			int currentFitness = problem.getFitness(currentBest);
			int nextFitness = problem.getFitness(next);
			
			if(nextFitness > currentFitness) {
				currentBest = next;
			}
			
		}
		
		return currentBest;
	}
	
	/**
	 * Returns the number of moves from the last time a problem was solved.
	 * 
	 * @return The number of moves.
	 */
	public int getMovesTaken() {
		int result = movesTaken;
		return result;
	}
}
