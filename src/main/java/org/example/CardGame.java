package org.example;

import java.util.ArrayList;
import java.util.List;

public class CardGame {
    static List<Card> deckOfCards = new ArrayList<>(){};
    static final String[] cardSuit = new String[]{"♠", "♥", "♦", "♣"};
    static final String[] cardSymbol = new String[]{"2", "3","4","5","6","7","8","9","10","J","Q","K","A"};
    static final int[] cardValue = new int[]{2,3,4,5,6,7,8,9,10,11,12,13,14};

    public CardGame() {
        populateDeck();
    }

//setup card deck and add cards to the new deck
    private static void populateDeck(){
        for (String suit: cardSuit){
            int count = 0;
            for(String symbol: cardSymbol){
                Card card = new Card(suit, symbol, cardValue[count++]);
                deckOfCards.add(card);
            }
        }
    }

    static void getDeck(){
        for (Card card : deckOfCards){
            System.out.println(card);
        }

    }
}
