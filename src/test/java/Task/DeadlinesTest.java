package task;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTest {

    @Test
    public void testMark() {
        Deadlines d = new Deadlines("Submit report", LocalDateTime.parse("2025-03-15T23:59:00"));
        d.markAsDone();
        assertEquals("[D][X] Submit report (by: Mar 15 2025 11:59 pm)", d.toString(),
                "toString() does not match expected format.");
    }

    @Test
    public void testUnmark() {
        Deadlines d = new Deadlines("Submit report", LocalDateTime.parse("2025-03-15T23:59:00"));
        d.markAsDone();
        d.markAsIncomplete();
        assertEquals("[D][ ] Submit report (by: Mar 15 2025 11:59 pm)", d.toString(),
                "toString() does not match expected format after unmarking.");
    }

    @Test
    public void testUpdateDeadline() {
        Deadlines d = new Deadlines("Submit report", LocalDateTime.parse("2025-03-15T23:59:00"));
        d.updateBy(LocalDateTime.parse("2025-03-20T18:00:00"));
        assertEquals("[D][ ] Submit report (by: Mar 20 2025 06:00 pm)", d.toString(),
                "Deadline update not reflected in toString().");
    }

    @Test
    public void testDifferentDateFormats() {
        Deadlines d1 = new Deadlines("Task 1", LocalDateTime.parse("2023-12-31T23:59:00"));
        assertEquals("[D][ ] Task 1 (by: Dec 31 2023 11:59 pm)", d1.toString(),
                "Mismatch in expected date format for year-end date.");

        Deadlines d2 = new Deadlines("Task 2", LocalDateTime.parse("2024-01-01T00:01:00"));
        assertEquals("[D][ ] Task 2 (by: Jan 01 2024 12:01 am)", d2.toString(),
                "Mismatch in expected date format for start of the year.");
    }
}
