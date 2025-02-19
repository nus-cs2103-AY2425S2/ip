package mab.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import mab.task.Task;
import mab.task.ToDos;
import mab.MabException;

class ListCommandTest {
    @Test
    void testListWithTasks() throws MabException {
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new ToDos("Test task", false));
        ListCommand command = new ListCommand("");
        assertDoesNotThrow(() -> command.execute(taskList));
    }
    
    @Test
    void testEmptyList() throws MabException {
        ArrayList<Task> taskList = new ArrayList<>();
        ListCommand command = new ListCommand("");
        assertDoesNotThrow(() -> command.execute(taskList));
    }
}
