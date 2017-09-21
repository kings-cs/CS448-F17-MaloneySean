package edu.kings.cs448.fall2017.MaloneySean.search;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Program to solve Eight Puzzle problems.
 * @author Sean Maloney
 */
public class EightPuzzleMain {

	/**
	 * The main method.
	 * @param args Not Used.
	 */
	public static void main(String[] args) {
		final double NANOSECONDS_PER_SECOND = 1000000000.0;
		int[][] config = new int[3][3];
		int x = 0;
		int y = 0;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		System.out.println("Input Eight Puzzle Start State: ");
		
		boolean validInput = true;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				int num = input.nextInt();
				
				if(num > 9 || num < 0) {
					validInput = false;
				}
				
				if(num == 0) {
					x = i;
					y = j;
				}
				
				config[i][j] = num;
			}
		}
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				System.out.print(config[i][j] + " ");
			}
			System.out.println();
		}
		
		if(!validInput) {
			System.out.println("Invalid Input For Initial State");
		}
		else {
			EightPuzzleProblem problem = new EightPuzzleProblem(config, x, y);
			SearchSolver<EightPuzzleState, EightPuzzleAction> solver = SearchUtilities.chooseAlgorithm(SearchUtilities.getAlgorithmMap());
			long startTime = System.nanoTime();
			ArrayList<EightPuzzleAction> solution = solver.solve(problem);
			long endTime = System.nanoTime();
			SearchUtilities.printSolution(solution);
			System.out.println("Solving this problem required expanding " + solver.getNumExpandedNodes() + " nodes and ran for " + (endTime - startTime) / NANOSECONDS_PER_SECOND + " seconds.");
		}
		
		
	}
}
