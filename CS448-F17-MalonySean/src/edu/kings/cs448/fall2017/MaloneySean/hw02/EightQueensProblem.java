package edu.kings.cs448.fall2017.MaloneySean.hw02;

import java.util.Random;
import java.util.Set;

/**
 * Class to represent an Eight Queens problem.
 * @author Sean Maloney
 *
 */
public class EightQueensProblem implements HillClimbingProblem<EightQueensState> {

	/**
	 * Random used to generate numbers used in creating a random state.
	 */
	private Random rand;
	
	/**
	 * Default Constructor.
	 */
	public EightQueensProblem() {
		rand = new Random();
	}
	
	@Override
	public EightQueensState getRandomState() {
		// TODO: Also how?
		int[] rows = {rand.nextInt(8), rand.nextInt(8), rand.nextInt(8), rand.nextInt(8),
				rand.nextInt(8), rand.nextInt(8), rand.nextInt(8), rand.nextInt(8)};
		
		EightQueensState result = new EightQueensState(rows[0], rows[1], rows[2], rows[3], rows[4], rows[5], rows[6], rows[7]);
		
		return result;
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

//	public static void main(String[] args) {
//			EightQueensProblem test = new EightQueensProblem();
//			
//			System.out.println(test.getRandomState().toString());
//	}
}
