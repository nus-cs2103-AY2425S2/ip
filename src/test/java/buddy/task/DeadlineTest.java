package buddy.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import buddy.exception.BuddyException;

class DeadlineTest {

    @Test
    void constructor_validInput_createsDeadline() throws BuddyException {
        LocalDateTime dueDate = LocalDateTime.of(2024, 2, 15, 23, 59);
        Deadline deadline = new Deadline("Submit Report", dueDate);

        assertEquals("Submit Report", deadline.getDescription());
        assertEquals(dueDate, deadline.by);
    }

    @Test
    void toStorageStringFormat_validDeadline_correctFormat() throws BuddyException {
        LocalDateTime dueDate = LocalDateTime.of(2024, 2, 15, 23, 59);
        Deadline deadline = new Deadline("Submit Report", dueDate);
        deadline.markTaskAsDone(); // Simulate completion

        String expected = "D | 1 | Submit Report | 2024-02-15 2359\n";
        assertEquals(expected, deadline.toStorageStringFormat());
    }

    @Test
    void toString_validDeadline_correctFormat() throws BuddyException {
        LocalDateTime dueDate = LocalDateTime.of(2024, 2, 15, 23, 59);
        Deadline deadline = new Deadline("Submit Report", dueDate);

        String expected = "[D][ ] Submit Report (by: Feb 15 2024 11:59 PM)";
        assertEquals(expected, deadline.toString());

        deadline.markTaskAsDone();
        expected = "[D][X] Submit Report (by: Feb 15 2024 11:59 PM)";
        assertEquals(expected, deadline.toString());
    }
}
