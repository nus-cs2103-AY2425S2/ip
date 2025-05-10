package helix.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import helix.stub.StubStorage;
import helix.ui.ConsoleUi;

/**
 * Tests the behavior of the {@code TaskList} class.
 *
 * <p>
 * This class verifies the behavior of the {@link TaskList} methods, such as adding and removing tasks,
 * using a {@link StubStorage} to avoid dependencies on external storage.
 * </p>
 */
public class TaskListTest {

    private TaskList taskList;
    private ConsoleUi consoleUi;
    private StubStorage stubStorage;

    /**
     * Sets up the testing environment before each test.
     *
     * <p>
     * A new {@link TaskList} is initialized with a {@link StubStorage} to avoid file I/O operations,
     * and a {@link ConsoleUi} instance is created for use in the tests.
     * </p>
     *
     * @throws IOException if an error occurs during setup (unlikely with {@link StubStorage})
     */
    @BeforeEach
    void setUp() throws IOException {
        stubStorage = new StubStorage();
        consoleUi = new ConsoleUi();
        taskList = new TaskList(stubStorage);
    }

    /**
     * Tests the {@link TaskList#addTask(Task, ConsoleUi)} method.
     *
     * <p>
     * Verifies that a valid {@link Task} can be successfully added to the task list and ensures that
     * the task count and task description are correct after the addition.
     * </p>
     */
    @Test
    void addTask_validTask_taskAdded() {
        Task task = new Todo("Sample Task");
        taskList.addTask(task, consoleUi);
        assertEquals(1, taskList.getTaskCount(), "Task count should be 1 after adding a task.");
        assertEquals("Sample Task", taskList.getTask(0).getDescription(), "The added task's description should match.");
    }

    /**
     * Tests the {@link TaskList#removeTask(int, ConsoleUi)} method.
     *
     * <p>
     * Verifies that a task can be successfully removed from the task list by its index and ensures that
     * the task count is updated correctly after the removal.
     * </p>
     */
    @Test
    void removeTask_validIndex_taskRemoved() {
        Task task = new Todo("Sample Task");
        taskList.addTask(task, consoleUi);
        Task removedTask = taskList.removeTask(0, consoleUi);
        assertEquals("Sample Task", removedTask.getDescription(), "The removed task's description should match.");
        assertEquals(0, taskList.getTaskCount(), "Task count should be 0 after removing the task.");
    }
}
