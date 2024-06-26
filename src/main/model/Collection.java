package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a collection having a list of decks
public class Collection implements Writable {
    private String name;
    private List<Deck> myCollection;

    // EFFECTS: creates an instance of the class with a name and an empty collection
    public Collection(String name) {
        this.name = name;
        myCollection = new ArrayList<Deck>();
    }

    // EFFECTS: returns the name of the collection
    public String getName() {
        return name;
    }

    // EFFECTS: returns list of deck in collection
    public List<Deck> getCollection() {
        return myCollection;
    }

    // EFFECTS: returns a list of deck names
    public List<String> getDeckNames() {
        List<String> deckNames = new ArrayList<>();
        for (Deck d : myCollection) {
            deckNames.add(d.getName());
        }
        return deckNames;
    }

    // EFFECTS: returns the number of decks in the collection
    public int getSize() {
        return myCollection.size();
    }

    // REQUIRES: collection does not already contain a deck with the same name
    // MODIFIES: this
    // EFFECTS: adds new deck to the end of the collection
    public Deck addNewDeck(Deck name) {
        myCollection.add(name);
        EventLog.getInstance().logEvent(new Event("Added deck '" + name.getName() + "' to collection."));
        return name;
    }

    // EFFECTS: returns fields in this collection into JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("myCollection", decksToJson());
        return json;
    }

    // EFFECTS: returns decks in this collection as a JSON array
    private JSONArray decksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Deck d : myCollection) {
            jsonArray.put(d.toJson());
        }

        return jsonArray;
    }
}
