package edu.kings.cs448.fall2017.MaloneySean.hw04;

public class VariablePair {
	
	private String varOne;
	
	private String varTwo;
	
	
	public VariablePair(String one, String two) {
		varOne = one;
		varTwo = two;
	}


	public String getVarOne() {
		return varOne;
	}


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
