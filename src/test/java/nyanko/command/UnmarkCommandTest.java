package nyanko.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import nyanko.storage.Storage;
import nyanko.task.TaskList;
import nyanko.task.ToDo;
import nyanko.ui.Ui;

/**
 * Unit tests for the {@link UnmarkCommand} class.
 */
class UnmarkCommandTest {

    @Test
    void testUnmarkValidTask() throws IOException, InvalidTaskNumberException {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("dummy.txt");

        taskList.addTask(new ToDo("Read a book"));
        taskList.getTask(0).markAsDone();

        Command command = new UnmarkCommand("1");
        command.execute(taskList, ui, storage);

        assertFalse(taskList.getTask(0).isDone());
    }

    @Test
    void testUnmarkInvalidTaskNumber() {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("dummy.txt");

        Command command = new UnmarkCommand("1"); // No tasks exist yet
        assertThrows(InvalidTaskNumberException.class, () -> {
            command.execute(taskList, ui, storage);
        });
    }
}
