package runny.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import runny.RunnyException;
import runny.task.TaskList;


public class TodoCommandTest {
    @Test
    public void missingArgument() {
        assertThrows(RunnyException.class, () -> new TodoCommand("").doCommand(null, null, null));
    }
    @Test
    public void testTodoLoad() {
        TaskList tasks = new TaskList();
        TodoCommand testTodoCommand = new TodoCommand("sell food");
        testTodoCommand.loadTask(tasks);
        assertEquals(1, tasks.size());
    }
}
