package julie.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import julie.exception.WrongFormatException;

public class DeadlineTest {
    @Test
    public void testDeadlineCreation() throws WrongFormatException {
        Deadline deadline = new Deadline("Submit report", "10-02-2025 1800");
        assertEquals("[D] [ ] Submit report (by: Feb 10 2025, 06:00 pm)",
                deadline.toString(),
                "Deadline string representation is incorrect!");
    }

    @Test
    public void testMarkDone() throws WrongFormatException {
        Deadline deadline = new Deadline("Submit report", "10-02-2025 1800");
        deadline.markDone();
        assertEquals("[D] [X] Submit report (by: Feb 10 2025, 06:00 pm)",
                deadline.toString(),
                "Marking Deadline as done failed!");
    }

    @Test
    public void testMarkUndone() throws WrongFormatException {
        Deadline deadline = new Deadline("Submit report", "10-02-2025 1800");
        deadline.markDone();
        deadline.markUndone();
        assertEquals("[D] [ ] Submit report (by: Feb 10 2025, 06:00 pm)",
                deadline.toString(),
                "Unmarking Deadline as undone failed!");
    }

    @Test
    public void testToFileFormat() throws WrongFormatException {
        Deadline deadline = new Deadline("Submit report", "10-02-2025 1800");
        assertEquals("D | 0 | Submit report | 10-02-2025 1800",
                deadline.toFileFormat(),
                "ToFileFormat representation is incorrect!");

        deadline.markDone();
        assertEquals("D | 1 | Submit report | 10-02-2025 1800",
                deadline.toFileFormat(),
                "ToFileFormat after marking done is incorrect!");
    }

    @Test
    public void testInvalidDeadlineFormat() {
        Exception exception = assertThrows(WrongFormatException.class, () -> {
            new Deadline("Submit report", "10/02/2025 18:00");
        });

        assertEquals("Invalid date/time format!\nCorrect format: DD-MM-YYYY HHMM",
                exception.getMessage(),
                "Exception message for wrong date format is incorrect!");
    }

    @Test
    public void testMissingDeadlineDateTime() {
        Exception exception = assertThrows(WrongFormatException.class, () -> {
            new Deadline("Submit report", "");
        });

        assertEquals("Invalid date/time format!\nCorrect format: DD-MM-YYYY HHMM",
                exception.getMessage(),
                "Exception message for missing deadline date/time is incorrect!");
    }
}
