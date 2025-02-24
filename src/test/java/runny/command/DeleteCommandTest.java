package runny.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import runny.RunnyException;
import runny.storage.Storage;
import runny.task.TaskList;
import runny.ui.Ui;

public class DeleteCommandTest {

    @Test
    public void negativeArgument() {
        assertThrows(RunnyException.class, () -> new DeleteCommand("-1").doCommand(null, null, new TaskList()));
    }

    @Test
    public void testTaskDeletion() throws RunnyException {
        TaskList tasks = new TaskList();
        DeadlineCommand testDeadline = new DeadlineCommand("borrow book /by 2025-02-01 1500");
        testDeadline.loadTask(tasks);
        new DeleteCommand("1").doCommand(new Ui(), new Storage("./data/testCase.txt"), tasks);
        assertEquals(0, tasks.size());
    }
}
