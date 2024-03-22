package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a deck having a list of cards, and a name
public class Deck implements Writable {
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

    // EFFECTS: returns the list of questions in the deck
    public List<String> getQuestions() {
        List<String> questions = new ArrayList<>();
        for (Card c : currentDeck) {
            questions.add(c.getQuestion());
        }
        return questions;
    }

    // EFFECTS: returns the list of answers in the deck
    public List<String> getAnswers() {
        List<String> answers = new ArrayList<>();
        for (Card c : currentDeck) {
            answers.add(c.getAnswer());
        }
        return answers;
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

    // EFFECTS: returns fields in this collection into JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("currentDeck", cardsToJson());
        return json;
    }

    // EFFECTS: returns cards in this deck as a JSON array
    private JSONArray cardsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Card c : currentDeck) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}
