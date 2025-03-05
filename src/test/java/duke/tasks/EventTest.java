package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import g.tasks.Event;

public class EventTest {

    @Test
    public void testToFileString() {
        Event event = new Event("Team meeting", "2025-02-15", "2025-02-16", false);
        assertEquals("E | 0 | Team meeting | 2025-02-15 | 2025-02-16", event.toFileString());

        Event completedEvent = new Event("Hackathon", "2025-03-05", "2025-03-06", true);
        assertEquals("E | 1 | Hackathon | 2025-03-05 | 2025-03-06", completedEvent.toFileString());
    }

    @Test
    public void testToString() {
        Event event = new Event("Conference", "2025-04-01", "2025-04-03", false);
        assertEquals("[E][ ] Conference (from: Apr 01 2025 to: Apr 03 2025)", event.toString());

        Event completedEvent = new Event("Workshop", "2025-05-10", "2025-05-12", true);
        assertEquals("[E][X] Workshop (from: May 10 2025 to: May 12 2025)", completedEvent.toString());
    }
}
