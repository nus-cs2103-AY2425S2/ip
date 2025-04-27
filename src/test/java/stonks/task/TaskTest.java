package stonks.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void stringTest() {
        Task task = new Task("test");
        assertEquals(task.toString(), "[ ] test" );
    }

    @Test
    public void markTest() {
        Task task = new Task("test");
        task.markDone();
        assertEquals(task.isDone, true);
        assertEquals(task.toString(), "[X] test");
        task.markNotDone();
        assertEquals(task.isDone, false);
        assertEquals(task.toString(), "[ ] test" );
    }
}
