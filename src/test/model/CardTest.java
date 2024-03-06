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
        assertEquals(0, happy.getStudyCounter());
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
    void testGetStudyCounter() {
        assertEquals(0, happy.getStudyCounter());
    }

    @Test
    void testGetStatus() {
        assertFalse(love.getStatus());
        love.updateStudyCounter();
        love.updateStatus();
        assertFalse(love.getStatus());
        love.updateStudyCounter();
        love.updateStatus();
        assertFalse(love.getStatus());
        love.updateStudyCounter();
        love.updateStatus();
        assertTrue(love.getStatus());
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
    void testUpdateStatus() {
        assertFalse(brave.getStatus());
        brave.updateStatus();
        assertFalse(brave.getStatus());
        brave.updateStudyCounter();
        brave.updateStatus();
        assertFalse(brave.getStatus());
        brave.updateStudyCounter();
        brave.updateStatus();
        assertFalse(brave.getStatus());
        brave.updateStudyCounter();
        brave.updateStatus();
        assertTrue(brave.getStatus());
    }

    @Test
    void testUpdateStudyCounter() {
        assertFalse(happy.getStatus());
        happy.updateStatus();
        assertFalse(happy.getStatus());
        happy.updateStudyCounter();
        happy.updateStatus();
        assertFalse(happy.getStatus());
        happy.updateStudyCounter();
        happy.updateStatus();
        assertFalse(happy.getStatus());
        happy.updateStudyCounter();
        happy.updateStatus();
        assertTrue(happy.getStatus());
        happy.updateStudyCounter();
        happy.updateStatus();
        assertTrue(happy.getStatus());
    }

    @Test
    void testResetStatus() {
        assertFalse(brave.getStatus());
        brave.updateStatus();
        assertFalse(brave.getStatus());
        brave.updateStudyCounter();
        brave.updateStatus();
        assertFalse(brave.getStatus());
        brave.updateStudyCounter();
        brave.updateStatus();
        assertFalse(brave.getStatus());
        brave.updateStudyCounter();
        brave.updateStatus();
        assertTrue(brave.getStatus());
        brave.resetStatus();
        assertFalse(brave.getStatus());
    }

    @Test
    void testResetStudyCounter() {
        assertFalse(happy.getStatus());
        happy.updateStatus();
        assertFalse(happy.getStatus());
        happy.updateStudyCounter();
        happy.updateStatus();
        assertFalse(happy.getStatus());
        happy.updateStudyCounter();
        happy.updateStatus();
        assertFalse(happy.getStatus());
        happy.updateStudyCounter();
        happy.updateStatus();
        assertTrue(happy.getStatus());
        happy.updateStudyCounter();
        happy.updateStatus();
        assertTrue(happy.getStatus());
        happy.resetStudyCounter();
        happy.updateStatus();
        assertFalse(happy.getStatus());
    }
}
