package mab.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import mab.task.Task;
import mab.task.Deadlines;
import mab.MabException;

class DeadlinesCommandTest {
    @Test
    void testExecute() throws MabException {
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new Deadlines("Test deadline", false, "2025-02-04"));
        DeadlinesCommand command = new DeadlinesCommand("Test deadline /by 2020-12-12 20:30");
        assertDoesNotThrow(() -> command.execute(taskList));
    }

    @Test
    void testEmptyDeadlines() throws MabException {
        ArrayList<Task> taskList = new ArrayList<>();
        DeadlinesCommand command = new DeadlinesCommand("Test deadline /by");
        assertThrows(MabException.class, () -> command.execute(taskList));
    }

    @Test
    void testInvalidDate() throws MabException {
        ArrayList<Task> taskList = new ArrayList<>();
        DeadlinesCommand command = new DeadlinesCommand("Test deadline /by 20:30 2020-12-12");
        assertThrows(MabException.class, () -> command.execute(taskList));
    }

}
