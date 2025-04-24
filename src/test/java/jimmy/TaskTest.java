package jimmy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jimmy.tasks.Task;
import jimmy.tasks.Todo;

class TaskTest {

    @Test
    void testInitialState() {
        Task task = new Todo("Read book");
        Assertions.assertFalse(task.isCompleted(), "A new task should be initially unmarked.");
        Assertions.assertEquals(" ", task.getStatus(), "New task should have a blank status.");
    }

    @Test
    void testMark() {
        Task task = new Todo("Read book");
        task.mark();
        Assertions.assertTrue(task.isCompleted(), "Task should be marked as done.");
        Assertions.assertEquals("X", task.getStatus(), "Marked task should have 'X' status.");
    }

    @Test
    void testUnmark() {
        Task task = new Todo("Read book");
        task.mark();
        task.unmark();
        Assertions.assertFalse(task.isCompleted(), "Task should be marked as not done.");
        Assertions.assertEquals(" ", task.getStatus(), "Unmarked task should have a blank status.");
    }

    @Test
    void testMultipleMarkUnmark() {
        Task task = new Todo("Read book");
        task.mark();
        Assertions.assertTrue(task.isCompleted(), "Task should be marked as done after first mark.");
        task.mark();
        Assertions.assertTrue(task.isCompleted(), "Task should still be marked as done after multiple marks.");
        task.unmark();
        Assertions.assertFalse(task.isCompleted(), "Task should be unmarked after calling unmark().");
        task.unmark();
        Assertions.assertFalse(task.isCompleted(), "Task should remain unmarked after multiple unmarks.");
    }

    @Test
    void testToStringUnmarked() {
        Task task = new Todo("Read book");
        Assertions.assertEquals("[T][ ] Read book", task.toString(),
            "toString() should return correct format for unmarked task.");
    }

    @Test
    void testToStringMarked() {
        Task task = new Todo("Read book");
        task.mark();
        Assertions.assertEquals("[T][X] Read book", task.toString(),
            "toString() should return correct format for marked task.");
    }
}
