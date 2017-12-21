package edu.kings.cs448.fall2017.MaloneySean.strategygames;

/**
 * An action in the game of Connect Four.
 * 
 * @author Sean Maloney
 * @version 2017
 */
public class ConnectFourAction {
	
	/** The symbol to be placed on the board. */
	private char symbol;
	/** The row to put it in. */
	private int row;
	/** The column to put it in. */
	private int col;
	
	/**
	 * Constructs a new Connect Four.
	 * 
	 * @param theSymbol The symbol.
	 * @param theRow The row.
	 * @param theCol The column.
	 */
	public ConnectFourAction(char theSymbol, int theRow, int theCol) {
		symbol = theSymbol;
		row = theRow;
		col = theCol;
	}

	/**
	 * Gets the symbol.
	 * 
	 * @return The symbol.
	 */
	public char getSymbol() {
		return symbol;
	}

	/**
	 * Gets the row.
	 * 
	 * @return The row.
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Gets the column.
	 * 
	 * @return The column.
	 */
	public int getCol() {
		return col;
	}
	
	@Override
	public String toString() {
		return symbol + " in row " + row + ", column " + col;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean result;
		if(o == null) {
			result = false;
		}
		else if(this == o) {
			result = true;
		}
		else if(!(o instanceof ConnectFourAction)) {
			result = false;
		}
		else {
			ConnectFourAction other = (ConnectFourAction)o;
			result = (symbol == other.symbol && row == other.row && col == other.col);
		}
		return result;
	}
	
	@Override
	public int hashCode() {
		return (symbol * 100) + (row * 10) + col;
	}
}
