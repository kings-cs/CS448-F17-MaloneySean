package edu.kings.cs448.fall2017.MaloneySean.strategygames;

/**
 * A program for playing Connect Four.
 * 
 * @author Sean Maloney
 */
public class ConnectFourMain {
	/**
	 * The main method.
	 * 
	 * @param args Unused.
	 */
	public static void main(String[] args) {
		System.out.println("Let's play a game of Connect Four!");
		
		StrategyAlgorithm<ConnectFourState, ConnectFourAction> firstPlayer = GameUtilities.chooseAlgorithm(1);
		StrategyAlgorithm<ConnectFourState, ConnectFourAction> secondPlayer = GameUtilities.chooseAlgorithm(2);
		ConnectFourGame game = new ConnectFourGame();
		ConnectFourState finalState = GameUtilities.playGame(game, firstPlayer, secondPlayer);

		if(finalState.didPlayerWin(GamePlayer.MAX)) {
			System.out.println(GamePlayer.MAX.getName() + " wins!");
			System.out.println("Final Utility: " + game.getUtility(finalState));
		}
		else if(finalState.didPlayerWin(GamePlayer.MIN)) {
			System.out.println(GamePlayer.MIN.getName() + " wins!");
			System.out.println("Final Utility: " + game.getUtility(finalState));
		}
		else {
			System.out.println("The game is a draw!");
		}

	}
}
