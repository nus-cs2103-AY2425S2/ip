package amara.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class EventTest {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private Event event = new Event(
            "CM4288 Final Year Project Meeting",
            LocalDateTime.parse("2025-02-02 1500", this.formatter),
            LocalDateTime.parse("2025-02-02 1600", this.formatter)
    );

    @Test
    public void testEventFunctionality() {
        assertEquals("[E][ ] CM4288 Final Year Project Meeting "
                + "(from: Feb 2 2025, 3:00 PM to: Feb 2 2025, 4:00 PM)",
                event.toString());

        this.event.markTask();
        assertEquals("[E][X] CM4288 Final Year Project Meeting "
                + "(from: Feb 2 2025, 3:00 PM to: Feb 2 2025, 4:00 PM)",
                event.toString());

        this.event.unmarkTask();
        assertEquals("[E][ ] CM4288 Final Year Project Meeting "
                + "(from: Feb 2 2025, 3:00 PM to: Feb 2 2025, 4:00 PM)",
                event.toString());

        this.event.getSavedFormat();
        assertEquals("E,0,CM4288 Final Year Project Meeting,"
                + "2025-02-02T15:00,2025-02-02T16:00",
                event.getSavedFormat());
    }
}
