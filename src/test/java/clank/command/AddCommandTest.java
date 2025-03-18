package clank.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clank.exception.ClankException;
import clank.task.Deadline;
import clank.task.TaskList;
import clank.utility.Storage;
import clank.utility.Ui;

/**
 * Unit tests for the {@code AddCommand} class.
 */
public class AddCommandTest {

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
     * Tests the creation and execution of an AddCommand for a Todo task.
     */
    @Test
    public void testAddTodo() throws ClankException {
        Command addTodo = new AddCommand("todo Read book");
        addTodo.execute(taskList, ui, storage);
        assertEquals(1, taskList.getTasks().size());
        assertEquals("Read book", taskList.getTasks().get(0).getDescription());
    }

    /**
     * Tests the creation and execution of an AddCommand for a Deadline task.
     */
    @Test
    public void testAddDeadline() throws ClankException {
        Command addDeadline = new AddCommand("deadline Submit report /by 25/12/2025 2359");
        addDeadline.execute(taskList, ui, storage);
        assertEquals(1, taskList.getTasks().size());
        assertTrue(taskList.getTasks().get(0) instanceof Deadline);
    }

    /**
     * Tests handling of invalid AddCommand input.
     */
    @Test
    public void testAddInvalidCommand() {
        assertThrows(ClankException.class, () -> new AddCommand("add something wrong"));
    }
}
