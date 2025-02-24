package runny.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import runny.RunnyException;
import runny.storage.Storage;
import runny.task.TaskList;
import runny.ui.Ui;


public class MarkCommandTest {
    @Test
    public void negativeArgument() {
        assertThrows(RunnyException.class, () -> new MarkCommand("-1").doCommand(null, null, new TaskList()));
    }
    @Test
    public void invalidArgument() {
        assertThrows(RunnyException.class, () -> new MarkCommand("2").doCommand(null, null, new TaskList()));
    }
    @Test
    public void markTaskTest() throws RunnyException {
        TaskList tasks = new TaskList();
        TodoCommand testMarkCommand = new TodoCommand("sell food");
        testMarkCommand.loadTask(tasks);
        new MarkCommand("1").doCommand(new Ui(), new Storage("./data/testCase.txt"), tasks);
        assertTrue(tasks.get(0).getDone());
        new DeleteCommand("1").doCommand(new Ui(), new Storage("./data/testCase.txt"), tasks);
    }
}
