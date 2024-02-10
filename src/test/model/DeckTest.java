package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeckTest {
    private Card happy;
    private Card sad;
    private Card angry;
    private Card curious;
    private Card love;
    private Card maths;
    private Card science;
    private Deck emotions;
    private Deck subjects;

    @BeforeEach
    void runBefore() {
        emotions = new Deck("emotions");
        subjects = new Deck("subjects");
        happy = new Card("happy", "feeling content");
        sad = new Card("sad", "unhappy");
        angry = new Card("angry","to feel irritated");
        curious = new Card("curious", "eager to learn");
        love = new Card("love", "deep affection");
        maths = new Card("maths", "addition, subtraction, division, and multiplication");
        science = new Card("science", "understanding the natural world");
    }

    @Test
    void testDeck() {
        assertTrue(emotions.viewDeck().isEmpty());
        assertTrue(subjects.viewDeck().isEmpty());
    }

    @Test
    void testAddCard() {
        assertEquals(love, emotions.addCard(love));
        assertEquals(1, emotions.getSize());
        assertEquals(happy, emotions.addCard(happy));
        assertEquals(2, emotions.getSize());
        assertEquals(sad, emotions.addCard(sad));
        assertEquals(3, emotions.getSize());
        assertEquals(maths, subjects.addCard(maths));
        assertEquals(1, subjects.getSize());
    }

    @Test
    void testViewDeck() {
        assertTrue(emotions.viewDeck().isEmpty());
        emotions.addCard(angry);
        assertEquals(angry, emotions.viewDeck().get(0));
        assertEquals(1, emotions.getSize());
        emotions.addCard(curious);
        assertEquals(curious, emotions.viewDeck().get(1));
        assertEquals(2, emotions.getSize());
        subjects.addCard(science);
        assertEquals(2, emotions.getSize());
        assertEquals(science, subjects.viewDeck().get(0));
        assertEquals(1, subjects.getSize());
        emotions.addCard(happy);
        assertEquals(3, emotions.getSize());
        assertEquals(happy, emotions.viewDeck().get(2));
    }

    @Test
    void testGetSize() {
        assertEquals(0, emotions.getSize());
        emotions.addCard(love);
        assertEquals(1, emotions.getSize());
        subjects.addCard(maths);
        assertEquals(1, subjects.getSize());
        emotions.addCard(happy);
        assertEquals(2, emotions.getSize());
    }

    @Test
    void testFindCard() {
        emotions.addCard(happy);
        assertEquals(1, emotions.findCardNumber(happy));
        emotions.addCard(sad);
        assertEquals(-1, emotions.findCardNumber(love));
        emotions.addCard(curious);
        assertEquals(3, emotions.findCardNumber(curious));
        subjects.addCard(maths);
        assertEquals(-1, emotions.findCardNumber(maths));
        assertEquals(1, subjects.findCardNumber(maths));
    }

    @Test
    void testEditCard() {
        emotions.addCard(happy);
        assertEquals(happy, emotions.editCard(happy, "joyful", "feeling content"));
        emotions.addCard(sad);
        assertEquals(sad, emotions.editCard(sad, "sad", "feeling discontent"));
        assertEquals(happy, emotions.editCard(happy, "hysterics", "laughing out loud"));
    }

    @Test
    void testDeleteCard() {
        subjects.addCard(maths);
        subjects.addCard(science);
        assertEquals(2, subjects.getSize());
        assertEquals(maths, subjects.deleteCard(maths));
        assertEquals(1, subjects.getSize());
        assertEquals(-1, subjects.findCardNumber(maths));
        assertEquals(science, subjects.viewDeck().get(0));
        assertEquals(science, subjects.deleteCard(science));
        assertEquals(0, subjects.getSize());
        assertEquals(-1, subjects.findCardNumber(science));
    }
}
