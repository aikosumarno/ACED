package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        myCollection = new Collection();
    }

    @Test
    void testCollection() {
        assertTrue( myCollection.getCollection().isEmpty());
    }

    @Test
    void testAddNewDeck() {
        myCollection.addNewDeck(subjects);
        assertEquals(1, myCollection.getCollection().size());
        assertEquals(subjects, myCollection.getCollection().get(0));
        myCollection.addNewDeck(emotions);
        assertEquals(2, myCollection.getCollection().size());
        assertEquals(emotions, myCollection.getCollection().get(1));
    }
}
