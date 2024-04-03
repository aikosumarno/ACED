package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Event class
 */
public class EventTest {
	private Event e;
    private Event e2;
    private Event e3;
	private Date d;
	
	//NOTE: these tests might fail if time at which line (2) below is executed
	//is different from time that line (1) is executed.  Lines (1) and (2) must
	//run in same millisecond for this test to make sense and pass.
	
	@BeforeEach
	public void runBefore() {
		e = new Event("Deleted 'Define happy' card from emotions deck.");   // (1)
        e2 = new Event("Added 'Define angry' card to emotions deck.");
		d = Calendar.getInstance().getTime();   // (2)
	}
	
	@Test
	public void testEvent() {
		assertEquals("Deleted 'Define happy' card from emotions deck.", e.getDescription());
		assertEquals(d, e.getDate());
	}

	@Test
	public void testToString() {
		assertEquals(d.toString() + "\n" + "Deleted 'Define happy' card from emotions deck.", e.toString());
	}

    @Test
    public void testEquals() {
        e3 = e;
        assertFalse(e.equals(e2));
        assertFalse(e.equals(d));
        assertTrue(e.equals(e3));
    }

    @Test
    public void testHashCode() {
        assertEquals(13 * e.getDate().hashCode() + e.getDescription().hashCode(), e.hashCode());
    }
}
