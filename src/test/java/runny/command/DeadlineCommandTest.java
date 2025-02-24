package runny.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import runny.RunnyException;
import runny.task.TaskList;

public class DeadlineCommandTest {
    @Test
    public void missingArgument() {
        assertThrows(RunnyException.class, () -> new DeadlineCommand("").doCommand(null, null, null));
    }

    @Test
    public void testDeadlineLoad() {
        TaskList tasks = new TaskList();
        DeadlineCommand testDeadLine = new DeadlineCommand("borrow book /by 2025-02-01 1200");
        testDeadLine.loadTask(tasks);
        assertEquals(1, tasks.size());
    }
}
