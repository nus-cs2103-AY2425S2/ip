package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import exceptions.TaskException;

public class EventTest {
    @Test
    public void testCreateValidEvent() throws TaskException {
        Event event = Event.create("event project meeting /from 6/8/2023 1400 /to 6/8/2023 1600 /priority HIGH");
        assertEquals("[E][ ] project meeting (Priority: High) "
                + "(from: 6 August 2023, 2:00pm to: 6 August 2023, 4:00pm)", event.toString());
    }

    @Test
    public void testCreateEventWithoutPriorityUsesDefault() throws TaskException {
        Event event = Event.create("event project meeting /from 6/8/2023 1400 /to 6/8/2023 1600");
        assertEquals("[E][ ] project meeting (Priority: Low) "
                + "(from: 6 August 2023, 2:00pm to: 6 August 2023, 4:00pm)", event.toString());
    }

    @Test
    public void testCreateEventWithInvalidStartDateThrowsException() {
        Exception exception = assertThrows(TaskException.class, () -> {
            Event.create("event meeting /from 35/8/2023 1400 /to 6/8/2023 1600 /priority HIGH");
        });
        assertEquals("Invalid date-time format bro! Use: d/M/yyyy HHmm.", exception.getMessage());
    }

    @Test
    public void testCreateEventWithEndDateBeforeStartThrowsException() {
        Exception exception = assertThrows(TaskException.class, () -> {
            Event.create("event meeting /from 6/8/2023 1600 /to 6/8/2023 1400 /priority HIGH");
        });
        assertEquals("Are you a time traveler cos an end time cannot be before a start time!",
                exception.getMessage());
    }

    @Test
    public void testCreateEventWithMissingDatesThrowsException() {
        Exception exception = assertThrows(TaskException.class, () -> {
            Event.create("event meeting /priority HIGH");
        });
        assertEquals("PLEASE BRUH! Use: event <description> /from <start> /to <end> /priority "
                + "<LOW|MEDIUM|HIGH|URGENT> ._.", exception.getMessage());
    }
}
