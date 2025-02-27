package nyanko.task;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EventTest {

    @Test
    void testEventCreation() {
        Event event = new Event("Project meeting", "2025-02-22 1400", "2025-02-22 1600");

        assertEquals("Project meeting", event.getDescription());
        assertEquals("[E][ ] Project meeting (from: Feb 22 2025, 2:00 pm to: Feb 22 2025, 4:00 pm)", event.toString());
    }

    @Test
    void testEventToSaveFormat() {
        Event event = new Event("Project meeting", "2025-02-22 1400", "2025-02-22 1600");

        assertEquals("Event|0|Project meeting|2025-02-22 1400|2025-02-22 1600", event.toSaveFormat());
    }

    @Test
    void testEventFromSaveFormat() throws InvalidTaskFormatException {
        Event event = (Event) Event.fromSaveFormat("Event|1|Hackathon|2025-03-10 1000|2025-03-10 1800");

        assertEquals("Hackathon", event.getDescription());
        assertEquals("[E][X] Hackathon (from: Mar 10 2025, 10:00 am to: Mar 10 2025, 6:00 pm)", event.toString());
        assertTrue(event.isDone());
    }

    @Test
    void testInvalidEventDateFormat() {
        Exception exception = assertThrows(DateTimeParseException.class, () -> {
            new Event("Invalid Event", "22-02-2025 1400", "22-02-2025 1600");
        });

        assertTrue(exception.getMessage().contains("could not be parsed"));
    }
}
