package laffy.tasklist.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void task_descAndBy_taskWithDescAndBy() {
        Deadline task = new Deadline("task desc", LocalDateTime.parse("2002-06-21T15:40"));
        assertEquals("task desc", task.getDescription());
        assertEquals("[D][ ] task desc (by: Friday, 21/06/2002, 03:40pm)", task.toString());
    }

    @Test
    public void task_descIsDoneAndBy_taskWithDescIsDoneAndBy() {
        Deadline task = new Deadline("task desc", true, LocalDateTime.parse("2002-06-21T15:40"));
        assertEquals("task desc", task.getDescription());
        assertTrue(task.isDone());
        assertEquals("[D][X] task desc (by: Friday, 21/06/2002, 03:40pm)", task.toString());
    }

    @Test
    public void markAsDone_task_taskIsDone() {
        Deadline task = new Deadline("task desc", LocalDateTime.parse("2002-06-21T15:40"));
        assertFalse(task.isDone());
        task.markAsDone();
        assertTrue(task.isDone());
    }

    @Test
    public void markAsUndone_task_taskIsUndone() {
        Deadline task = new Deadline("task desc", true, LocalDateTime.parse("2002-06-21T15:40"));
        assertTrue(task.isDone());
        task.markAsUndone();
        assertFalse(task.isDone());
    }
}
