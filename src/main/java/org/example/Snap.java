package org.example;

import java.util.Objects;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


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
        this.player1 = playerSetUp(player);
        System.out.println("Player 2 please enter a screen name: ");
        String anotherPlayer = scanner.nextLine().toLowerCase();
        this.player2 = playerSetUp(anotherPlayer);
        this.currentPlayer = this.player1;
        System.out.printf( "%s press ENTER to begin.\n", this.currentPlayer.getName());
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
            System.out.printf("%s ,press ENTER to draw the next card", this.currentPlayer.getName());
            gameIsOn();

        }else {
            checkCardSymbols();
        }
    }

// set up player
    private Player playerSetUp(String playerName){
        if (playerName.isEmpty()) {
            System.out.println("Sorry, that name is invalid. Please try again");
            playerName = scanner.nextLine().toLowerCase();
            playerSetUp(playerName);

        }
        return new Player(playerName);
    }

// set up player toggle
    private void togglePlayer(){
        if (this.player1.getName().equals(this.currentPlayer.getName())) {
            this.currentPlayer = this.player2;
        } else {
            this.currentPlayer = this.player1;
        }

    }

// delete the card at the top of the deck
    public void removeCard(){
        deckOfCards.removeFirst();
    }

    // check if current and previous cards has the same symbol. Continue game or display winner
    public void checkCardSymbols() {
        if (Objects.equals(currentCard.symbol, previousCard.symbol)) {
            System.out.println(this.currentPlayer.getName() + " type snap within 3 seconds to win");
            String typeSnap = scanner.nextLine();
            //3-second timer to type snap and secure win
            Timer timer = new Timer();

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    togglePlayer();
                    System.out.println("Sorry, you were too slow. \n.CONGRATULATIONS " + currentPlayer.getName() + " wins  !!");
                }
            }, 3000);

            if(Objects.equals(typeSnap.toLowerCase(), "snap")){
                timer.cancel();
                System.out.println("WELL DONE " + this.currentPlayer.getName() + " you are the winner!!");
            }

        } else {
            previousCard = currentCard;
            removeCard();
            togglePlayer();
            System.out.println(this.currentPlayer.getName() + " ,press ENTER to draw the next card");
            gameIsOn();
        }
    }
}
