# CS448-F17-MaloneySean
Artificial Inteligence Assignments 

Author: Sean Maloney
Base Code via Chad Hogg
Last Updated: 12/21/2017


  *Table Of Contents*
 
0. Introduction
1. Search
2. Local Search
3. Strategy Games
4. Constraint Satisfaction Problems
5. Propositional Logic


**************************

0. This repository contains code from various assignments for CS448 that use Artificial Inteligence algorithms/techniques to solve problems.

1. Found in package: edu.kings.cs448.fall2017.MaloneySean.search
  
  Contains implementaions of various classical search algorithms:
  
    * A Star Graph Search
    * Breadth First Graph Search
    * Breadth First Tree Search
    * Depth First Graph Search
    * Depth First Tree Search
    * Greedy Best First Graph Search 
    * Iterative Deepening Graph Search
    * Iterative Deepening Tree Search
    * Uniform Cost Graph Search
    
   These algorithms can be used to solve 8 Puzzle Problems by launching the Eight Puzzle Main Class and following the prompts. 
   Problem states should be inputted in the following format: 7 2 4 5 0 6 8 3 1 
   
   Possible outcomes of the algorithms:
   
    * Found a solution of length X after generating Y nodes in Z seconds.
    * Terminated with OutOfMemoryError exception
    * Terminated with StackOverflowError exception
    * Manually killed after running for more than one hour
    
2. Found in package: edu.kings.cs448.fall2017.MaloneySean.localsearch
  
   Contains implementaions of various local search algorithms:
    
    * Bogo Search
    * Genetic Search
    * Hill Climbing Search
    
   These algorithms can be used to solve the Eight Queens Problem by launching Eight Queens Main Class.

3. Found in package: edu.kings.cs448.fall2017.MaloneySean.strategygames

  Contains implementations of algorithms used to play two player strategy games:
  
    * Alpha Beta Pruning Search
    * Iterative Deepening Search
    * Mini Max Search
    * Mini Max With Cutoff 
    * Random 
 
  These algorithms can be used to play Tic Tac Toe or Connect Four by launching either Main class and following the prompts. 
  
4. Found in package: edu.kings.cs448.fall2017.MaloneySean.csps

   Contains implementations of algorithms used to solve constraint satisfaction problems:
   
   * Simple Backtracking Search
   * Backtracking Search With AC3
   * Backtracking Search With AC3 and Heuristics
   
   These algorithms can be used to solve the Australia Coloring problem or Sudoku by launching either the AustraliaMain or Sudoku class.
   The Sudoku class is currently configured to solve the following puzzle where 0s represent open spaces: 

050000020

001002700

076400800

700060300

003805900

002040005

004007590

008100600

090000010
   

5. Found in package: edu.kings.cs448.fall2017.MaloneySean.proplogic
  
    Uses algorithms based on knowledge bases of propositional logic to solve project.
    Uses the DPLL algorithm to check for sentence entailment. 
    
    Current contains a ClueAgent that can used to assist in the playing of Clue by following the given prompts. By nature, this algorithms is very slow.
   
