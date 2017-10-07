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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getFitness(EightQueensState theState) {
		// TODO: Does this return the number of attacking pairs?
		return theState.getQueensAttacking();
	}

	@Override
	public int getMaximumFitness() {
		//TODO: Does this only have to return 0?
		return 0;
	}

	@Override
	public Set<EightQueensState> getNeighbors(EightQueensState theState) {
		// TODO Auto-generated method stub
		return null;
	}

}
