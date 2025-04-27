package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dew.DewException;
import storage.Storage;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;

public class AddCommandTest {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Task sampleTask;

    @BeforeEach
    void setUp() {
        taskList = new TaskList(new ArrayList<>()); // Empty task list
        storage = new Storage("src/main/testTasks.txt"); // A test storage file (ensure no real file operations)
        ui = new Ui(); // Assuming Ui has a constructor
        sampleTask = new Todo("Test Task"); // Assuming Task has a constructor
    }

    @Test
    void execute_taskAddedSuccessfully() {
        AddCommand addCommand = new AddCommand(sampleTask);
        addCommand.execute(taskList, ui, storage);

        assertEquals(1, taskList.getTasks().size(), "TaskList should contain one task");
        assertEquals(sampleTask, taskList.getTasks().get(0), "Added task should match the expected task");
    }

    @Test
    void execute_storageSavesTasks() throws DewException {
        AddCommand addCommand = new AddCommand(sampleTask);
        addCommand.execute(taskList, ui, storage);

        // Manually check if tasks were saved (assuming storage has a way to retrieve saved tasks)
        TaskList loadedTasks = new TaskList(storage.loadTasks());
        assertEquals(1, loadedTasks.getTasks().size(), "Storage should persist the task");
        assertEquals(sampleTask.toString(), loadedTasks.getTasks().get(0).toString(),
                "Stored task should match original");
    }

    @Test
    void isExit_returnsFalse() {
        AddCommand addCommand = new AddCommand(sampleTask);
        assertFalse(addCommand.isExit(), "isExit should always return false");
    }
}
