package taskmax.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test class for the Task class.
 */
class TaskTest {
    /**
     * Tests marking a task as done.
     */
    @Test
    void testMarkAsDone() {
        Task task = new ToDo("Finish CS2103", 1);
        assertFalse(task.isDone());

        task.markAsDone();
        assertTrue(task.isDone());
    }

    /**
     * Tests marking a task as not done.
     */
    @Test
    void testMarkAsNotDone() {
        Task task = new ToDo("Test Task", 1);
        task.markAsDone();
        task.markAsNotDone();
        assertFalse(task.isDone(), "Task should be marked as not done.");
    }
}
