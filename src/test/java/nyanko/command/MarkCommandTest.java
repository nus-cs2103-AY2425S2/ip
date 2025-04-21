package nyanko.command;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import nyanko.storage.Storage;
import nyanko.task.TaskList;
import nyanko.task.ToDo;
import nyanko.ui.Ui;

/**
 * Unit tests for the {@link MarkCommand} class.
 */
class MarkCommandTest {

    @Test
    void testMarkValidTask() throws IOException, InvalidTaskNumberException {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("dummy.txt");
        taskList.addTask(new ToDo("Finish assignment"));

        Command command = new MarkCommand("1");
        command.execute(taskList, ui, storage);

        assertTrue(taskList.getTask(0).isDone()); // ✅ Use isDone() method
    }

    @Test
    void testMarkInvalidTaskNumber() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("dummy.txt");

        Command command = new MarkCommand("1"); // No tasks exist yet
        assertThrows(InvalidTaskNumberException.class, () -> {
            command.execute(taskList, ui, storage);
        });
    }
}
