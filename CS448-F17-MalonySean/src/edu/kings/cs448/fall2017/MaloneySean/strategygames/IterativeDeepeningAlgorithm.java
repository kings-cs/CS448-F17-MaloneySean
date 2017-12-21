package edu.kings.cs448.fall2017.MaloneySean.strategygames;

//import java.util.Iterator;
//import java.util.Set;

/**
 * A class that plays games by choosing their actions through the Alpha Beta Pruning strategy in an interative manner.
 * 
 * @author Sean Maloney
 *
 * @param <S> The type of states used.
 * @param <A> The type of actions used.
 */
public class IterativeDeepeningAlgorithm<S, A> implements StrategyAlgorithm<S, A> {

	/** The number of states expanded the last time we chose an action. */
	private int count;
	
	/**
	 * The maximum amount of time the algorithm is allowed to run.
	 */
	private int maxTime;
	
	/**
	 * The total amount of time that has been currently used.
	 */
	private int totalTime;
	
	/**
	 * Constructs an IterativeDeepeningAlgorithm to run using a set amount of time.
	 * @param miliseconds The max amount of time the algorithm is allowed to run for.
	 */
	public IterativeDeepeningAlgorithm(int miliseconds) {
		maxTime = miliseconds;
	}
	
	@Override
	public A nextAction(StrategyGame<S, A> game, S state) {
		int depth = 1;
		boolean isSearching = true;
		
		A result = null;
		
		while(isSearching) {
			AlphaBetaAlgorithm<S, A> search = new AlphaBetaAlgorithm<S, A>(depth);
			long startTime = System.nanoTime();
			result = search.nextAction(game, state);
			
			

			long endTime = System.nanoTime();

			long timeTaken = endTime - startTime;

			double miliSeconds = timeTaken / 1000000.0;
			
			totalTime += miliSeconds;
			
			count += search.getStateCount();
			
			if(!search.didPreviousCutoff()) {
				isSearching = false;
			}
			else if(totalTime >= maxTime / 2) {
				isSearching = false;
			}
			

			
			depth++;
		}
		
		
		return result;
	}

	@Override
	public int getStateCount() {
		return count;
	}

}
