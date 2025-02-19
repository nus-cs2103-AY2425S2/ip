package mab.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import mab.task.Task;
import mab.task.ToDos;
import mab.MabException;

class ToDosCommandTest {
    @Test
    void testExecute() throws MabException {
        ArrayList<Task> taskList = new ArrayList<>();
        ToDosCommand command = new ToDosCommand("test");
        assertDoesNotThrow(() -> command.execute(taskList));
    }
    
    @Test
    void testEmptyToDos() throws MabException {
        ArrayList<Task> taskList = new ArrayList<>();
        ToDosCommand command = new ToDosCommand("");
        assertThrows(MabException.class, () -> command.execute(taskList));
    }
}
