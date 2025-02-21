package bobandsteve.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import bobandsteve.exception.InvalidCommandFormatException;

public class TaskTest {
    @Test
    public void createTodo_success() {
        Assertions.assertEquals("[T][ ] test",
                new Todo("test", "[ ]").toString());
    }

    @Test
    public void createTodoToSaveFormat_success() {
        Assertions.assertEquals("T | 0 | test",
                new Todo("test", "[ ]").toSaveFormat());
    }

    @Test
    public void createDeadline_correctDateFormat_success() throws InvalidCommandFormatException {
        Assertions.assertEquals("[D][ ] test (by: Jan 22 2025 08:30 am)",
                new Deadline("test", "[ ]", "2025-01-22 08:30").toString());
    }

    @Test
    public void createDeadline_testToSaveFormat_success() throws InvalidCommandFormatException {
        Assertions.assertEquals("D | 0 | test | 2025-01-22 08:30",
                new Deadline("test", "[ ]", "2025-01-22 08:30").toSaveFormat());
    }

    @Test
    public void createDeadline_incorrectDateFormat_exceptionThrown() {
        try {
            assertEquals("[D][ ] test (by: Jan 22 2025, 08:30 AM)",
                    new Deadline("test", "[ ]", "test").toString());
            fail();
        } catch (InvalidCommandFormatException error) {
            assertEquals("Invalid date-time format. Expected: YYYY-MM-DD HH:MM", error.getMessage());
        }
    }

    @Test
    public void createEvent_testToSaveFormat_success() throws InvalidCommandFormatException {
        Assertions.assertEquals("E | 0 | test | 2025-01-22 08:30 | 2025-01-22 08:30",
                new Event("test", "[ ]", "2025-01-22 08:30", "2025-01-22 08:30").toSaveFormat());
    }

    @Test
    public void createEvent_testSameDate_success() throws InvalidCommandFormatException {
        Assertions.assertEquals("[E][ ] test (from: Jan 22 2025 08:30 am to: Jan 22 2025 08:30 am)",
                new Event("test", "[ ]", "2025-01-22 08:30", "2025-01-22 08:30").toString());
    }

    @Test
    public void createEvent_testDiffDate_success() throws InvalidCommandFormatException {
        Assertions.assertEquals("[E][ ] test (from: Jan 22 2025 08:30 am to: Jan 23 2025 08:30 am)",
                new Event("test", "[ ]", "2025-01-22 08:30", "2025-01-23 08:30").toString());
    }

    @Test
    public void createEvent_endTimeEarlierThanStartTime_exceptionThrown() {
        try {
            assertEquals("[E][ ] test (at: Jan 22 2025, 08:30 AM - 08:30 am)",
                    new Event("test", "[ ]", "2025-01-22 09:30", "2025-01-22 08:30").toString());
            fail();
        } catch (InvalidCommandFormatException error) {
            assertEquals("Start date-time (2025-01-22T09:30) must be before "
                    + "end date-time (2025-01-22T08:30)", error.getMessage());
        }
    }
}
