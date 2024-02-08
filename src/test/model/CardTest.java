package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    private Card happy;
    private Card brave;
    private Card love;

    @BeforeEach
    void runBefore() {
        happy = new Card("happy", "feeling content");
        brave = new Card("brave", "to be courageous");
        love = new Card("love", "deep affection");
    }

    @Test
    void testCard() {
        assertEquals("happy", happy.getQuestion());
        assertEquals("feeling content", happy.getAnswer());
        assertFalse(happy.getStatus());
    }

    @Test
    void testGetQuestion() {
        assertEquals("happy", happy.getQuestion());
        assertEquals("brave", brave.getQuestion());
        assertEquals("love", love.getQuestion());
    }

    @Test
    void testGetAnswer() {
        assertEquals("feeling content", happy.getAnswer());
        assertEquals("to be courageous", brave.getAnswer());
        assertEquals("deep affection", love.getAnswer());
    }

    @Test
    void testGetStatus() {
        assertFalse(love.getStatus());
        love.changeStatus(true);
        assertTrue(love.getStatus());
        love.changeStatus(false);
        assertFalse(love.getStatus());
    }

    @Test
    void testEditQuestion() {
        love.editQuestion("fond");
        assertEquals("fond", love.getQuestion());
        love.editQuestion("love");
        assertEquals("love", love.getQuestion());
        brave.editQuestion("fearless");
        assertEquals("fearless", brave.getQuestion());
    }

    @Test
    void testEditAnswer() {
        love.editAnswer("like very much");
        assertEquals("like very much", love.getAnswer());
        happy.editAnswer("feeling cheerful");
        assertEquals("feeling cheerful", happy.getAnswer());
    }

    @Test
    void testSetStatus() {
        assertFalse(brave.getStatus());
        brave.changeStatus(true);
        assertTrue(brave.getStatus());
        brave.changeStatus(false);
        assertFalse(brave.getStatus());
    }
}
