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

// run the game - While the player hasn't won, keep drawing cards
    public void gameIsOn(){
        scanner.nextLine();
        dealCard();

        currentCard = deckOfCards.getFirst();
        if(previousCard == null) {
            previousCard = currentCard;
            removeCard();
            togglePlayer();
            System.out.printf("%s ,press ENTER to draw the next card", currentPlayer.getName());
            gameIsOn();

        }else {
            checkCardSymbols();
        }
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

// set up player toggle
    private void togglePlayer(){
        if (player1.getName().equals(currentPlayer.getName())) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }

    }

// delete the card at the top of the deck
    public void removeCard(){
        deckOfCards.removeFirst();
    }

    // check if current and previous cards has the same symbol. Continue game or display winner
    public void checkCardSymbols() {
        if (Objects.equals(currentCard.symbol, previousCard.symbol)) {
            System.out.println("CONGRATULATIONS, " + currentPlayer.getName() + " YOU WIN");
        }else {
            previousCard = currentCard;
            removeCard();
            togglePlayer();
            System.out.println(currentPlayer.getName() + " ,press ENTER to draw the next card");
            gameIsOn();

        }
    }
}
