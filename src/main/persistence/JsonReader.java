package persistence;

import model.Card;
import model.Collection;
import model.Deck;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads collection from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Collection read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCollection(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Collection parseCollection(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Collection c = new Collection(name);
        addDecks(c, jsonObject);
        return c;
    }

    // MODIFIES: c
    // EFFECTS: parses decks from JSON object and adds them to collection
    private void addDecks(Collection c, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("myCollection");
        for (Object json : jsonArray) {
            JSONObject nextDeck = (JSONObject) json;
            addDeck(c, nextDeck);
        }
    }

    // MODIFIES: c
    // EFFECTS: parses deck from JSON object and adds it to collection
    private void addDeck(Collection c, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Deck deck = new Deck(name);
        c.addNewDeck(deck);
        addCards(deck, jsonObject);
    }

    // MODIFIES: c
    // EFFECTS: parses cards from JSON object and adds them to deck
    private void addCards(Deck d, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("currentDeck");
        for (Object json : jsonArray) {
            JSONObject nextCard = (JSONObject) json;
            addCard(d, nextCard);
        }
    }

    // MODIFIES: c
    // EFFECTS: parses card from JSON object and adds it to deck
    private void addCard(Deck d, JSONObject jsonObject) {
        String question = jsonObject.getString("question");
        String answer = jsonObject.getString("answer");
        boolean status = jsonObject.getBoolean("status");
        int studyCounter = jsonObject.getInt("studyCounter");
        Card card = new Card(question, answer, status, studyCounter);
        d.addCard(card);
    }
}
