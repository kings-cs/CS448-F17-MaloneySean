package edu.kings.cs448.fall2017.MaloneySean.csps;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Class to check arc consistency on a CSP using the AC3 algorithm with back
 * tracking and a heuristic.
 * 
 * @author Sean Maloney
 */
public class BacktrackingWithAC3AndHeuristics extends BacktrackingWithAC3 {

	@Override
	public String chooseUnassignedVariable(ConstraintSatisfactionProblem problem, Map<String, Object> assignment) {
		String result = "";
		
		result = mrv(problem, assignment);

		return result;
	}

	/**
	 * Private helper method to select an unassigned variable based on the Minimum Remaining Value Heuristic, ties are broken using the
	 * Maximum Degree Value Heuristic.
	 * @param problem The CSP in question.
	 * @param assignment The current assignments.
	 * @return The next variable to be used.
	 */
	private String mrv(ConstraintSatisfactionProblem problem, Map<String, Object> assignment) {
		String result = "";

		Map<String, Set<Object>> domains = problem.getDomains();
		Set<String> variables = domains.keySet();
		Set<String> assignmentKeys = assignment.keySet();

		Set<Object> currentSmallest = null;

		for (String current : variables) {
			if (!assignmentKeys.contains(current)) {
				Set<Object> currentDomain = domains.get(current);

				if (currentSmallest == null) {
					currentSmallest = currentDomain;
					result = current;
				} else {
					if (currentDomain.size() < currentSmallest.size()) {
						currentSmallest = currentDomain;
						result = current;
					} else if (currentDomain.size() == currentSmallest.size()) {
						String tieBreak = mdh(problem, result, current);
						if (tieBreak != result) {
							result = current;
							currentSmallest = currentDomain;
						}
					}
				}
			}
		}

		return result;
	}

	/**
	 * Private helper method to choose between two unassigned variables using the Maximum Degree Heuristic.
	 * @param problem The CSP in question.
	 * @param one The first variable.
	 * @param two The second variable.
	 * @return Whichever variable is better.
	 */
	private String mdh(ConstraintSatisfactionProblem problem, String one, String two) {
		String result = one;

		Set<Constraint> constraints = problem.getConstraints();
		Iterator<Constraint> iter = constraints.iterator();

		int countOne = 0;
		int countTwo = 0;

		while (iter.hasNext()) {
			Constraint currentConstraint = iter.next();

			if (currentConstraint.doesScopeContain(one)) {
				countOne++;
			}
			if (currentConstraint.doesScopeContain(two)) {
				countTwo++;
			}

		}

		if (countTwo > countOne) {
			result = two;
		}

		return result;
	}

}
