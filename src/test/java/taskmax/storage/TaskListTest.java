package taskmax.storage;

import org.junit.jupiter.api.Test;
import taskmax.task.Task;
import taskmax.task.ToDo;
import taskmax.exception.TaskmaxException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    @Test
    void testMarkTaskAsDone() throws TaskmaxException {
        TaskList taskList = new TaskList(List.of(new ToDo("Test Task")));

        taskList.get(0).markAsDone();
        assertTrue(taskList.get(0).isDone());
    }
}
