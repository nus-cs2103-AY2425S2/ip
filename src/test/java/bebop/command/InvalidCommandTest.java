package bebop.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bebop.task.TaskList;
import bebop.ui.Storage;
import bebop.ui.Ui;

public class InvalidCommandTest {
    @Test
    public void invalidCommandTestSuccess() {
        InvalidCommand a = new InvalidCommand();
        TaskList t = new TaskList();
        assertEquals("Sorry that's not a valid command :D, please use an appropriate format",
                a.execute(t, new Ui(), new Storage("data/bebop.txt")));
    }
}

