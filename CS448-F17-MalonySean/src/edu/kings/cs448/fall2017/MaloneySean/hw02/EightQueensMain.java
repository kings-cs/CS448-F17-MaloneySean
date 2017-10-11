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
		//***************Hill Climbing Test**********************
		System.out.println("Hill Climbing Search Test: ");
		System.out.println();
		HillClimbingSearch<EightQueensState> queenSearch = new HillClimbingSearch<EightQueensState>();

		

		int successes = 0;
		int fails = 0;
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
				fails++;
			}
		}
		
		if(fails == 0) {
			fails = 1;
		}
		if(successes == 0) {
			successes = 1;
		}
		
		System.out.println("No Sideways Steps Successes: " + successes);
		System.out.println("No Sideways Steps Average Moves For Success: " + (double) successMoves / successes);
		System.out.println("No Sideways Steps Average Moves For Failure: " + (double) failMoves / fails);
		
		
		successes = 0;
		fails = 0;
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
				fails++;
			}
		}
		
		if(fails == 0) {
			fails = 1;
		}
		if(successes == 0) {
			successes = 1;
		}
		
		System.out.println("100 Sideways Steps Successes: " + successes);
		System.out.println("100 Sideways Steps Average Moves For Success: " + (double) successMoves / successes);
		System.out.println("100 Sideways Steps Average Moves For Failure: " + (double) failMoves / fails);
	
	
		//***************Genetic Test**********************
		System.out.println();
		System.out.println("Gentic Search Test: ");
		System.out.println();
		GeneticSearch<EightQueensState> geneticTest = new GeneticSearch<EightQueensState>();
		EightQueensProblem geneticProblem = new EightQueensProblem();
		
		int generationsNeeded = 0;
		
		for(int i = 0; i < 10; i++) {
			geneticTest.solve(geneticProblem, 100, 5);
			generationsNeeded += geneticTest.getGenerationCount();
		}
		
		System.out.println("100 Generations 5% Mutation Average Generations Needed: " + generationsNeeded / 10);
		
		generationsNeeded = 0;
		
		for(int i = 0; i < 10; i++) {
			geneticTest.solve(geneticProblem, 100, 20);
			generationsNeeded += geneticTest.getGenerationCount();
		}
		
		System.out.println("100 Generations 20% Mutation Average Generations Needed: " + generationsNeeded / 10);
		
		generationsNeeded = 0;
		
		for(int i = 0; i < 10; i++) {
			geneticTest.solve(geneticProblem, 200, 20);
			generationsNeeded += geneticTest.getGenerationCount();
		}
		
		System.out.println("200 Generations 20% Mutation Average Generations Needed: " + generationsNeeded / 10);
		
		
		
		//***************Bogo Test**********************
		System.out.println();
		System.out.println("Bogo Test: ");
		System.out.println();
		
		BogoSearch<EightQueensState> bogoTest = new BogoSearch<EightQueensState>();
		EightQueensProblem bogoTestProblem = new EightQueensProblem();
	
		int bogoStates = 0;
		for(int i = 0; i < 10; i++) {
			bogoTest.solve(bogoTestProblem);
			bogoStates += bogoTest.getStatesGenerated();
		}
		
		System.out.println("Bogo Test Average: " + bogoStates / 10);
	}
}
