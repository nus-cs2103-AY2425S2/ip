package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TaskEventTest {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private TaskEvent task;

    @BeforeEach
    public void initialize() {
        task = new TaskEvent("meet friends",
                LocalDateTime.parse("2000-01-01 12:00", formatter),
                LocalDateTime.parse("2000-01-01 18:00", formatter));
    }

    @Test
    public void getFullName_functionCall_success() {
        String expectedName = "[E][ ] meet friends (from: 2000-01-01 12:00 | to: 2000-01-01 18:00)";
        assertEquals(expectedName, task.getFullName());
    }

    @Test
    public void getFromTime_functionCall_success() {
        String expectedFromTime = "2000-01-01 12:00";
        assertEquals(expectedFromTime, task.getFromTime());
    }

    @Test
    public void getToTime_functionCall_success() {
        String expectedToTime = "2000-01-01 18:00";
        assertEquals(expectedToTime, task.getToTime());
    }

    @Test
    public void markDone_functionCall_success() {
        String expectedName = "[E][X] meet friends (from: 2000-01-01 12:00 | to: 2000-01-01 18:00)";
        task.markDone();
        assertEquals(expectedName, task.getFullName());
    }

    @Test
    public void unmarkDone_functionCall_success() {
        String expectedName = "[E][ ] meet friends (from: 2000-01-01 12:00 | to: 2000-01-01 18:00)";
        task.markDone();
        task.unmarkDone();
        assertEquals(expectedName, task.getFullName());
    }

    @Test
    public void getName_functionCall_success() {
        String expectedName = "meet friends";
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
