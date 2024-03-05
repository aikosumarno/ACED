package persistence;

import model.Card;
import model.Collection;
import model.Deck;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{
    @Test
    void testWriterInvalidFile() {
        try {
            Collection c = new Collection("Aiko's Flashcard Collection");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Collection c = new Collection("Aiko's Flashcard Collection");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCollection.json");
            writer.open();
            writer.write(c);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCollection.json");
            c = reader.read();
            assertEquals("Aiko's Flashcard Collection", c.getName());
            assertEquals(0, c.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Collection c = new Collection("Aiko's Flashcard Collection");
            Deck animalsDeck = new Deck("animals");
            Deck devicesDeck = new Deck("devices");
            c.addNewDeck(animalsDeck);
            c.addNewDeck(devicesDeck);
            Card dog = new Card("What sound does a dog make?", "bark", false, 0);
            Card cat = new Card("What sound does a cat make?", "meow", false, 2);
            Card camera = new Card("What is a camera used for?", "take pictures", true, 5);
            animalsDeck.addCard(dog);
            animalsDeck.addCard(cat);
            devicesDeck.addCard(camera);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCollection.json");
            writer.open();
            writer.write(c);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCollection.json");
            c = reader.read();
            assertEquals("Aiko's Flashcard Collection", c.getName());
            List<Deck> decks = c.getCollection();
            assertEquals(2, decks.size());
            checkDeck("animals", decks.get(0));
            checkDeck("devices", decks.get(1));
            List<Card> animalsCards = decks.get(0).viewDeck();
            List<Card> devicesCards = decks.get(1).viewDeck();
            checkCard("What sound does a dog make?", "bark", false,
                    0, animalsCards.get(0));
            checkCard("What sound does a cat make?", "meow", false,
                    2, animalsCards.get(1));
            checkCard("What is a camera used for?", "take pictures",
                    true, 5, devicesCards.get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
