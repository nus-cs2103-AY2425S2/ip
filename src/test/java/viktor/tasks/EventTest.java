package viktor.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testEventConstructor() {
        EventTask event = new EventTask("meeting", "1/2/2025 1000", "1/2/2025 1200");
        assertNotNull(event);
    }

    @Test
    public void testGetType() {
        EventTask event = new EventTask("meeting", "1/2/2025 1000", "1/2/2025 1200");
        assertEquals("E", event.getType());
    }

    @Test
    public void testGetDescription() {
        EventTask event = new EventTask("meeting", "1/2/2025 1000", "1/2/2025 1200");
        assertEquals("meeting", event.getDescription());
    }

    @Test
    public void testMatchesDate() {
        EventTask event = new EventTask("meeting", "1/2/2025 1000", "1/2/2025 1200");
        assertTrue(event.matchesDate(LocalDate.of(2025, 2, 1)));
    }

    @Test
    public void testToString() {
        EventTask event = new EventTask("meeting", "1/2/2025 1000", "1/2/2025 1200");
        assertEquals("[E][ ] meeting (from: Feb. 1 2025, 10:00 to: Feb. 1 2025, 12:00)", event.toString());
    }

    @Test
    public void testBeDone() {
        EventTask event = new EventTask("meeting", "1/2/2025 1000", "1/2/2025 1200");
        event.beDone();
        assertEquals("[E][X] meeting (from: Feb. 1 2025, 10:00 to: Feb. 1 2025, 12:00)", event.toString());
    }

    @Test
    public void testBeUndone() {
        EventTask event = new EventTask("meeting", "1/2/2025 1000", "1/2/2025 1200");
        event.beDone();
        event.beUndone();
        assertEquals("[E][ ] meeting (from: Feb. 1 2025, 10:00 to: Feb. 1 2025, 12:00)", event.toString());
    }

    @Test
    public void testToSave() {
        EventTask event = new EventTask("meeting", "1/2/2025 1000", "1/2/2025 1200");
        assertEquals("E |   | meeting | Feb. 1 2025, 10:00 | Feb. 1 2025, 12:00", event.toSave());
    }
}
