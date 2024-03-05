package persistence;

import model.Card;
import model.Deck;
import org.junit.jupiter.api.Test;

import model.Collection;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Collection c = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCollection.json");
        try {
            Collection c = reader.read();
            assertEquals("Emily's Flashcard Collection", c.getName());
            assertEquals(0, c.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCollection.json");
        try {
            Collection c = reader.read();
            assertEquals("Maya's Flashcard Collection", c.getName());
            List<Deck> decks = c.getCollection();
            assertEquals(2, decks.size());
            checkDeck("emotions", decks.get(0));
            checkDeck("objects", decks.get(1));
            List<Card> emotionsCards = decks.get(0).viewDeck();
            List<Card> objectsCards = decks.get(1).viewDeck();
            checkCard("Define Happy", "feeling joyful", false,
                    1, emotionsCards.get(0));
            checkCard("Define Sad", "feeling upset", true,
                    3, emotionsCards.get(1));
            checkCard("What is a water bottle used for?", "to hold water",
                    false, 0, objectsCards.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
