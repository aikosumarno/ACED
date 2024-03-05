package persistence;

import model.Card;
import model.Deck;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkDeck(String name, Deck deck) {
        assertEquals(name, deck.getName());
    }

    protected void checkCard(String question, String answer, Boolean status, int studyCounter, Card card) {
        assertEquals(question, card.getQuestion());
        assertEquals(answer, card.getAnswer());
        assertEquals(status, card.getStatus());
        assertEquals(studyCounter, card.getStudyCounter());
    }
}
