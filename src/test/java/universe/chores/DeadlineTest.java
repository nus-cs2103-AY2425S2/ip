package universe.chores;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

/**
 * Unit test class for Deadline.
 * This class tests the string representation and file format conversion of a Deadline object.
 */
public class DeadlineTest {
    private LocalDateTime dateTime = LocalDateTime.parse("31 Jan 2025 1600",
            DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
    private Deadline deadline = new Deadline("ip week 3", dateTime);

    /**
     * Tests the toString() method of the Deadline class.
     * Ensures that the string representation matches the expected format.
     */
    @Test
    public void toStringTest() {
        assertEquals("[D][ ] ip week 3 (by: 31-1-2025 16:00)",
                deadline.toString());
    }

    /**
     * Tests the toFileString() method of the Deadline class.
     * Ensures that the file format representation matches the expected format.
     */
    @Test
    public void toFileStringTest() {
        assertEquals("D | 0 | ip week 3 | 31-1-2025 16:00",
                deadline.toFileString());
    }
}
