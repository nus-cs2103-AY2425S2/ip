package perrygpt.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        // Initialize a new TaskList before each test
        taskList = new TaskList();
    }

    @Test
    void addTask_shouldAddTaskSuccessfully() {
        // Create a mock Task
        Task task = new Todo("Read a book", false);

        // Add the task
        taskList.addTask(task);

        // Verify the task was added
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    void removeTask_shouldRemoveTaskSuccessfully() {
        // Add a few tasks
        Task task1 = new Todo("Task 1", false);
        Task task2 = new Todo("Task 2", true);

        taskList.addTask(task1);
        taskList.addTask(task2);

        // Remove the first task
        taskList.removeTask(0);

        // Verify the first task was removed
        assertEquals(1, taskList.size());
        assertEquals(task2, taskList.getTask(0));
    }
}