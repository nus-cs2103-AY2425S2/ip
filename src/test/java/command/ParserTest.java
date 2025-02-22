package command;

import org.junit.jupiter.api.Test;
import task.TaskList;
import task.Todo;
import task.Deadline;
import task.Event;
import task.Task;
import ui.Ui;
import exception.SamanthaException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    @Test
    void markTest() throws SamanthaException {
        Ui ui = new Ui();
        TaskList tasks = new TaskList(new ArrayList<>());

        Parser.parseCommand("todo Finish sleeping", tasks, ui);
        assertEquals("[T][ ] Finish sleeping", tasks.getTask(0).toString());

        Parser.parseCommand("mark 1", tasks, ui);
        assertEquals("[T][X] Finish sleeping", tasks.getTask(0).toString());
    }

    @Test
    void deadlineTest() throws SamanthaException {
        Ui ui = new Ui();
        TaskList tasks = new TaskList(new ArrayList<>());

        Parser.parseCommand("deadline Submit QF2104 Assignment 1 /by 2025-02-17", tasks, ui);

        assertEquals(1, tasks.getSize());
        assertEquals("[D][ ] Submit QF2104 Assignment 1 (by: Feb 17 2025)", tasks.getTask(0).toString());
    }
}
