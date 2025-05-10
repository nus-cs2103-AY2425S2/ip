package phil.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * Represents Testing Class for Deadline Class.
 */
public class DeadlineTest {

    /**
     * Tests the toString method of the Deadline class with a new Deadline.
     */
    @Test
    public void toString_newDeadline_correctStringOutput() {
        Deadline deadline = new Deadline("homework", "tomorrow");
        assertEquals("[D][ ] homework (by: tomorrow)", deadline.toString());
        deadline.markDone();
        assertEquals("[D][X] homework (by: tomorrow)", deadline.toString());
        deadline.markNotDone();
        assertEquals("[D][ ] homework (by: tomorrow)", deadline.toString());
    }

    /**
     * Tests the toLoadString method of the Deadline class with a new Deadline.
     */
    @Test
    public void toLoadString_newDeadline_correctStringOutput() {
        Deadline deadline = new Deadline("homework", "tomorrow");
        assertEquals("Deadline -   - homework - tomorrow", deadline.toLoadString());
        deadline.markDone();
        assertEquals("Deadline - X - homework - tomorrow", deadline.toLoadString());
        deadline.markNotDone();
        assertEquals("Deadline -   - homework - tomorrow", deadline.toLoadString());
    }

    /**
     * Tests the toString method of the Deadline class with a new Deadline.
     * This deadline gives the from and to dates with a valid date-time input.
     */
    @Test
    public void toString_newDeadlineWithDateTime_correctStringOutput() {
        Deadline deadline = new Deadline("homework", "2/7/25 1000");
        assertEquals("[D][ ] homework (by: Feb 7, 2025 10.00AM)", deadline.toString());
        deadline.markDone();
        assertEquals("[D][X] homework (by: Feb 7, 2025 10.00AM)", deadline.toString());
        deadline.markNotDone();
        assertEquals("[D][ ] homework (by: Feb 7, 2025 10.00AM)", deadline.toString());
    }

    /**
     * Tests the toLoadString method of the Deadline class with a new Deadline.
     */
    @Test
    public void toLoadString_newDeadlineWithDateTime_correctStringOutput() {
        Deadline deadline = new Deadline("homework", "2/7/25 1000");
        assertEquals("Deadline -   - homework - 2/7/25 1000", deadline.toLoadString());
        deadline.markDone();
        assertEquals("Deadline - X - homework - 2/7/25 1000", deadline.toLoadString());
        deadline.markNotDone();
        assertEquals("Deadline -   - homework - 2/7/25 1000", deadline.toLoadString());
    }
}
