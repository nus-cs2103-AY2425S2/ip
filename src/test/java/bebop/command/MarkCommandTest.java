package bebop.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bebop.task.TaskList;
import bebop.task.Todo;
import bebop.ui.Storage;
import bebop.ui.Ui;

public class MarkCommandTest {
    @Test
    public void markCommandTestSuccess() {
        MarkCommand a = new MarkCommand(true, "mark 1");
        TaskList t = new TaskList();
        t.addTask(new Todo("world", false));
        assertEquals("1 Good Job! I've marked the task as done!",
                a.execute(t, new Ui(), new Storage("data/bebop.txt")));
    }

    @Test
    public void unmarkCommandTestSuccess() {
        MarkCommand a = new MarkCommand(false, "unmark 1");
        TaskList t = new TaskList();
        t.addTask(new Todo("world", true));
        assertEquals("1 Alright! Let's get this done soon :)",
                a.execute(t, new Ui(), new Storage("data/bebop.txt")));
    }
}

