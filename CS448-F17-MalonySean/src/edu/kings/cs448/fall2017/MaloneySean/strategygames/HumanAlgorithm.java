package edu.kings.cs448.fall2017.MaloneySean.strategygames;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

/**
 * Class that allows a human to play a game through user input.
 * @author Sean Maloney	
 *
 * @param <S> The kind of states used by the algorithm.
 * @param <A> The kinds of actions used by the algorithm.
 */
public class HumanAlgorithm<S, A> implements StrategyAlgorithm<S, A> {

	/**
	 * Scanner used so a human player can give input for the problem.
	 */
	private Scanner input;
	
	/**
	 * Default constructor.
	 */
	public HumanAlgorithm() {
		input = new Scanner(System.in);
	}
	
	@Override
	public A nextAction(StrategyGame<S, A> game, S state) {
		// TODO: HANDLE INPUT MISMATCH
		Set<A> actions = game.getActions(state);
		ArrayList<A> orderedActions = new ArrayList<A>(actions);
		
		int choice = -1;
		boolean goodInput = false;
		while(goodInput == false) {
			while(choice < 0 || choice >= orderedActions.size()) {
				System.out.println("Available actions:");
				for(int i = 0; i < orderedActions.size(); i++) {
					System.out.println(i + ":\t" + orderedActions.get(i));
				}
				System.out.print("Your choice: ");




				try {
					choice = input.nextInt();

					goodInput = true;

				}
				catch(InputMismatchException ime) {
					System.out.println("Input Must Be A Number!");
					input.next();
				}
			}
		}
		
		
		A action = orderedActions.get(choice);
		
		return action;
	}

	@Override
	public int getStateCount() {
		return 0;
	}

}
