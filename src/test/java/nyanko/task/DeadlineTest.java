package nyanko.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    void testToString() {
        Deadline deadline = new Deadline("Submit report", "2025-02-25 2359");
        assertEquals("[D][ ] Submit report (by: Feb 25 2025, 11:59 pm)", deadline.toString());
    }

    @Test
    void testToSaveFormat() {
        Deadline deadline = new Deadline("Submit report", "2025-02-25 2359");
        assertEquals("Deadline|0|Submit report|2025-02-25 2359", deadline.toSaveFormat());
    }

    @Test
    void testFromSaveFormatValid() throws InvalidTaskFormatException {
        Task task = Deadline.fromSaveFormat("Deadline|1|Finish homework|2025-12-31 1800");
        assertTrue(task instanceof Deadline);
        assertEquals("[D][X] Finish homework (by: Dec 31 2025, 6:00 pm)", task.toString());
    }

    @Test
    void testFromSaveFormatInvalid() {
        assertThrows(InvalidTaskFormatException.class, () -> {
            Deadline.fromSaveFormat("Deadline|1|MissingDate");
        });
    }

    @Test
    void testInvalidDateFormat() {
        assertThrows(DateTimeParseException.class, () -> {
            new Deadline("Submit report", "2025-02-XX 1200"); // Invalid date format
        });
    }
}
