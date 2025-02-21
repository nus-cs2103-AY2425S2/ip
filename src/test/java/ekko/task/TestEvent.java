package ekko.task;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    void testConstructor() {
        LocalDateTime start = LocalDateTime.of(2025, 2, 20, 10, 0);
        LocalDateTime end = LocalDateTime.of(2025, 2, 20, 12, 0);
        Event event = new Event("Project meeting", start, end);

        assertEquals("Project meeting", event.printDes(), "Description should match the provided value.");
        assertFalse(event.isDone(), "Newly created event should not be marked as done.");
    }

    @Test
    void testMarkDone() {
        LocalDateTime start = LocalDateTime.of(2025, 3, 15, 9, 0);
        LocalDateTime end = LocalDateTime.of(2025, 3, 15, 11, 0);
        Event event = new Event("Team discussion", start, end);

        event.markDone();
        assertTrue(event.isDone(), "Event should be marked as done after calling markDone().");
    }

    @Test
    void testToString() {
        LocalDateTime start = LocalDateTime.of(2025, 4, 10, 14, 30);
        LocalDateTime end = LocalDateTime.of(2025, 4, 10, 16, 30);
        Event event = new Event("Conference call", start, end);

        String expectedOutput = "[E] [ ] Conference call (from: 2025-04-10T14:30 to: 2025-04-10T16:30)";
        assertEquals(expectedOutput, event.toString(), "toString() should return correctly formatted string.");

        event.markDone();
        String expectedDoneOutput = "[E] [X] Conference call (from: 2025-04-10T14:30 to: 2025-04-10T16:30)";
        assertEquals(expectedDoneOutput, event.toString(), "toString() should update when event is marked done.");
    }

    @Test
    void testInvalidStartEndTime() {
        LocalDateTime start = LocalDateTime.of(2025, 5, 1, 14, 0);
        LocalDateTime end = LocalDateTime.of(2025, 5, 1, 12, 0);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Event("Invalid Event", start, end);
        });

        assertEquals("The start time must be before the end time.", exception.getMessage(),
                "Constructor should throw an AssertionError when start time is after end time.");
    }
}
