package charlie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void testDeadlineCreation() {
        Deadline deadline = new Deadline("do homework /by 2024-02-01");
        assertEquals("[D][ ] do homework (by: Feb 1 2024)", deadline.toString());
    }

    @Test
    void testDeadlineWithMarking() {
        Deadline deadline = new Deadline("submit report", "2024-12-25", true);
        assertEquals("[D][X] submit report (by: Dec 25 2024)", deadline.toString());
    }

    @Test
    void testWriteToFile() {
        Deadline deadline = new Deadline("finish project", "2025-05-10", false);
        assertEquals("D|0|finish project|2025-05-10\n", deadline.writeToFile());

        Deadline markedDeadline = new Deadline("finish project", "2025-05-10", true);
        assertEquals("D|1|finish project|2025-05-10\n", markedDeadline.writeToFile());
    }

    @Test
    void testInvalidFormatHandling() {
        try {
            new Deadline("invalid deadline format");
        } catch (Exception e) {
            assertEquals(ArrayIndexOutOfBoundsException.class, e.getClass());
        }
    }

    @Test
    void testWriteToFileWithExceptionHandling() {
        Deadline deadline = new Deadline("test task", "2025-06-15", false);
        String expectedOutput = "D|0|test task|2025-06-15\n";
        assertEquals(expectedOutput, deadline.writeToFile());
    }
}
