package perrygpt.tasks;

import org.junit.jupiter.api.Test;
import perrygpt.exceptions.PerryGPTException;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void constructor_shouldParseDateCorrectly() throws PerryGPTException {
        String description = "Submit report";
        String by = "2025-12-25 1800"; // Input in "yyyy-MM-dd HHmm" format
        boolean isDone = false;

        Deadline deadline = new Deadline(description, by, isDone);

        // Check description and isDone flag
        assertEquals(description, deadline.description);
        assertFalse(deadline.isDone);

        // Check if the date was parsed correctly
        assertEquals("Dec 25 2025, 6:00 PM", deadline.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")));
    }

    @Test
    void toFileFormat_shouldReturnCorrectFormat() throws PerryGPTException {
        String description = "Submit assignment";
        String by = "2023-10-15 0900"; // Input in "yyyy-MM-dd HHmm" format
        boolean isDone = true;

        Deadline deadline = new Deadline(description, by, isDone);

        // Expected output format: "D | 1 | Submit assignment | 2023-10-15 0900"
        String expected = "D | 1 | Submit assignment | 2023-10-15 0900";
        assertEquals(expected, deadline.toFileFormat());
    }
}