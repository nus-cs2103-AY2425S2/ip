package bebop.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bebop.task.TaskList;
import bebop.task.Todo;
import bebop.ui.Storage;
import bebop.ui.Ui;


public class DeleteCommandTest {
    @Test
    public void deleteCommandTestSuccess() {
        DeleteCommand a = new DeleteCommand("delete 3");
        TaskList t = new TaskList();
        t.addTask(new Todo("world", true));
        t.addTask(new Todo("world2", true));
        t.addTask(new Todo("world3", true));
        assertEquals("Alright! Congrats on finishing your task:)\n"
                + "\t[T] [X] world3\n", a.execute(t, new Ui(), new Storage("data/bebop.txt")));
    }

    @Test
    public void deleteCommandTestFailure() {
        DeleteCommand a = new DeleteCommand("delete 10");
        TaskList t = new TaskList();
        t.addTask(new Todo("world", true));
        t.addTask(new Todo("world2", true));
        t.addTask(new Todo("world3", true));
        assertEquals("Delete only accepts valid integers",
                a.execute(t, new Ui(), new Storage("data/bebop.txt")));
    }
}
