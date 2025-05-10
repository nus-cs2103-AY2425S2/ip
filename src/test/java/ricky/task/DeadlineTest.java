package ricky.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testToString() {
        LocalDateTime by = LocalDateTime.parse("2023-12-31T23:59", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Deadline deadline = new Deadline("submit report", by);
        assertEquals("[D][ ] submit report (by: Dec 31 2023 11:59PM)", deadline.toString());
    }

    @Test
    public void testStore() {
        LocalDateTime by = LocalDateTime.parse("2023-12-31T23:59", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Deadline deadline = new Deadline("submit report", by);
        assertEquals("D | 0 | submit report | 2023-12-31T23:59", deadline.storeInfo());
    }
}
