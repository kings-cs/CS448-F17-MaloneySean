package edu.kings.cs448.fall2017.MaloneySean.hw02;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * Class used to solve problems with a Genetic Search.
 * @author Sean Maloney
 *
 * @param <S> The kinds of states.
 */
public class GeneticSearch<S> {
	
	/**
	 * Random to generate numbers for parent selection.
	 */
	private Random rand;
	
	/**
	 * The amount of generations generated in the last call to solve.
	 */
	private int generationCount;
	
	/**
	 * Default constructor.
	 */
	public GeneticSearch() {
		rand = new Random();
	}
	
	/**
	 * Attempts to solve a problem using a Genetic Search.
	 * 
	 * @param problem The problem to be solved.
	 * @param generationSize The amount of children in a generation.
	 * @param mutationRate How often a state mutates.
	 * @return The solution state.
	 */
	public S solve(GeneticProblem<S> problem, int generationSize, int mutationRate) {
		S result = null;
		
		Set<S> generation = new HashSet<S>();
		for(int i = 0; i < generationSize; i++) {
			generation.add(problem.getRandomState());
		}
		
		generationCount = 1;
		
		boolean isRunning = true;
		while(isRunning) {
			result = checkGeneration(generation, problem);
			if(result != null) {
				isRunning = false;
			}
			Set<S> nextGeneration = spawnGeneration(generation, problem, mutationRate);
			generation = nextGeneration;
			generationCount++;
		}
		
		
		
		return result;
	}
	
	/**
	 * Private helper to make a new generation based off a current generation.
	 * @param generation The generation to be used as parents.
	 * @param problem The problem to be solved.
	 * @param mutationRate The likelihood of a child mutating.
	 * @return The newly created generation.
	 */
	private Set<S> spawnGeneration(Set<S> generation, GeneticProblem<S> problem, int mutationRate){
		Set<S> newGeneration = new HashSet<S>();
		
		for(int i = 0; i < generation.size(); i++) {
			S parentOne = fitnessProportionateSelection(generation, problem);
			S parentTwo = fitnessProportionateSelection(generation, problem);
			S child = problem.crossover(parentOne, parentTwo);
			
			int chance = rand.nextInt(99);
			if(mutationRate >= chance) {
				problem.mutate(child);
			}
			newGeneration.add(child);
			
//			int childFit = problem.getFitness(child);
//			if(childFit == problem.getMaximumFitness()) {
//				System.out.println("BARK");
//			}
		}
		
		

		
		return newGeneration;
	}
	
	/**
	 * Private helper to select a parent. Likelihood of selection based on how high its fitness is.
	 * @param generation The generation for parents to be selected from.
	 * @param problem The problem to be solved.
	 * @return The new child.
	 */
	private S fitnessProportionateSelection(Set<S> generation, GeneticProblem<S> problem) {
		int totalFitness = 0;
		
		for(S current : generation) {
			int currentFitness = problem.getFitness(current);
			
//			if(currentFitness < 0) {
//				System.out.println("C: " + currentFitness);
//				System.out.println(generation.size());
//				System.out.println(current.toString());
//			}
//			
			totalFitness = totalFitness + currentFitness;
		}
//		System.out.println("T: " + totalFitness + " G: " + generation.size());
	
		
		S found = null;
		int accumlatedFitness = 0;
		int neededFitness = rand.nextInt(totalFitness);
		if(neededFitness == 0) {
			neededFitness = 1;
		}
		
		Iterator<S> iter = generation.iterator();
		S potentialParent = iter.next();
		
		//TODO: Does this need to worry about going over?
		while(found == null) {

			accumlatedFitness += problem.getFitness(potentialParent);
			if(accumlatedFitness >= neededFitness) {
				found = potentialParent;
			}
			else {
				potentialParent = iter.next();
			}
		}
		
		return found;
	}
	
	/**
	 * Method to check if a generation contains a solution and return that state if so.
	 * @param generation The generation to be searched.
	 * @param problem The problem to be solved.
	 * @return A solution state if found, otherwise null.
	 */
	private S checkGeneration(Set<S> generation, GeneticProblem<S> problem) {
		S result = null;
		Iterator<S> iter = generation.iterator();
		
		boolean found = false;
		while(!found && iter.hasNext()) {
			S current = iter.next();
			int currentFitness = problem.getFitness(current);
			if(currentFitness == problem.getMaximumFitness()) {
				result = current;
				found = true;
			}
		}
		
		
		return result;
	}
	
	/**
	 * The number of generations created during the last call to solve.
	 * @return The number of generations.
	 */
	public int getGenerationCount() {
		return generationCount;
	}
}
