package edu.kings.cs448.fall2017.MaloneySean.hw02;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
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
		
		movesTaken = 0;
		int sideWays = 0;
		
		S current = problem.getRandomState();
		boolean moved = true;
		
		while(moved) {
			Set<S> neighbors = problem.getNeighbors(current);
		
			Set<S> bestSet = selectBestSet(neighbors, problem);
			S bestMember = bestSet.iterator().next();
			
			int currentFitness = problem.getFitness(current);
			int bestMemberFitness = problem.getFitness(bestMember);
			//int bestFitness = problem.getFitness(best);
//			if(bestMemberFitness > currentFitness) {
//				isBetter = isSetBetter(currentFitness, bestSet, problem);
//			}
			
			if(bestMemberFitness > currentFitness) {
				//current = bestSet.iterator().next();
				current = selectRandomState(bestSet);
				sideWays = 0;
			}
			else if(bestMemberFitness == currentFitness && sideWays < maxSidewaysMoves) {
				//current = bestSet.iterator().next();
				current = selectRandomState(bestSet);
				sideWays++;
			}
			else {
				moved = false;
			}
			
			movesTaken++;
		}
		
		return current;
	}
	
	/**
	 * Method to select a random state out of a given set.
	 * @param set The set of states.
	 * @return The randomly chosen state.
	 */
	@SuppressWarnings("unchecked")
	private S selectRandomState(Set<S> set) {
		S result = null;
		Random rand = new Random();
		
		int randomNum = rand.nextInt(set.size());
		 
		result = (S) set.toArray()[randomNum];
		
		
		return result;
	}
	
//	/**
//	 * Private helper method to determine if all the entries in the best set are better than the current state.
//	 * @param currentFitness The fitness of the current state.
//	 * @param best The set of the best neighbors.
//	 * @param problem The problem being solved.
//	 * @return Whether all members of the state are better.
//	 */
//	private boolean isSetBetter(int currentFitness, Set<S> best, HillClimbingProblem<S> problem) {
//		boolean result = true;
//		Iterator<S> iter = best.iterator();
//		
//		while(result == true && iter.hasNext()) {
//			S currentBest = iter.next();
//			int currentBestFitness = problem.getFitness(currentBest);
//
//			if(currentBestFitness == currentFitness) {
//				result = false;
//			}
//		}
//		
//		return result;
//	}
	
	/**
	 * Private helper method used to select the best state from a set of neighbors.
	 * @param neighbors The neighbors of the state.
	 * @param problem The problem to be solved.
	 * @return The best neighbor.
	 */
	private Set<S> selectBestSet(Set<S> neighbors, HillClimbingProblem<S> problem) {
		Iterator<S> iter = neighbors.iterator();
		Set<S> currentBest = new HashSet<S>();
		
		S bestNeighbour = iter.next();
		
		while(iter.hasNext()) {
			S nextNeighbour = iter.next();
			
			int currentFitness = problem.getFitness(bestNeighbour);
			int nextFitness = problem.getFitness(nextNeighbour);
			
			if(nextFitness > currentFitness) {
				bestNeighbour = nextNeighbour;
			}	
		}
		
		currentBest.add(bestNeighbour);
		int bestFitness = problem.getFitness(bestNeighbour);
		for(S current : neighbors) {
			int currentFitness = problem.getFitness(current);
			
			if(bestFitness == currentFitness) {
				currentBest.add(current);
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
