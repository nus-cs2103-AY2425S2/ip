package runny.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import runny.RunnyException;
import runny.task.TaskList;

public class EventCommandTest {
    @Test
    public void missingArgument() {
        assertThrows(RunnyException.class, () -> new EventCommand("").doCommand(null, null, null));
    }
    @Test
    public void testEventLoad() throws RunnyException {
        TaskList tasks = new TaskList();
        EventCommand testEventCommand = new EventCommand("read book /from 2025-01-20 1800 /to 2025-02-01 1800");
        testEventCommand.loadTask(tasks);
        assertEquals(1, tasks.size());
    }
}
