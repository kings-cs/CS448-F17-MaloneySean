package edu.kings.cs448.fall2017.MaloneySean.proplogic;

import java.util.ArrayList;
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
	private static String CASEFILE = "CF";
	
	/**
	 * Contains a string for each player in the game.
	 */
	private static String[] PLAYERS = {"P1", 
											 "P2",
											 "P3",
											 "P4",
											 "P5",
											 "P6",
											 CASEFILE};
	
	/**
	 * Contains a string for each card in the game.
	 */
	private static String[] CARDS = {"Mustard",
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
	private static String[] SUSPECTS = {"Mustard",
												"Plum",
												"Green",
												"Peacock",
												"Scarlet",
												"White"};
	
	/**
	 * Contains a string for every weapon in the game.
	 */
	private static String[] WEAPONS = {"Candlestick",
											"Revolver",
											"Rope",
											"Pipe",
											"Wrench"};

	/**
	 * Contains a string for every room in the game.
	 */
	private static String[] ROOMS = {"Hall",
			"Lounge",
			"Dining",
			"Kitchen",
			"Ballroom",
			"Conservatory",
			"Billiards",
			"Library",
			"Study"};
	

	
	/**
	 * The main method.
	 * @param args Unused.
	 */
	public static void main(String[] args) {
		
		ArrayList<String> currentPlayers = new ArrayList<String>();
		for(int i = 0; i < PLAYERS.length; i++) {
			currentPlayers.add(PLAYERS[i]);
		}
		
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
			String currentPlayer = currentPlayers.get(playerTurn);
			
			if(playerTurn == 0) {
				System.out.println("It is out turn!");
				System.out.print("We get to suggest a suspect whom we believe commited this guesome murder.");
				
				
				displaySuspectKnowledge(algorithm, knowledgeBase, currentPlayers);
				System.out.print("Whom do you wish to suggest?: ");
				
				String suspectGuess = input.next();
				
				System.out.println("We also get to suggest a weapon that we believe was used.");
				displayWeaponKnowledge(algorithm, knowledgeBase, currentPlayers);
				System.out.print("Which weapon do you wish to suggest?: ");
				
				String weaponGuess = input.next();
				
				System.out.println("We also get to suggest a room in which we believe the murder was commited.");
				displayRoomKnowledge(algorithm, knowledgeBase, currentPlayers);
				System.out.print("Which room do you wish to suggest?: ");
				
				String roomGuess = input.next();
				
				boolean cardShown = false;
				int showCount = 0;
				while(!cardShown && showCount < currentPlayers.size()) {
					String showingPlayer = currentPlayers.get(showCount);
					System.out.print("Does " + showingPlayer + " show you a card? (y/n): ");
					String yesNo = input.next();
					
					if(yesNo.equals("y")) {
						cardShown = true;
						
						System.out.print("Which card did they show you?: ");
						String shownCard = input.next();
						
						knowledgeBase.addSentence(new Proposition(buildHasCard(showingPlayer, shownCard)));
						
					}
					else {
						Disjunction doesNotHave = new Disjunction();
						Negation doesNotHaveSuspect = new Negation(new Proposition(buildHasCard(showingPlayer, suspectGuess)));
						Negation doesNotHaveWeapon = new Negation(new Proposition(buildHasCard(showingPlayer, weaponGuess)));
						Negation doesNotHaveRoom = new Negation(new Proposition(buildHasCard(showingPlayer, roomGuess)));
						
						doesNotHave.addSentence(doesNotHaveSuspect);
						doesNotHave.addSentence(doesNotHaveWeapon);
						doesNotHave.addSentence(doesNotHaveRoom);
						
						knowledgeBase.addSentence(doesNotHave);
					}
					
					showCount++;
				
				}
				
				
				System.out.print("Do you wish to also make an acusation? (y/n): ");
				String yesNo = input.next();
				if(yesNo.equals("y")) {					
					System.out.print("Was your accusation correct? (y/n): ");
					String win = input.next();
					
					if(win.equals("y")) {
						System.out.println("Congratulations!");
					}
					else {
						System.out.println("Better luck next time!");
					}
					
					playing = false;
				}
				
			}
			else {
				
				//OTHER PLAYERS TURN
				
				System.out.println("It is " + currentPlayer + "'s turn!");
				
				System.out.print("Whom did " + currentPlayer + " guess commited the murder?: ");
				String suspectGuess = input.next();
				
				
				System.out.print("What weapon did " + currentPlayer + " guess was used?: ");
				String weaponGuess = input.next();
				
				System.out.print("Where did " + currentPlayer + " guess the crime was commited?: ");
				String roomGuess = input.next();
				
				boolean cardShown = false;
				int showCount = 0;
				while(!cardShown && showCount < currentPlayers.size()) {
					String showingPlayer = currentPlayers.get(showCount);
					System.out.print("Did " + showingPlayer + " show " + currentPlayer + " a card? (y/n): ");
					String yesNo = input.next();
					
					if(yesNo.equals("y")) {
						cardShown = true;
						
						Disjunction hasGuessCard = new Disjunction();
						hasGuessCard.addSentence(new Proposition(buildHasCard(showingPlayer, suspectGuess)));
						hasGuessCard.addSentence(new Proposition(buildHasCard(showingPlayer, weaponGuess)));
						hasGuessCard.addSentence(new Proposition(buildHasCard(showingPlayer, roomGuess)));
					}
					else {
						Conjunction doesNotHave = new Conjunction();
						Negation doesNotHaveSuspect = new Negation(new Proposition(buildHasCard(showingPlayer, suspectGuess)));
						Negation doesNotHaveWeapon = new Negation(new Proposition(buildHasCard(showingPlayer, weaponGuess)));
						Negation doesNotHaveRoom = new Negation(new Proposition(buildHasCard(showingPlayer, roomGuess)));
						
						doesNotHave.addSentence(doesNotHaveSuspect);
						doesNotHave.addSentence(doesNotHaveWeapon);
						doesNotHave.addSentence(doesNotHaveRoom);
						
						knowledgeBase.addSentence(doesNotHave);
					}
					
					showCount++;
				
				}
				
				System.out.print("Did " + currentPlayer + " make an acussation? (y/n): ");
				String accused = input.next();
				if(accused.equals("y")) {
					
					
					System.out.print("Whom did they accuse?: ");
					String accusedGuess = input.next();
					
					System.out.print("What weapon did they say was used?: ");
					String accusedWeapon = input.next();
					
					System.out.print("Where did they say the murder was commited?: ");
					String accusedLocation = input.next();
					
					System.out.print("Were " + currentPlayer + "'s accusation correct? (y/n): ");
					String win = input.next();
					
					if(win.equals("y")) {
						System.out.println(currentPlayer + " wins");
						playing = true;
					}
					else {
						System.out.println(currentPlayer + "is out of the game");
						
						Conjunction doesNotHave = new Conjunction();
						Negation doesNotHaveSuspect = new Negation(new Proposition(buildHasCard(CASEFILE, accusedGuess)));
						Negation doesNotHaveWeapon = new Negation(new Proposition(buildHasCard(CASEFILE, accusedWeapon)));
						Negation doesNotHaveRoom = new Negation(new Proposition(buildHasCard(CASEFILE, accusedLocation)));
						
						doesNotHave.addSentence(doesNotHaveSuspect);
						doesNotHave.addSentence(doesNotHaveWeapon);
						doesNotHave.addSentence(doesNotHaveRoom);
						
						knowledgeBase.addSentence(doesNotHave);
						
						
						currentPlayers.remove(currentPlayer);
					}
					
				}
				
				
				
			}
			
			playerTurn++;
			
			if(!(playerTurn < currentPlayers.size() - 1)) {
				playerTurn = 0;
			}
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
	 * 
	 * @param algorithm The entailment checker used.
	 * @param knowledgeBase The knowledge base.
	 * @param currentPlayers The players still playing.
	 */
	private static void displaySuspectKnowledge(EntailmentChecker algorithm, Sentence knowledgeBase, ArrayList<String> currentPlayers) {
		System.out.println("Here is what we know about the locations of the suspect cards: ");
		
		for(int i = 0; i < SUSPECTS.length; i++) {
			String suspect = SUSPECTS[i];
			StringBuffer result = new StringBuffer();
			result.append(suspect + " is in one of the following: ");
			
			boolean playerHas = false;
			int count = 0;
			while(!playerHas && count < currentPlayers.size()) {

				String player = currentPlayers.get(count);
				String currentSentence = buildHasCard(player, suspect);
				boolean entailment = algorithm.entails(knowledgeBase, new Proposition(currentSentence));

				if(!entailment) {
					if(count != 0) {
						result.append(player + " ");
					}
				}
				else {
					playerHas = true;
					result = new StringBuffer();
					result.append(suspect + " is in one of the following: " + player);
				}

				count++;
			}
			System.out.println(result.toString());
		}
	
		
	}
	
	/**
	 * Private helper to display what we know about the weapon cards.
	 * 
	 * @param algorithm The entailment checker used.
	 * @param knowledgeBase The knowledge base.
	 * @param currentPlayers The players who are still left in the game.
	 */
	private static void displayWeaponKnowledge(EntailmentChecker algorithm, Sentence knowledgeBase, ArrayList<String> currentPlayers) {
		System.out.println("Here is what we know about the locations of the weapons cards: ");
		
		for(int i = 0; i < WEAPONS.length; i++) {
			String weapon = WEAPONS[i];
			StringBuffer result = new StringBuffer();
			result.append(weapon + " is in one of the following: ");
			
			boolean playerHas = false;
			int count = 0;
			while(!playerHas && count < currentPlayers.size()) {

				String player = currentPlayers.get(count);
				String currentSentence = buildHasCard(player, weapon);
				boolean entailment = algorithm.entails(knowledgeBase, new Proposition(currentSentence));

				if(!entailment) {
					if(count != 0) {
						result.append(player + " ");
					}
				}
				else {
					playerHas = true;
					result = new StringBuffer();
					result.append(weapon + " is in one of the following: " + player);
				}

				count++;
			}
			System.out.println(result.toString());
		}
		
	}
	
	/**
	 * Private helper to display what we know about the locations of the room cards.
	 * 
	 * @param algorithm The entailment checker used.
	 * @param knowledgeBase The knowledge base.
	 * @param currentPlayers The players who are still left in the game.
	 */
	private static void displayRoomKnowledge(EntailmentChecker algorithm, Sentence knowledgeBase, ArrayList<String> currentPlayers) {
		System.out.println("Here is what we know about the locations of the room cards: ");
		
		for(int i = 0; i < ROOMS.length; i++) {
			String room = ROOMS[i];
			StringBuffer result = new StringBuffer();
			result.append(room + " is in one of the following: ");
			
			boolean playerHas = false;
			int count = 0;
			while(!playerHas && count < currentPlayers.size()) {

				String player = currentPlayers.get(count);
				String currentSentence = buildHasCard(player, room);
				boolean entailment = algorithm.entails(knowledgeBase, new Proposition(currentSentence));

				if(!entailment) {
					if(count != 0) {
						result.append(player + " ");
					}
				}
				else {
					playerHas = true;
					result = new StringBuffer();
					result.append(room + " is in one of the following: " + player);
				}

				count++;
			}
			System.out.println(result.toString());
		}
	}
}
