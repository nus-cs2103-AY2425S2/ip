package clank.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clank.exception.ClankException;
import clank.task.TaskList;
import clank.task.Todo;
import clank.utility.Storage;
import clank.utility.Ui;

/**
 * Unit tests for the {@code DeleteCommand} class.
 */
public class DeleteCommandTest {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Sets up a test environment with a sample task list before each test.
     */
    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        storage = new Storage("./data/testData.txt");
        ui = new Ui();
    }

    /**
     * Tests the execution of DeleteCommand to remove a task.
     */
    @Test
    public void testDeleteTask() throws ClankException {
        taskList.addTask(new Todo("Task to delete"));
        Command delete = new DeleteCommand("delete 1");
        delete.execute(taskList, ui, storage);
        assertEquals(0, taskList.getTasks().size());
    }

    /**
     * Tests handling of incorrect DeleteCommand format.
     */
    @Test
    public void testDeleteInvalidFormat() {
        assertThrows(ClankException.class, () -> new DeleteCommand("delete"));
    }
}
