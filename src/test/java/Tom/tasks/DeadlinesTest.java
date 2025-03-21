package Tom.tasks;

import Tom.TomException;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the functionality of the Deadlines class.
 */
public class DeadlinesTest {

    @Test
    public void testDeadlineCreationWithValidDateString() throws TomException {
        Deadlines deadline = new Deadlines("Submit report", false, "2025-12-31");
        assertEquals("Submit report", deadline.getDescription()); // Check description
        assertFalse(deadline.getStringStatus()); // Check initial status (should be incomplete)
        assertEquals("[D][ ] Submit report (by: Dec 31 2025)", deadline.toString()); // Check string representation
    }

    @Test
    public void testDeadlineCreationWithInvalidDateString() {
        Exception exception = assertThrows(TomException.class, () -> {
            new Deadlines("Submit report", false, "invalid-date");
        });
        assertEquals("Invalid date format! Use 'yyyy-MM-dd' (e.g., 2025-02-15).", exception.getMessage()); // Check exception message
    }

    @Test
    public void testDeadlineCreationWithLocalDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2025, 12, 31, 23, 59);
        Deadlines deadline = new Deadlines("Submit report", false, dateTime);
        assertEquals("Submit report", deadline.getDescription()); // Check description
        assertFalse(deadline.getStringStatus()); // Check initial status (should be incomplete)
        assertEquals("[D][ ] Submit report (by: Dec 31 2025)", deadline.toString()); // Check string representation
    }

    @Test
    public void testMarkAsCompleted() throws TomException {
        Deadlines deadline = new Deadlines("Submit report", false, "2025-12-31");
        deadline.markTask(); // Mark the task as completed
        assertTrue(deadline.getStringStatus()); // Verify the task is completed
        assertEquals("[D][X] Submit report (by: Dec 31 2025)", deadline.toString()); // Check string representation
    }

    @Test
    public void testMarkAsIncomplete() throws TomException {
        Deadlines deadline = new Deadlines("Submit report", true, "2025-12-31");
        deadline.unmarkTask(); // Mark the task as incomplete
        assertFalse(deadline.getStringStatus()); // Verify the task is incomplete
        assertEquals("[D][ ] Submit report (by: Dec 31 2025)", deadline.toString()); // Check string representation
    }
}