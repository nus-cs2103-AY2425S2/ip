package baymax.task;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    void testDeadlineCreationWithValidDate() {
        Deadline deadline = new Deadline("Finish assignment", "2025-02-07");
        assertEquals("[D][ ] Finish assignment (by: Feb 07 2025)", deadline.toString());
    }

    @Test
    void testDeadlineCreationWithValidDateTime() {
        Deadline deadline = new Deadline("Submit 2103", "2025-02-13 1800");
        assertEquals("[D][ ] Submit 2103 (by: Feb 13 2025 18:00)", deadline.toString());
    }

    @Test
    void testDeadlineCreationWithInvalidDate() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Deadline("Invalid date", "2024-11");
        });

        assertTrue(exception.getMessage().contains("Invalid"));
    }

    @Test
    void testToFileStringFormat() {
        Deadline deadline = new Deadline("Finish project", "2024-10-10");
        assertEquals("D | false | Finish project | 2024-10-10", deadline.toFileString());
    }
}
