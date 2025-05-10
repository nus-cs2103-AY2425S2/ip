package laffy.tasklist.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void task_descAndAt_taskWithDescAndAt() {
        Event task = new Event("task desc",
                LocalDateTime.parse("2002-06-21T15:40"),
                LocalDateTime.parse("2005-09-05T00:00"));
        assertEquals("task desc", task.getDescription());
        assertEquals(
                "[E][ ] task desc (from: Friday, 21/06/2002, 03:40pm to: Monday, 05/09/2005, 12:00am)",
                task.toString()
        );
    }

    @Test
    public void task_descIsDoneAndAt_taskWithDescIsDoneAndAt() {
        Event task = new Event("task desc", true,
                LocalDateTime.parse("2002-06-21T15:40"),
                LocalDateTime.parse("2005-09-05T00:00"));
        assertEquals("task desc", task.getDescription());
        assertTrue(task.isDone());
        assertEquals(
                "[E][X] task desc (from: Friday, 21/06/2002, 03:40pm to: Monday, 05/09/2005, 12:00am)",
                task.toString()
        );
    }

    @Test
    public void markAsDone_task_taskIsDone() {
        Event task = new Event("task desc",
                LocalDateTime.parse("2002-06-21T15:40"),
                LocalDateTime.parse("2005-09-05T00:00"));
        assertFalse(task.isDone());
        task.markAsDone();
        assertTrue(task.isDone());
    }

    @Test
    public void markAsUndone_task_taskIsUndone() {
        Event task = new Event("task desc", true,
                LocalDateTime.parse("2002-06-21T15:40"),
                LocalDateTime.parse("2005-09-05T00:00"));
        assertTrue(task.isDone());
        task.markAsUndone();
        assertFalse(task.isDone());
    }

}
