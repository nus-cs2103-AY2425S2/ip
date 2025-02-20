package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Task;
import task.Todo;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Tests the TaskList class.
 */
class TaskListTest {

    private TaskList taskList;

    /**
     * Sets up the task list for testing.
     */
    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    /**
     * Tests the addTask method.
     */
    @Test
    void addTask_addsTaskSuccessfully() {
        Task task = new Todo("Read book");

        taskList.addTasks(task);
        assertEquals(1, taskList.getTasks().size());
        assertSame(task, taskList.getTasks().get(0));
    }
}
