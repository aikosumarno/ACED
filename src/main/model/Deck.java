package model;

import java.util.ArrayList;
import java.util.List;

// Represents a deck having a list of cards, and a name
public class Deck {
    private List<Card> currentDeck;
    private String name;

    // EFFECTS: creates an instance of the class with an empty deck of the given name
    public Deck(String name) {
        currentDeck = new ArrayList<Card>();
        this.name = name;
    }

    // REQUIRES: deck does not already contain card with same question and answer
    // MODIFIES: this
    // EFFECTS: adds given card to the end of the deck
    public Card addCard(Card card) {
        currentDeck.add(card);
        return card;
    }

    // EFFECTS: returns the deck of cards
    public List<Card> viewDeck() {
        return this.currentDeck;
    }

    // EFFECTS: returns the number of cards in the deck
    public int getSize() {
        return currentDeck.size();
    }

    // EFFECTS: returns the name of the deck
    public String getName() {
        return this.name;
    }


    // REQUIRES: currentDeck.size() != 0
    // EFFECTS: returns the card's position in the list,
    //          -1 if not found
    public int findCardNumber(Card card) {
        for (Card current : currentDeck) {
            if (current == card) {
                return currentDeck.indexOf(current) + 1;
            }
        }
        return -1;
    }

    // REQUIRES: currentDeck.findCard(currentQuestion) != null
    // MODIFIES: this, card with current questions
    // EFFECTS: changes the card's question and answer  with the new question
    //          and answer, respectively.
    public Card editCard(Card card, String newQuestion, String newAnswer) {
        card.editQuestion(newQuestion);
        card.editAnswer(newAnswer);
        return card;
    }

    // REQUIRES: currentDeck.findCard(question) != null
    // MODIFIES: this
    // EFFECTS: deletes and returns the card with the given question
    public Card deleteCard(Card card) {
        return currentDeck.remove(findCardNumber(card) - 1);
    }
}
