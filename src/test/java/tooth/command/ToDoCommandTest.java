package tooth.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tooth.stuff.Parser;
import tooth.stuff.Storage;
import tooth.stuff.TaskList;
import tooth.stuff.UI;

public class ToDoCommandTest {
    @Test
    public void toDoTest() {
        TaskList t = new TaskList();
        UI ui = new UI();
        Storage s = new Storage();
        Parser p = new Parser();
        Command c = p.parse("todo task");
        c.execute(t, ui, s);
        assertEquals(1, t.numTask());
    }
}
