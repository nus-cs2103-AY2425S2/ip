package mab.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import mab.task.Task;
import mab.task.ToDos;
import mab.MabException;

class DeleteCommandTest {
    @Test
    void testValidDelete() throws MabException {
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new ToDos("Test task", false));
        DeleteCommand command = new DeleteCommand("1");
        assertDoesNotThrow(() -> command.execute(taskList));
        assertTrue(taskList.isEmpty());
    }
    
    @Test
    void testInvalidIndex() {
        ArrayList<Task> taskList = new ArrayList<>();
        DeleteCommand command1 = new DeleteCommand("0");
        DeleteCommand command2 = new DeleteCommand("-2");
        DeleteCommand command3 = new DeleteCommand("1");
        assertThrows(MabException.class, () -> command1.execute(taskList));
        assertThrows(MabException.class, () -> command2.execute(taskList));
        assertThrows(MabException.class, () -> command3.execute(taskList));
    }
}
