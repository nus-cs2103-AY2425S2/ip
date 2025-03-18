package clank.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clank.exception.ClankException;
import clank.task.TaskList;
import clank.task.Todo;
import clank.utility.Storage;
import clank.utility.Ui;

/**
 * Unit tests for the {@code MarkCommand} class.
 */
public class MarkCommandTest {

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
     * Tests marking a task as done using MarkCommand.
     */
    @Test
    public void testMarkTask() throws ClankException {
        taskList.addTask(new Todo("Task to mark"));
        Command mark = new MarkCommand("mark 1");
        mark.execute(taskList, ui, storage);
        assertEquals("X", taskList.getTasks().get(0).getStatusIcon());
    }
}
