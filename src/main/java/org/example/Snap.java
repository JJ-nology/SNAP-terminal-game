package org.example;

import java.util.Objects;
import java.util.Scanner;


public class Snap extends CardGame{
    protected Card currentCard;
    protected Card previousCard;
    protected Player player1;
    protected Player player2;
    protected Player currentPlayer;
    Scanner scanner = new Scanner(System.in);

// initial game set up
    private void setUpGame(){
        populateDeck();
        shuffleDeck();
        System.out.println("Player 1 please enter a screen name: ");
        String player = scanner.nextLine().toLowerCase();
        player1 = playerSetUp(player);
        System.out.println("Player 2 please enter a screen name: ");
        String anotherPlayer = scanner.nextLine().toLowerCase();
        player2 = playerSetUp(anotherPlayer);
        currentPlayer = player1;
        System.out.printf( "%s press ENTER to begin.\n", currentPlayer.getName());
    }
// set up player
    private Player playerSetUp(String playerName){
        if (playerName.isEmpty()) {
            System.out.println("Sorry, that name is invalid. Please try again");
            String name = scanner.nextLine().toLowerCase();
            playerSetUp(name);

        }
        return new Player(playerName);
    }
// run the game - While the player hasn't won, keep drawing cards
    public void gameIsOn(){
        scanner.nextLine();
        dealCard();

        currentCard = deckOfCards.getFirst();
        if(previousCard == null) {
            previousCard = currentCard;
            removeCard();
            System.out.println("Press ENTER to draw the next card");
            gameIsOn();

        }else {
            checkCardSymbols();
        }
    }
// delete the card at the top of the deck
    public void removeCard(){
        deckOfCards.removeFirst();
    }
// welcome player to the game, give instructions, set up the game
    public Snap() {
        System.out.println("""
                Welcome to SNAP!!\s
                All you need to win the game is to draw 2 cards\s
                in a row that have the same symbol i.e. 2 Jacks\s
                This is a 2 player game so press ENTER when it's your turn.""");

        setUpGame();
        gameIsOn();

    }
    // check if current and previous cards has the same symbol. Continue game or display winner
    public void checkCardSymbols() {
        if (Objects.equals(currentCard.symbol, previousCard.symbol)) {
            System.out.println("CONGRATULATIONS, YOU WIN");
        }else {
            previousCard = currentCard;
            removeCard();
            System.out.println("Press ENTER to draw again");
            gameIsOn();

        }
    }
}
