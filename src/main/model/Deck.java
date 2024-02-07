package model;

import java.util.List;

public class Deck {

    // EFFECTS: creates an instance of the class with an empty deck of the given name
    public Deck(String name) {

    }

    // REQUIRES: deck does not already contain card with same question and answer
    // MODIFIES: this
    // EFFECTS: adds given card to the end of the deck
    public void addCard(Card card) {

    }

    // EFFECTS: returns the deck of cards
    public List<Card> viewCards() {
        return null; //stub
    }

    // EFFECTS: returns the number of cards in the deck
    public int getSize() {
        return 0; //stub
    }


    // REQUIRES: currentDeck.size() != 0
    // EFFECTS: returns the card in the list with the given question,
    //          null if not found
    public Card findCard(String question) {
        return null; //stub
    }

    // REQUIRES: currentDeck.findCard(currentQuestion) != null
    // MODIFIES: this, card with current questions
    // EFFECTS: changes the card's question and answer  with the new question
    //          and answer, respectively.
    public void editCard(String currentQuestion, String newQuestion, String newAnswer) {

    }

    // REQUIRES: currentDeck.findCard(question) != null
    // MODIFIES: this
    // EFFECTS: deletes and returns the card with the given question
    public Card deleteCard(String question) {
        return null; //stub
    }
}
