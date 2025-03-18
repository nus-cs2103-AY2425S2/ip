package clank.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@code Deadline} class.
 */
public class DeadlineTest {

    /**
     * Tests the creation of a Deadline task and its string representation.
     */
    @Test
    public void testDeadlineCreation() {
        Deadline deadline = new Deadline("Submit report", "1/3/2025 1800");
        assertEquals("[D][ ] Submit report (by: 1 March 2025, 6:00 PM)", deadline.toString());
    }

    /**
     * Tests that the save format of a Deadline task is correctly formatted.
     */
    @Test
    public void testSaveFormat() {
        Deadline deadline = new Deadline("Submit report", "1/3/2025 1800");
        assertEquals("D|false|Submit report|1/3/2025 1800", deadline.toSaveFormat());
    }
}
