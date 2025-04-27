package tooth.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tooth.stuff.Parser;
import tooth.stuff.Storage;
import tooth.stuff.TaskList;
import tooth.stuff.UI;

public class DeadlineCommandTest {
    @Test
    public void deadlineTest() {
        TaskList t = new TaskList();
        UI ui = new UI();
        Storage s = new Storage();
        Parser p = new Parser();
        Command c = p.parse("deadline task /by 2000-01-01");
        c.execute(t, ui, s);
        assertEquals(1, t.numTask());
    }
}
