package edu.kings.cs448.fall2017.MaloneySean.proplogic;

import java.util.Arrays;
import java.util.Scanner;

/**
 * A class that allows us o work with a knowledge base representing the game Clue.
 * @author Sean Maloney
 */
public class ClueAgent {
	
	/**
	 * Represents the case file.
	 */
	private static final String CASEFILE = "CF";
	
	/**
	 * Contains a string for each player in the game.
	 */
	private static final String[] PLAYERS = {"P1", 
											 "P2",
											 "P3",
											 "P4",
											 "P5",
											 "P6",
											 CASEFILE};
	
	/**
	 * Contains a string for each card in the game.
	 */
	private static final String[] CARDS = {"Mustard",
											"Plum",
											"Green",
											"Peacock",
											"Scarlet",
											"White",
											"Knife",
											"Candlestick",
											"Revolver",
											"Rope",
											"Pipe",
											"Wrench",
											"Hall",
											"Lounge",
											"Dining",
											"Kitchen",
											"Ballroom",
											"Conservatory",
											"Billiards",
											"Library",
											"Study"};
	


	
	/**
	 * Contains a string for every character card in the game.
	 */
	private static final String[] SUSPECTS = {"Mustard",
												"Plum",
												"Green",
												"Peacock",
												"Scarlet",
												"White"};
	
	/**
	 * Contains a string for every weapon in the game.
	 */
	private static final String[] WEAPONS = {"Candlestick",
											"Revolver",
											"Rope",
											"Pipe",
											"Wrench"};

	/**
	 * Contains a string for every room in the game.
	 */
	private static final String[] ROOMS = {};
	

	
	/**
	 * The main method.
	 * @param args Unused.
	 */
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		Conjunction knowledgeBase = getInitialKnowledgeBase();
		//Conjunction knowledgeBase = new Conjunction();
		EntailmentChecker algorithm = new DPLLAlgorithm();
		
		System.out.println("I hear that you are playing a 6-person game of Clue and would like some help");
		System.out.println("I am going to assume that you are the first player (player 1).");
		System.out.println("First, I need to know what cards you were dealt.");
		System.out.println("Please print 3 of the following, exactly.");
		System.out.println(Arrays.toString(CARDS));
		
		System.out.print("Card: ");
		String cardOne = input.next();
		System.out.print("Card: ");
		String cardTwo = input.next();
		System.out.print("Card: ");
		String cardThree = input.next();
		
		
		String hasCardOne = buildHasCard(PLAYERS[0], cardOne);
		String hasCardTwo = buildHasCard(PLAYERS[0], cardTwo);
		String hasCardThree = buildHasCard(PLAYERS[0], cardThree);
		
		knowledgeBase.addSentence(new Proposition(hasCardOne));
		knowledgeBase.addSentence(new Proposition(hasCardTwo));
		knowledgeBase.addSentence(new Proposition(hasCardThree));
		
		System.out.println("OK, time for the game to start!");
		
		boolean playing = true;
		int playerTurn = 0;
		while(playing) {
			String currentPlayer = PLAYERS[0];
			
			String result = displaySuspectKnowledge(algorithm, knowledgeBase, SUSPECTS[0]);
			System.out.println(result);
			
			playing = false;
		}
		
	}

	/**
	 * Gets all of the knowledge rules that are true in every instance of Clue, before the game has started.
	 * @return An initial knowledge base for Clue.
	 */
	public static Conjunction getInitialKnowledgeBase() {
		Conjunction knowledgeBase = new Conjunction();
		
		//Each card is in at lease one place 
		for(int i = 0; i < CARDS.length; i++) {
			String currentCard = CARDS[i];
			Disjunction cardLocations = new Disjunction();
			for(int j = 0; j < PLAYERS.length; j++) {
				String currentPlayer = PLAYERS[j];
				String hasCard = buildHasCard(currentPlayer, currentCard);
				
				
				
				cardLocations.addSentence(new Proposition(hasCard));
				
			}
			knowledgeBase.addSentence(cardLocations);
		}
		
		//Each card is in at most one place
		for(int i = 0; i < CARDS.length; i++) {
			String currentCard = CARDS[i];
			for(int j = 0; j < PLAYERS.length; j++) {
				String cardHolder = PLAYERS[j];
				for(int k = 0; k < PLAYERS.length; k++) {
					if(j != k) {
						String cantHold = PLAYERS[k];
						
						Sentence cantHaveCard = Sentence.parse("( " + buildHasCard(cardHolder, currentCard) + " --> ! ( " + buildHasCard(cantHold, currentCard) + " ) )");
						knowledgeBase.addSentence(cantHaveCard);
					}
					
				}
				
			}
		}
		
		
		
		//Case File Contains At Least One Suspect Card
		Disjunction caseFileHasSuspect = new Disjunction();
		for(int i = 0; i < SUSPECTS.length; i++) {
			String currentSuspect = SUSPECTS[i];
			String hasCard = buildHasCard(CASEFILE, currentSuspect);
			caseFileHasSuspect.addSentence(new Proposition(hasCard));
		}
		knowledgeBase.addSentence(caseFileHasSuspect);
		
		//Case File Contains At Least One Weapon Card
		Disjunction caseFileHasWeapon = new Disjunction();
		for(int i = 0; i < WEAPONS.length; i++) {
			String currentWeapon = WEAPONS[i];
			String hasCard = buildHasCard(CASEFILE, currentWeapon);
			caseFileHasWeapon.addSentence(new Proposition(hasCard));
		}
		knowledgeBase.addSentence(caseFileHasWeapon);
		
		//Case File Contains At Least One Room Card
		Disjunction caseFileHasRoom = new Disjunction();
		for(int i = 0; i < ROOMS.length; i++) {
			String currentRoom = ROOMS[i];
			String hasCard = buildHasCard(CASEFILE, currentRoom);
			caseFileHasRoom.addSentence(new Proposition(hasCard));
		}
		knowledgeBase.addSentence(caseFileHasRoom);
		
		
		//Case File Contains At Most One Suspect Card
		for(int i = 0; i < SUSPECTS.length; i++) {
			String currentSuspect = SUSPECTS[i];
			for(int j = 0; j < SUSPECTS.length; j++) {
				if(i != j) {
					String cantHave = SUSPECTS[j];
					
					Sentence cantHaveSuspect = Sentence.parse("( " + buildHasCard(CASEFILE, currentSuspect) + " --> ! ( " + buildHasCard(CASEFILE, cantHave) + " ) )");
					knowledgeBase.addSentence(cantHaveSuspect);
				}
			}
		}
		
		
		//Case File Contains At Most One Weapon Card
		for(int i = 0; i < WEAPONS.length; i++) {
			String currentWeapon = WEAPONS[i];
			for(int j = 0; j < WEAPONS.length; j++) {
				if(i != j) {
					String cantHave = WEAPONS[j];
					
					Sentence cantHaveSuspect = Sentence.parse("( " + buildHasCard(CASEFILE, currentWeapon) + " --> ! ( " + buildHasCard(CASEFILE, cantHave) + " ) )");
					knowledgeBase.addSentence(cantHaveSuspect);
				}
			}
		}
		
		//Case File Contains At MostOne Room Card
		for(int i = 0; i < ROOMS.length; i++) {
			String currentRoom = ROOMS[i];
			for(int j = 0; j < ROOMS.length; j++) {
				if(i != j) {
					String cantHave = ROOMS[j];
					
					Sentence cantHaveSuspect = Sentence.parse("( " + buildHasCard(CASEFILE, currentRoom) + " --> ! ( " + buildHasCard(CASEFILE, cantHave) + " ) )");
					knowledgeBase.addSentence(cantHaveSuspect);
				}
			}
		}
		
		
		return knowledgeBase;
	}
	
	
	/**
	 * Builds a sentence for a player having a card.
	 * @param player The player with the card.
	 * @param card The card in question.
	 * @return The sentence.
	 */
	private static String buildHasCard(String player, String card) {
		String result = "HasCard(" + player + "," + card + ")";
		return result;
	}
	
	
	/**
	 * Private helper to display what we know about the suspect cards.
	 */
	private static String displaySuspectKnowledge(EntailmentChecker algorithm, Sentence knowledgeBase, String suspect) {
		System.out.println("Here is what we know about the locations of the suspect cards: ");
		
		
		StringBuffer result = new StringBuffer();
		result.append(suspect + " is in one of the following: ");
		
		boolean playerHas = false;
		int count = 0;
		
		while(!playerHas && count < PLAYERS.length) {
			
			String player = PLAYERS[count];
			String currentSentence = buildHasCard(player, suspect);
			boolean entailment = algorithm.entails(knowledgeBase, new Proposition(currentSentence));
			
			System.out.println(currentSentence);
			System.out.println(player + " : " + entailment);
			
			if(!entailment) {
				result.append(player + " ");
			}
			else {
				playerHas = true;
				result = new StringBuffer();
				result.append(suspect + " is in one of the following: " + player);
			}
			
			count++;
		}
	
		return result.toString();
	}
	
	/**
	 * Private helper to display what we know about the weapon cards.
	 */
	private static void displayWeaponKnowledge() {
		System.out.println("Here is what we know about the locations of the weapons cards: ");
	}
	
	/**
	 * Private helper to display what we know about the locations of the room cards.
	 */
	private static void displayLocationKnowledge() {
		System.out.println("Here is what we know about the locations of the room cards: ");
	}
}
