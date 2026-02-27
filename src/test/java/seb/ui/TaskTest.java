package seb.ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDateTime;

class TaskTest {

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Deadline("Initial Task Description", "Sun, 16 Feb 2025, 14:00", false);
    }

    @Test
    void update_description_shouldUpdateTaskDescription() throws SebException {
        task.update("desc", "Updated Description");
        assertEquals("Updated Description", task.getDescription());
    }

    @Test
    void update_pastDeadline_shouldThrowException() {
        String pastDate = "01-01-2020 1000"; // Past date
        Exception exception = assertThrows(SebException.class, () -> task.update("deadline", pastDate));
        assertEquals("Date entered cannot be in the past!", exception.getMessage());
    }
}

