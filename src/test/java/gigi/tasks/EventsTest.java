package gigi.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EventsTest {
    private Events event;

    @BeforeEach
    void setUp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.event = new Events("Team Meeting",
                LocalDateTime.parse("2024-03-25 14:00", formatter),
                LocalDateTime.parse("2024-03-25 16:00", formatter));
    }

    @Test
    void constructor_validDescriptionAndEventTimes_taskInitialized() {
        assertNotNull(event);
        assertEquals("[E][ ] Team Meeting (from: 25 Mar 2024, 2:00pm to: 25 Mar 2024, 4:00pm)", event.toString());
    }

    @Test
    void markDone_taskMarkedDone_statusUpdated() {
        event.markDone(0);
        assertEquals("[E][X] Team Meeting (from: 25 Mar 2024, 2:00pm to: 25 Mar 2024, 4:00pm)", event.toString());
    }

    @Test
    void markUndone_taskPreviouslyMarkedDone_statusReverted() {
        event.markDone(0); // Mark as done first
        event.markUndone(0);
        assertEquals("[E][ ] Team Meeting (from: 25 Mar 2024, 2:00pm to: 25 Mar 2024, 4:00pm)",
                event.toString());
    }

    @Test
    void convertToText_taskNotDone_correctSaveFormat() {
        assertEquals("[E] | false | Team Meeting | 25 Mar 2024, 2:00pm | 25 Mar 2024, 4:00pm",
                event.convertToText());
    }

    @Test
    void convertToText_taskDone_correctSaveFormat() {
        event.markDone(0);
        assertEquals("[E] | true | Team Meeting | 25 Mar 2024, 2:00pm | 25 Mar 2024, 4:00pm",
                event.convertToText());
    }
}
