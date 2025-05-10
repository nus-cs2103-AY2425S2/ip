package baymax.task;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    void testValidEventWithDateOnly() {
        Event event = new Event("Meeting", "2024-05-10", "2024-05-12");
        assertEquals("[E][ ] Meeting (from: May 10 2024 to: May 12 2024)", event.toString());
    }

    @Test
    void testValidEventWithDateTime() {
        Event event = new Event("Conference", "2024-05-10 1400", "2024-05-12 1800");
        assertEquals("[E][ ] Conference (from: May 10 2024 14:00 to: May 12 2024 18:00)", event.toString());
    }

    @Test
    void testInvalidDateFormatThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Event("Invalid Event", "15-MAR-2024", "16-MAR-2024")
        );
        assertEquals("Invalid event format! Use 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'.", exception.getMessage());
    }

    @Test
    void testEndDateBeforeStartDateThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Event("Reversed Event", "2024-05-12", "2024-05-10")
        );
        assertEquals("Error: 'From' date must be before 'To' date.", exception.getMessage());
    }

    @Test
    void testToFileStringWithDate() {
        Event event = new Event("Project Deadline", "2024-06-01", "2024-06-10");
        assertEquals("E | false | Project Deadline | 2024-06-01 | 2024-06-10", event.toFileString());
    }

    @Test
    void testToFileStringWithDateTime() {
        Event event = new Event("Hackathon", "2024-06-01 0900", "2024-06-10 2100");
        assertEquals("E | false | Hackathon | 2024-06-01 0900 | 2024-06-10 2100", event.toFileString());
    }
}

