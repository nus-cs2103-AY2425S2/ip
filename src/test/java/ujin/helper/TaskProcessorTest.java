package ujin.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ujin.task.Deadline;
import ujin.task.Task;

public class TaskProcessorTest {
    @Test
    public void testTaskToText() {
        Task task = new Deadline("borrow book", "2024/07/28");
        assertEquals("D | 0 | borrow book | 2024-07-28 23:59", TaskProcessor.taskToText(task));
    }
}
