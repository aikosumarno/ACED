package model;

import java.util.List;

public class Deck {

    // EFFECTS: creates an instance of the class with an empty deck of the given name
    public Deck(String name) {

    }

    // REQUIRES: deck does not already contain card with same question and answer
    // MODIFIES: this
    // EFFECTS: adds given card to the end of the deck
    public Card addCard(Card card) {
        return null; //stub
    }

    // EFFECTS: returns the deck of cards
    public List<Card> viewDeck() {
        return null; //stub
    }

    // EFFECTS: returns the number of cards in the deck
    public int getSize() {
        return 0; //stub
    }


    // REQUIRES: currentDeck.size() != 0
    // EFFECTS: returns the card's position in the list,
    //          -1 if not found
    public int findCardNumber(Card card) {
        return 0; //stub
    }

    // REQUIRES: currentDeck.findCard(currentQuestion) != null
    // MODIFIES: this, card with current questions
    // EFFECTS: changes the card's question and answer  with the new question
    //          and answer, respectively.
    public Card editCard(Card card, String newQuestion, String newAnswer) {
        return null; //stub
    }

    // REQUIRES: currentDeck.findCard(question) != null
    // MODIFIES: this
    // EFFECTS: deletes and returns the card with the given question
    public Card deleteCard(Card card) {
        return null; //stub
    }
}
