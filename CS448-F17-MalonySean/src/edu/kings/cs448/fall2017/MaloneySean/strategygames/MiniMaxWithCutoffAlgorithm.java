package edu.kings.cs448.fall2017.MaloneySean.strategygames;

import java.util.Set;

/**
 * A class that plays games by choosing their actions through the Minimax strategy with depth cut off.
 * 
 * @author Sean Maloney
 * @param <S> The type of states in the game.
 * @param <A> The type of actions in the game.
 */
public class MiniMaxWithCutoffAlgorithm<S, A> implements StrategyAlgorithm<S, A> {
	
	/** The number of states expanded the last time we chose an action. */
	private int count;
	
	/**
	 * The maximum depth to which the algorithm will expand before stopping.
	 */
	private int cutoffDepth;
	
	/**
	 * Constructor that takes in the depth to which the algorithm is allowed to expand.
	 * @param depth The cutoff depth.
	 */
	public 	MiniMaxWithCutoffAlgorithm(int depth) {
		
		if(depth == -1) {
			cutoffDepth = Integer.MAX_VALUE;
		}
		else {
			cutoffDepth = depth;
		}
	}
	
	@Override
	public A nextAction(StrategyGame<S, A> game, S state) {
		count = 0;
		A result = null;
		int max = 0;
		Set<A> actions = game.getActions(state);
		for (A action : actions) {
			if (result == null) {
				result = action;
				if (game.getPlayer(state) == GamePlayer.MAX) {
					max = minValue(game, game.getResultingState(result, state), 1);
				} else {
					max = maxValue(game, game.getResultingState(result, state), 1);
				}
			} else {
				if (game.getPlayer(state) == GamePlayer.MAX) {
					int current = minValue(game,
							game.getResultingState(action, state), 1);
					if (current > max) {
						result = action;
						max = current;
					}
				}
				else{
					int current = maxValue(game,
							game.getResultingState(action, state), 1);
					if (current < max) {
						result = action;
						max = current;
					}
				}
			}
		}
		return result;
	}

	/**
	 * Computes the Minimax value of a state, assuming that it is Player.MIN's turn.
	 * 
	 * @param game The game being played.
	 * @param state The state in question.
	 * @param currentDepth The current depth of exploration.
	 * @return The Minimax value of that state.
	 */
	private int minValue(StrategyGame<S, A> game, S state, int currentDepth) {
		int result = 0;
		count++;
		if (game.isTerminalState(state)) {
			result = game.getUtility(state);
		}
		else if(currentDepth >= cutoffDepth) {
			//TODO: ???
			result = game.evaluateState(state);
		}
		else {
			result = Integer.MAX_VALUE;
			Set<A> actions = game.getActions(state);
			for (A action : actions) {
				result = Math.min(result,
						maxValue(game, game.getResultingState(action, state), currentDepth + 1));
			}
		}

		return result;

	}

	/**
	 * Computes the Minimax value of a state, assuming that it is Player.MAX's turn.
	 * 
	 * @param game The game being played.
	 * @param state The state in question.
	 * @param currentDepth The current depth of exploration.
	 * @return The Minimax value of that state.
	 */
	private int maxValue(StrategyGame<S, A> game, S state, int currentDepth) {
		count++;
		int result = 0;
		if (game.isTerminalState(state)) {
			result = game.getUtility(state);			
		}
		else if(currentDepth >= cutoffDepth) {
			//TODO: ???
			result = game.evaluateState(state);
		}
		else {
			result = Integer.MIN_VALUE;
			Set<A> actions = game.getActions(state);
			for (A action : actions) {
				result = Math.max(result,
						minValue(game, game.getResultingState(action, state), currentDepth + 1));
			}
		}
		return result;

	}

	@Override
	public int getStateCount() {
		return count;
	}

}
