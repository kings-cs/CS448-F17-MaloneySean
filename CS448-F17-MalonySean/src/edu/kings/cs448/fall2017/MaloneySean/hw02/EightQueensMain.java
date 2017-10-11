package edu.kings.cs448.fall2017.MaloneySean.hw02;

/**
 * Class to solve EightQueens Problems.
 * 
 * @author Sean Maloney
 *
 */
public class EightQueensMain {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            Not Used.
	 */
	public static void main(String[] args) {
		HillClimbingSearch<EightQueensState> queenSearch = new HillClimbingSearch<EightQueensState>();

		

		int successes = 0;
		int successMoves = 0;
		int failMoves = 0;

		for (int i = 0; i < 1000; i++) {
			EightQueensProblem testProblem = new EightQueensProblem();
			EightQueensState result = queenSearch.solve(testProblem, 0);
			
			int resultFitness = testProblem.getFitness(result);
			if (resultFitness == testProblem.getMaximumFitness()) {
				successes++;
				successMoves += queenSearch.getMovesTaken();
			} else {
				failMoves += queenSearch.getMovesTaken();
			}
		}
		
		System.out.println("No Sideways Steps Successes: " + successes);
		System.out.println("No Sideways Steps Average Moves For Success: " + (double) successMoves / 1000);
		System.out.println("No Sideways Steps Average Moves For Failure: " + (double) failMoves / 1000);
		
		
		successes = 0;
		successMoves = 0;
	    failMoves = 0;
		
		for (int i = 0; i < 1000; i++) {
			EightQueensProblem testProblem = new EightQueensProblem();
			EightQueensState result = queenSearch.solve(testProblem, 100);
			
			int resultFitness = testProblem.getFitness(result);
			if (resultFitness == testProblem.getMaximumFitness()) {
				successes++;
				successMoves += queenSearch.getMovesTaken();
			} else {
				failMoves += queenSearch.getMovesTaken();
			}
		}
		
		System.out.println("100 Sideways Steps Successes: " + successes);
		System.out.println("100 Sideways Steps Average Moves For Success: " + (double) successMoves / 1000);
		System.out.println("100 Sideways Steps Average Moves For Failure: " + (double) failMoves / 1000);
	}
}
