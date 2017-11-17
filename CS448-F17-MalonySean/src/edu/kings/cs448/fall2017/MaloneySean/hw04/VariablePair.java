package edu.kings.cs448.fall2017.MaloneySean.hw04;

/**
 * A class to store information for an ordered pair of variables, (xI, xJ).
 * @author Sean Maloney
 *
 */
public class VariablePair {
	
	/**
	 * The first variable in the pair (xI).
	 */
	private String varOne;
	
	/**
	 * The second variable in the prai (xJ).
	 */
	private String varTwo;
	
	/**
	 * Creates a VariablePair.
	 * @param one The value for varOne to be assigned.
	 * @param two The vale for varTwo to be assigned.
	 */
	public VariablePair(String one, String two) {
		varOne = one;
		varTwo = two;
	}


	/**
	 * Gets the the value of varOne.
	 * @return The value of varOne.
	 */
	public String getVarOne() {
		return varOne;
	}

	/**
	 * Gets the the value of varTwo.
	 * @return The value of varTwo.
	 */
	public String getVarTwo() {
		return varTwo;
	}
	
	@Override
	public boolean equals(Object other) {
		boolean checkOne = varOne.equals(((VariablePair) other).getVarOne());
		boolean checkTwo = varTwo.equals(((VariablePair) other).getVarTwo());
		
		return checkOne && checkTwo;
	}
}
