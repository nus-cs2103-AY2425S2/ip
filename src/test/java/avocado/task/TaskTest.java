package avocado.task;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TaskTest {
    @Test
    void testMarkAsDone() {
        Task task = new Todo("Read book");
        task.markAsDone();
        assertTrue(task.isDone, "Task should be marked as done.");
    }

    @Test
    void testMarkAsNotDone() {
        Task task = new Todo("Write code");
        task.markAsDone();
        task.markAsNotDone();
        assertFalse(task.isDone, "Task should be marked as not done.");
    }
}
