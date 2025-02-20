package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import exceptions.TaskException;

public class DeadlineTest {
    @Test
    public void testCreateValidDeadline() throws TaskException {
        Deadline deadline = Deadline.create("deadline return book /by 2/12/2019 1800 /priority MEDIUM");
        assertEquals("[D][ ] return book (Priority: Medium) (by: 2 December 2019, 6:00pm)",
                deadline.toString());
    }

    @Test
    public void testCreateDeadlineWithoutPriorityUsesDefault() throws TaskException {
        Deadline deadline = Deadline.create("deadline return book /by 2/12/2019 1800");
        assertEquals("[D][ ] return book (Priority: Low) (by: 2 December 2019, 6:00pm)",
                deadline.toString());
    }

    @Test
    public void testCreateDeadlineWithInvalidDateThrowsException() {
        Exception exception = assertThrows(TaskException.class, () -> {
            Deadline.create("deadline return book /by 35/12/2019 1800");
        });
        assertEquals("Invalid date-time format bro! Use: d/M/yyyy HHmm.", exception.getMessage());
    }

    @Test
    public void testCreateDeadlineWithMissingDateThrowsException() {
        Exception exception = assertThrows(TaskException.class, () -> {
            Deadline.create("deadline return book /priority HIGH");
        });
        assertEquals("PLEASE BRUH! Use: deadline <description> /by <d/M/yyyy HHmm> /priority "
                + "<LOW|MEDIUM|HIGH|URGENT> ._.", exception.getMessage());
    }
}
