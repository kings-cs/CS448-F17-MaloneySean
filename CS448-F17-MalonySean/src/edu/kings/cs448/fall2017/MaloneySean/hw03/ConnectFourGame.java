package edu.kings.cs448.fall2017.MaloneySean.hw03;

import java.util.HashSet;
import java.util.Set;

/**
 * A game of Connect Four.
 * 
 * @author Sean Maloney
 * @version 2017
 */
public class ConnectFourGame implements 
		StrategyGame<ConnectFourState, ConnectFourAction> {

	/**
	 * The initial state (an empty board).
	 */
	private ConnectFourState initialState;

	/**
	 * Constructs a new TicTacToeGame.
	 */
	public ConnectFourGame() {
		initialState = new ConnectFourState();
	}
	
	@Override
	public ConnectFourState getInitialState() {
		return initialState;
	}

	@Override
	public Set<ConnectFourAction> getActions(ConnectFourState theState) {
		char playerSymbol = getPlayer(theState).getSymbol();
		
		Set<ConnectFourAction> actions = new HashSet<ConnectFourAction>();

		char[][] board = theState.getGameBoardCopy();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				char current = board[i][j];
				if (current == ' ') {
					actions.add(new ConnectFourAction(playerSymbol, i, j));
				}
			}
		}
		
		return actions;
	}

	@Override
	public ConnectFourState getResultingState(ConnectFourAction theAction, ConnectFourState theState) {
		ConnectFourState result = null;
		char[][] board = theState.getGameBoardCopy();
		board[theAction.getRow()][theAction.getCol()] = theAction.getSymbol();
		result = new ConnectFourState(board);
		return result;
	}

	@Override
	public boolean isTerminalState(ConnectFourState theState) {
		boolean result = false;
		if(theState.isFull() || theState.didPlayerWin(GamePlayer.MAX) || theState.didPlayerWin(GamePlayer.MIN)){
			result = true;
		}
		
		return result;
	}

	@Override
	public int getUtility(ConnectFourState theState) {
		int result = 0;
		if(theState.didPlayerWin(GamePlayer.MAX)){
			result = GamePlayer.MAX.getWinUtility();
		}
		else if(theState.didPlayerWin(GamePlayer.MIN)){
			result = GamePlayer.MIN.getWinUtility();			
		}
		return result;
	}

	@Override
	public GamePlayer getPlayer(ConnectFourState theState) {
		return theState.getCurrentPlayer();
	}

	@Override
	public int evaluateState(ConnectFourState state) {
		return state.evaluate();
	}

}
