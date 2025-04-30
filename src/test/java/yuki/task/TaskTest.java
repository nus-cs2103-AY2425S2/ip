package yuki.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    void testMarkAsDone() {
        // Arrange
        Task task = new Task("Read book", false);

        // Act
        task.markAsDone();

        // Assert
        assertTrue(task.isDone());
    }

    @Test
    void testUnmarkAsDone() {
        // Arrange
        Task task = new Task("Read book", true);

        // Act
        task.markAsNotDone();

        // Assert
        assertFalse(task.isDone());
    }
}