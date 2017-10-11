package edu.kings.cs448.fall2017.MaloneySean.hw02;

import java.util.Set;

/**
 * Class to represent an Eight Queens problem.
 * @author Sean Maloney
 *
 */
public class EightQueensProblem implements HillClimbingProblem<EightQueensState> {

	@Override
	public EightQueensState getRandomState() {
		// TODO: Also how?
		return null;
	}

	@Override
	public int getFitness(EightQueensState theState) {
		int result = getMaximumFitness() - theState.getQueensAttacking();
		return result;
	}

	@Override
	public int getMaximumFitness() {
		return 28;
	}

	@Override
	public Set<EightQueensState> getNeighbors(EightQueensState theState) {
		// TODO: Hopefully doing this right
		return theState.getChildren();
	}

}
