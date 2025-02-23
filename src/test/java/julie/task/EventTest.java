package julie.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import julie.exception.WrongFormatException;


public class EventTest {

    @Test
    public void testEventCreation() throws WrongFormatException {
        Event event = new Event("Project meeting", "10-02-2025 1400", "10-02-2025 1600");
        assertEquals("[L] [E] [ ] Project meeting (from: Feb 10 2025, 02:00 pm to: Feb 10 2025, 04:00 pm)",
                event.toString(),
                "Event string representation is incorrect!");
    }

    @Test
    public void testMarkDone() throws WrongFormatException {
        Event event = new Event("Project meeting", "10-02-2025 1400", "10-02-2025 1600");
        event.markDone();
        assertEquals("[L] [E] [X] Project meeting (from: Feb 10 2025, 02:00 pm to: Feb 10 2025, 04:00 pm)",
                event.toString(),
                "Marking Event as done failed!");
    }

    @Test
    public void testMarkUndone() throws WrongFormatException {
        Event event = new Event("Project meeting", "10-02-2025 1400", "10-02-2025 1600");
        event.markDone();
        event.markUndone();
        assertEquals("[L] [E] [ ] Project meeting (from: Feb 10 2025, 02:00 pm to: Feb 10 2025, 04:00 pm)",
                event.toString(),
                "Unmarking Event as undone failed!");
    }

    @Test
    public void testToFileFormat() throws WrongFormatException {
        Event event = new Event("Project meeting", "10-02-2025 1400", "10-02-2025 1600");
        assertEquals("E | 0 | Project meeting | 10-02-2025 1400 | 10-02-2025 1600 | L",
                event.toFileFormat(),
                "ToFileFormat representation is incorrect!");

        event.markDone();
        assertEquals("E | 1 | Project meeting | 10-02-2025 1400 | 10-02-2025 1600 | L",
                event.toFileFormat(),
                "ToFileFormat after marking done is incorrect!");
    }

    @Test
    public void testInvalidEventFormat() {
        Exception exception = assertThrows(WrongFormatException.class, () -> {
            new Event("Project meeting", "10/02/2025 14:00", "10-02-2025 16:00");
        });

        assertEquals("Invalid date/time format!\nCorrect format: DD-MM-YYYY HHMM",
                exception.getMessage(),
                "Exception message for wrong date format is incorrect!");
    }

    @Test
    public void testMissingEventDateTime() {
        Exception exception = assertThrows(WrongFormatException.class, () -> {
            new Event("Project meeting", "", "");
        });

        assertEquals("Invalid date/time format!\nCorrect format: DD-MM-YYYY HHMM",
                exception.getMessage(),
                "Exception message for missing event start and end times is incorrect!");
    }

    @Test
    public void testEndDateBeforeStartDate() {
        Exception exception = assertThrows(WrongFormatException.class, () -> {
            new Event("Project meeting", "10-02-2025 1600", "10-02-2025 1400");
        });

        assertEquals("The end date/time of an event must be after the start date/time!",
                exception.getMessage(),
                "Exception message for end date before start date is incorrect!");
    }
}
