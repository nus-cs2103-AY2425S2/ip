package taskmax.storage;
import taskmax.task.ToDo;
import taskmax.exception.TaskmaxException;

import org.junit.jupiter.api.Test;

import java.util.List;


import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for the {@link TaskList} class.
 */
class TaskListTest {
    /**
     * Tests marking a task as done in the task list.
     *
     * @throws TaskmaxException If an error occurs while accessing the task list.
     */
    @Test
    void testMarkTaskAsDone() throws TaskmaxException {
        TaskList taskList = new TaskList(List.of(new ToDo("Test Task", 1)));

        taskList.get(0).markAsDone();
        assertTrue(taskList.get(0).isDone());
    }
}
