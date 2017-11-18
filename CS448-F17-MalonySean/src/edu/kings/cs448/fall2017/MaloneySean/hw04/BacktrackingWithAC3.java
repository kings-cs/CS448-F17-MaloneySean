package edu.kings.cs448.fall2017.MaloneySean.hw04;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Class to check arc consistency on a CSP using the AC3 algorithm with back
 * tracking.
 * 
 * @author Sean Maloney
 */
public class BacktrackingWithAC3 extends SimpleBacktrackingSearch {

	@Override
	public boolean extraCheck(ConstraintSatisfactionProblem problem, Map<String, Object> assignment,
			String mostRecentVar) {
		boolean result = true;

//		 Set<Object> mostRecentDomain = problem.getDomains().get(mostRecentVar);
//		 mostRecentDomain.clear();
//		 mostRecentDomain.add(assignment.get(mostRecentVar));

		Set<VariablePair> pairs = new HashSet<>();

		addVariablesToP(problem.getDomains().keySet(), mostRecentVar, pairs);

		Iterator<VariablePair> iter = pairs.iterator();

		while (iter.hasNext() && result) {
			VariablePair current = iter.next();

			String xI = current.getVarOne();
			String xJ = current.getVarTwo();

			Set<Object> revisedSet = revise(problem, xI, xJ);
			if (!revisedSet.isEmpty()) {

				Set<Object> domain = problem.getDomains().get(xI);
				Iterator<Object> revisionIter = revisedSet.iterator();

				while (revisionIter.hasNext() && result) {
					Object currentObj = revisionIter.next();
					domain.remove(currentObj);

					if (domain.isEmpty()) {
						result = false;
					}
				}

				if (domain.isEmpty() == false) {
					addVariablesToP(problem.getDomains().keySet(), xI, pairs);
				}
			}
		}

		return true;
	}

	/**
	 * Private helper method to determine what elements need to be removed from the
	 * domain.
	 * 
	 * @param problem
	 *            The CSP.
	 * @param xI
	 *            The first variable to be checked.
	 * @param xJ
	 *            The second variable to be checked.
	 * @return The set of elements to be removed from the domain.
	 */
	public Set<Object> revise(ConstraintSatisfactionProblem problem, String xI, String xJ) {
		// boolean revised = false;
		Set<Object> toRemove = new HashSet<>();

		Set<Object> domainXi = problem.getDomains().get(xI);
		Set<Object> domainXj = problem.getDomains().get(xJ);

		for (Object x : domainXi) {
			boolean ok = false;

			Map<String, Object> assignment = new HashMap<>();
			assignment.put(xI, x);

			for (Object y : domainXj) {
				assignment.put(xJ, y);
				if (problem.isConsistent(assignment)) {
					ok = true;
				}

			}
			if (!ok) {
				toRemove.add(x);
			}

		}

		return toRemove;
	}

	/**
	 * Private helper to add variables to the set P.
	 * 
	 * @param variables
	 *            The set of variables in the CSP.
	 * @param secondVar
	 *            The variable to be the second part of the ordered pairs.
	 * @param pairs
	 *            The set containing the ordered pairs.
	 */
	private void addVariablesToP(Set<String> variables, String secondVar, Set<VariablePair> pairs) {
		// Set<VariablePair> pairs = new HashSet<VariablePair>();

		for (String current : variables) {
			if (!current.equals(secondVar)) {
				pairs.add(new VariablePair(current, secondVar));
			}
		}

		// return pairs;
	}
}
