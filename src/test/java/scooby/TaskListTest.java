package scooby;

import scooby.tasks.Event;
import scooby.tasks.TaskList;
import scooby.tasks.ToDo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        // Set up a fresh task list before each test
        taskList = new TaskList();
    }

    @Test
    void testAddTask() {
        // Add a ToDo task
        String command = "todo Test task";
        taskList.addTask(command);

        // Check if the task was added
        assertEquals(new ToDo("Test task").toString(), taskList.getTask(0).toString(),
                "Task should be added to the list");
        assertTrue(taskList.getTask(0) instanceof ToDo,
                "The added task should be a ToDo");
    }

    @Test
    void testDeleteTask() {
        // Add a ToDo task
        String command = "todo Task to be deleted";
        taskList.addTask(command);

        // Ensure the task is added
        assertEquals(new ToDo("Task to be deleted").toString(), taskList.getTask(0).toString(),
                "Task should be added to the list");

        // Delete the task
        taskList.deleteTask(0);

        // Ensure the task is removed from the list
        assertTrue(taskList.isEmpty());
    }

    @Test
    void testTaskListNotEmptyAfterAdding() {
        // Add multiple tasks and ensure the task list is not empty
        taskList.addTask("todo Task 1");
        taskList.addTask("event Task 2 /from 2019-12-12 1300 /to 2019-12-13 1600");

        // Verify the equality
        assertEquals(new ToDo("Task 1").toString(), taskList.getTask(0).toString(),
                "Task 1 should be at index 0");
        assertEquals(new Event("Task 2", "2019-12-12 1300", "2019-12-13 1600").toString(),
                taskList.getTask(1).toString(), "Task 2 should be at index 1");

        // Verify the non-emptiness of the list
        assertFalse(taskList.isEmpty());
    }
}