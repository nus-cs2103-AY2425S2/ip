package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TaskDeadlineTest {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private TaskDeadline task;

    @BeforeEach
    public void initialize() {
        task = new TaskDeadline("do homework",
                LocalDateTime.parse("2000-01-01 12:00", formatter));
    }

    @Test
    public void getFullName_functionCall_success() {
        String expectedName = "[D][ ] do homework (by: 2000-01-01 12:00)";
        assertEquals(expectedName, task.getFullName());
    }

    @Test
    public void getDeadline_functionCall_success() {
        String expectedDeadline = "2000-01-01 12:00";
        assertEquals(expectedDeadline, task.getDeadline());
    }

    @Test
    public void markDone_functionCall_success() {
        String expectedName = "[D][X] do homework (by: 2000-01-01 12:00)";
        task.markDone();
        assertEquals(expectedName, task.getFullName());
    }

    @Test
    public void unmarkDone_functionCall_success() {
        String expectedName = "[D][ ] do homework (by: 2000-01-01 12:00)";
        task.markDone();
        task.unmarkDone();
        assertEquals(expectedName, task.getFullName());
    }

    @Test
    public void getName_functionCall_success() {
        String expectedName = "do homework";
        assertEquals(expectedName, task.getName());
    }

    @Test
    public void getDone_functionCall_success() {
        boolean isDone = true;
        task.markDone();
        assertEquals(isDone, task.getDone());

        isDone = false;
        task.unmarkDone();
        assertEquals(isDone, task.getDone());
    }
}
