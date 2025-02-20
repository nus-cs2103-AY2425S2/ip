package command;

import core.TaskList;
import exception.BaimiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;
import task.Todo;
import ui.Ui;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Contains the tests for the DeleteCommand class.
 */
class DeleteCommandTest {

    private static final String TEST_FILE_PATH = "./test_data/test.txt";

    /**
     * Sets up the test environment.
     */
    @BeforeEach
    void setUp() {
        File testDir = new File("./test_data");
        if (!testDir.exists()) {
            testDir.mkdirs();
        }
    }

    /**
     * Tests the execute method of the DeleteCommand class.
     *
     * @throws BaimiException If an error occurs.
     */
    @Test
    void execute_deletesTaskSuccessfully() throws BaimiException {
        TaskList taskList = new TaskList();
        taskList.addTasks(new Todo("Test Task"));
        Ui ui = new Ui();
        Storage storage = new Storage(TEST_FILE_PATH);
        DeleteCommand deleteCommand = new DeleteCommand(0);
        deleteCommand.executeAndGetResponse(taskList, ui, storage);
        assertEquals(0, taskList.getTasks().size());
        new File(TEST_FILE_PATH).delete();
    }
}
