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
 * Unit tests for the {@code FindCommand} class.
 */
public class FindCommandTest {

    private TaskList taskList;
    private Ui ui;

    /**
     * Sets up a test environment with a sample task list before each test.
     */
    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        ui = new Ui();
    }

    /**
     * Tests the execution of FindCommand to find a task by keyword.
     */
    @Test
    public void testFindTask() throws ClankException {
        taskList.addTask(new Todo("Buy milk"));
        taskList.addTask(new Todo("Read book"));
        Command find = new FindCommand("find book");
        find.execute(taskList, ui, new Storage("testData.txt"));
        assertEquals(1, taskList.findTasksWithKeywords("book").size());
    }

    /**
     * Tests handling of an empty keyword in FindCommand.
     */
    @Test
    public void testFindEmptyKeyword() {
        assertThrows(ClankException.class, () -> new FindCommand("find"));
    }
}
