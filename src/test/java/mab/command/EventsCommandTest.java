package mab.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import mab.task.Task;
import mab.task.Events;
import mab.MabException;

class EventsCommandTest {
    @Test
    void testExecute() throws MabException {
        ArrayList<Task> taskList = new ArrayList<>();
        EventsCommand command = new EventsCommand("test event /from 2025/12/12 12:12 /to 2025/12/12 13:13");
        assertDoesNotThrow(() -> command.execute(taskList));
    }
    
    @Test
    void testEmptyHourAndMin() throws MabException {
        ArrayList<Task> taskList = new ArrayList<>();
        EventsCommand command = new EventsCommand("test event /from 2025/12/12  /to 2025/12/12 ");
        assertDoesNotThrow(() -> command.execute(taskList));
    }

    @Test
    void testStartAfterEnd() throws MabException {
        ArrayList<Task> taskList = new ArrayList<>();
        EventsCommand command = new EventsCommand("test event /from 2025/12/12 13:13 /to 2025/12/12 12:12");
        assertThrows(MabException.class, () -> command.execute(taskList));
    }
}
