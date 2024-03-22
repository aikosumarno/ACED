package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CollectionTest {
    private Deck subjects;
    private Deck emotions;
    private Collection myCollection;

    @BeforeEach
    void runBefore() {
        subjects = new Deck("subjects");
        emotions = new Deck("emotions");
        myCollection = new Collection("Aiko's Flashcard Collection");
    }

    @Test
    void testCollection() {
        assertTrue( myCollection.getCollection().isEmpty());
        assertTrue(myCollection.getDeckNames().isEmpty());
        assertEquals("Aiko's Flashcard Collection", myCollection.getName());
    }

    @Test
    void testAddNewDeck() {
        List<String> deckNames = new ArrayList<>();
        deckNames.add("subjects");
        deckNames.add("emotions");
        myCollection.addNewDeck(subjects);
        assertEquals(1, myCollection.getCollection().size());
        assertEquals(subjects, myCollection.getCollection().get(0));
        myCollection.addNewDeck(emotions);
        assertEquals(2, myCollection.getCollection().size());
        assertEquals(emotions, myCollection.getCollection().get(1));
        assertEquals(deckNames, myCollection.getDeckNames());
    }
}
