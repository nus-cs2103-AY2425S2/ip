package gigi.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeadlinesTest {
    private Deadlines deadline;

    @BeforeEach
    void setUp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.deadline = new Deadlines("Submit report", LocalDateTime.parse("2024-03-25 23:59", formatter));
    }

    @Test
    void constructor_validDescriptionAndDeadline_taskInitialized() {
        assertNotNull(deadline);
        assertEquals("[D][ ] Submit report (by: 25 Mar 2024, 11:59pm)", deadline.toString());
    }

    @Test
    void markDone_taskMarkedDone_statusUpdated() {
        deadline.markDone(0);
        assertEquals("[D][X] Submit report (by: 25 Mar 2024, 11:59pm)", deadline.toString());
    }

    @Test
    void markUndone_taskPreviouslyMarkedDone_statusReverted() {
        deadline.markDone(0); // Mark as done first
        deadline.markUndone(0);
        assertEquals("[D][ ] Submit report (by: 25 Mar 2024, 11:59pm)", deadline.toString());
    }

    @Test
    void convertToText_taskNotDone_correctSaveFormat() {
        assertEquals("[D] | false | Submit report | 25 Mar 2024, 11:59pm", deadline.convertToText());
    }

    @Test
    void convertToText_taskDone_correctSaveFormat() {
        deadline.markDone(0);
        assertEquals("[D] | true | Submit report | 25 Mar 2024, 11:59pm", deadline.convertToText());
    }
}
