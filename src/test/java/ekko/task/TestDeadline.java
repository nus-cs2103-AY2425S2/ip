package ekko.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeadlineTest {
    private Deadline deadline;
    private LocalDateTime dueDate;

    @BeforeEach
    void setUp() {
        dueDate = LocalDateTime.of(2025, 3, 10, 23, 59);
        deadline = new Deadline("Finish assignment", dueDate);
    }

    @Test
    void testDeadlineCreation() {
        assertEquals("Finish assignment", deadline.printDes());
        assertFalse(deadline.isDone());
    }

    @Test
    void testMarkDone() {
        deadline.markDone();
        assertTrue(deadline.isDone());
    }

    @Test
    void testToStringNotDone() {
        String expected = "[D] [ ] Finish assignment (by: 2025-03-10T23:59)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    void testToStringDone() {
        deadline.markDone();
        String expected = "[D] [X] Finish assignment (by: 2025-03-10T23:59)";
        assertEquals(expected, deadline.toString());
    }
}
