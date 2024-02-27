package model;

import java.util.ArrayList;
import java.util.List;

// Represents a collection having a list of decks
public class Collection {
    private List<Deck> myCollection;

    // EFFECTS: creates an instance of the class with an empty collection
    public Collection() {
        myCollection = new ArrayList<Deck>();
    }

    // EFFECTS: returns list of deck in collection
    public List<Deck> getCollection() {
        return myCollection;
    }

    // REQUIRES: collection does not already contain a deck with the same name
    // MODIFIES: this
    // EFFECTS: adds new deck to the end of the collection
    public Deck addNewDeck(Deck name) {
        myCollection.add(name);
        return name;
    }
}
