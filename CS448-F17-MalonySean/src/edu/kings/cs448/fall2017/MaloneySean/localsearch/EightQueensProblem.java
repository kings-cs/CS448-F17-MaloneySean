package edu.kings.cs448.fall2017.MaloneySean.localsearch;

import java.util.Random;
import java.util.Set;

/**
 * Class to represent an Eight Queens problem.
 * @author Sean Maloney
 *
 */
public class EightQueensProblem implements HillClimbingProblem<EightQueensState>, GeneticProblem<EightQueensState> {

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
		Set<EightQueensState> result = theState.getChildren();
		
		return result;
	}

	//**************Genetic Problem Stuff*************************
	
	@Override
	public EightQueensState crossover(EightQueensState firstParent, EightQueensState secondParent) {
		// TODO Auto-generated method stub
		EightQueensState child = null;
		int seperator = rand.nextInt(8);
		
		char[][] childBoard = new char[8][8];
		char[][] parentOneBoard = firstParent.getBoardCopy();
		char[][] parentTwoBoard = secondParent.getBoardCopy();
		
		
		
		for(int i = 0; i < parentOneBoard.length; i++) {
			for(int j = 0; j < parentOneBoard[0].length; j++) {
				char current = parentOneBoard[j][i];
				
				if(i >= seperator) {
					current = parentTwoBoard[j][i];
				}
				
				childBoard[j][i] = current;
				
			}
		}

		child = new EightQueensState(childBoard);
		
	
		
		return child;
	}

	@Override
	public void mutate(EightQueensState theState) {
		// TODO Auto-generated method stub
		int column = rand.nextInt(8);
		int newRow = rand.nextInt(8);
		theState.mutateQueen(column, newRow);
		
//		if(this.getFitness(theState) < 0) {
//			System.out.println(theState.toString());
//		}
	}

//	public static void main(String[] args) {
//			EightQueensProblem test = new EightQueensProblem();
//			
//			System.out.println(test.getRandomState().toString());
//	}
}
