package edu.kings.cs448.fall2017.MaloneySean.hw04;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * A program to solve sudoku as a CSP.
 * 
 * @author Sean Maloney
 */
public class Sudoku {

	/**
	 * The length of the board.
	 */
	private static final int BOARD_LENGTH = 9;

	/**
	 * The width of the board.
	 */
	private static final int BOARD_WIDTH = 9;

	/**
	 * The main method.
	 * 
	 * @param args
	 *            Not Used.
	 */
	public static void main(String[] args) {

		String puzzle = SudokuPuzzles.EASY_PUZZLE;
		Scanner input = new Scanner(puzzle);

		ConstraintSatisfactionProblem csp = createProblem(input);

		CSPSolver search = new BacktrackingWithAC3AndHeuristics();

		long timeBefore = System.currentTimeMillis();
		Map<String, Object> assignment = search.solve(csp);
		System.out.println("That required " + (System.currentTimeMillis() - timeBefore) + " milliseconds.");

	

		if (assignment != null) {
			for (int i = 0; i < BOARD_LENGTH; i++) {
				for (int j = 0; j < BOARD_WIDTH; j++) {
					String currentVar = "C" + i + "_" + j;
					System.out.print(assignment.get(currentVar));
				}
				System.out.println();
			}
		}
		else {
			System.out.println("Assignment is null");
		}
	}

	/**
	 * Creates an instance of a Sudoku problem.
	 * 
	 * @param input
	 *            The starting state of the puzzle
	 * @return A CSP representing the given sudoku puzzle.
	 */
	public static ConstraintSatisfactionProblem createProblem(Scanner input) {
		Set<Object> domain = new HashSet<Object>();

		for (int i = 0; i < 10; i++) {
			// Possible values are digits 0-9.
			domain.add(i);
		}

		Map<String, Set<Object>> variables = new HashMap<>();

		String current = input.nextLine();

		for (int i = 0; i < BOARD_LENGTH; i++) {
			for (int j = 0; j < BOARD_WIDTH; j++) {
				// One Variable Per Cell In Matrix
				int index = (i * BOARD_LENGTH) + j;
				String currentCell = current.charAt(index) + "";
				String currentVar = "C" + i + "_" + j;
				if (currentCell.equals("0")) {
					variables.put(currentVar, new HashSet<Object>(domain));
				} else {
					int cellValue = Integer.parseInt(currentCell);
					Set<Object> varDomain = new HashSet<Object>();
					varDomain.add(cellValue);
					variables.put(currentVar, varDomain);
				}

				// System.out.println(currentVar + ": " + variables.get(currentVar).toString());
			}
		}

		Set<Constraint> constraints = new HashSet<>();
		
		//Create Row & Column Constraints
		for (int i = 0; i < BOARD_LENGTH; i++) {
			for (int j = 0; j < BOARD_WIDTH; j++) {
				String rowVarOne = "C" + i + "_" + j;
				String colVarOne = "C" + j + "_" + i;
				for (int k = 0; k < BOARD_LENGTH; k++) {
					String rowVarTwo = "C" + i + "_" + k;
					String colVarTwo = "C" + k + "_" + i;
					if (j != k) {
						// System.out.println(rowVarOne + " : " + rowVarTwo);
						// Add all the row constraints
						constraints.add(new BinaryDifferentConstraint(rowVarOne, rowVarTwo));
					}

					if (i != k && !colVarOne.equals(colVarTwo)) {
						//System.out.println(colVarOne + " : " + colVarTwo);
						// Add all the column constraints
						constraints.add(new BinaryDifferentConstraint(colVarOne, colVarTwo));
					}

				}
			}

		}
		
		//Create 3x3 Constraints
		
		for(int i = 0; i < BOARD_LENGTH; i++) {
			for(int j = 0; j < 3; j++) {
				int row = j + (i * 3) / BOARD_LENGTH;
				if(i > 2) {
					row += (i / 3) * 2;
				}
				for(int k = 0; k < 3; k++) {
					int col = k + (i * 3) % BOARD_LENGTH;
					
					System.out.println(row + " : " + col);
					
					
					for(int q = 0; q < 9; q++) {
						int r = q / 3;
						int c = q + (i * 3) % 3;
						
						//System.out.println(row + "_" + col + " : " + r + "_" + c % 3);
					}
					
				}
			}
			System.out.println();
			System.out.println("----------------");
			System.out.println();
		}

		 System.out.println(constraints.size());
		return new ConstraintSatisfactionProblem(variables, constraints);
	}
}
