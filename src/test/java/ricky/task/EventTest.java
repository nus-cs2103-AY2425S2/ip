package ricky.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testToString() {
        LocalDateTime from = LocalDateTime.parse("2023-12-31T18:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime to = LocalDateTime.parse("2023-12-31T23:59", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Event event = new Event("New Year Party", from, to);
        assertEquals("[E][ ] New Year Party (from: Dec 31 2023 6:00PM to: Dec 31 2023 11:59PM)", event.toString());
    }

    @Test
    public void testStore() {
        LocalDateTime from = LocalDateTime.parse("2023-12-31T18:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime to = LocalDateTime.parse("2023-12-31T23:59", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Event event = new Event("New Year Party", from, to);
        assertEquals("E | 0 | New Year Party | 2023-12-31T18:00 | 2023-12-31T23:59", event.storeInfo());
    }
}
