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
 * Unit tests for the {@code UnmarkCommand} class.
 */
public class UnmarkCommandTest {

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
     * Tests unmarking a task using UnmarkCommand.
     */
    @Test
    public void testUnmarkTask() throws ClankException {
        taskList.addTask(new Todo("Task to unmark"));
        taskList.mark(0);
        Command unmark = new UnmarkCommand("unmark 1");
        unmark.execute(taskList, ui, storage);
        assertEquals(" ", taskList.getTasks().get(0).getStatusIcon());
    }
}
