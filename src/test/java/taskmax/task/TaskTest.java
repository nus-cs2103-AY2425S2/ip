package taskmax.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    @Test
    void testMarkAsDone() {
        Task task = new ToDo("Finish CS2103");
        assertFalse(task.isDone()); // Task should start as not done

        task.markAsDone();
        assertTrue(task.isDone()); // After marking, it should be done
    }
}
