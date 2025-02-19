package mab.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import mab.task.Task;
import mab.task.ToDos;
import mab.MabException;

class MarkingCommandTest {
    @Test
    void testValidMarking() throws MabException {
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new ToDos("Test task", false));
        MarkingCommand command = new MarkingCommand("1", true);
        assertDoesNotThrow(() -> command.execute(taskList));
        assertTrue(taskList.get(0).getIsDone());
    }
    
    @Test
    void testInvalidIndex() {
        ArrayList<Task> taskList = new ArrayList<>();
        MarkingCommand command = new MarkingCommand("1", true);
        assertThrows(MabException.class, () -> command.execute(taskList));
    }
}
