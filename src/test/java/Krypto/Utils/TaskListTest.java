package Krypto.Utils;

import Krypto.Exceptions.KryptoExceptions;
import Krypto.Task.ToDo;
import Krypto.Task.Task;
import Krypto.IO.GUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MockGUI extends GUI {
    @Override
    public void newResponse(String input) {
    }
    @Override
    public void addTaskResponse(Task t, int len) {
    }
    @Override
    public void deleteTaskResponse(Task t, int len) {
    }
}

public class TaskListTest {
    private TaskList taskList;
    private MockGUI mockUI;

    @BeforeEach
    public void setUp() {
        mockUI = new MockGUI();
        taskList = new TaskList(new ArrayList<>(), mockUI);
    }

    @Test
    public void testAddTask()  {
        Task task = new ToDo("todo Read book");
        taskList.addTask(task);
        assertEquals(1, taskList.getLength());
        try {
            assertEquals("Read book", taskList.getTask(0).getDescription());
        } catch (KryptoExceptions e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDeleteTask() {
        Task task1 = new ToDo("todo Read book");
        Task task2 = new ToDo("todo Write notes");
        taskList.addTask(task1);
        taskList.addTask(task2);

        try {
            taskList.deleteTask(0);
        } catch (KryptoExceptions e) {
            throw new RuntimeException(e);
        }
        assertEquals(1, taskList.getLength());
        try {
            assertEquals("Write notes", taskList.getTask(0).getDescription());
        } catch (KryptoExceptions e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDeleteTask_InvalidIndex() {
        assertThrows(KryptoExceptions.class, () -> taskList.deleteTask(0));
    }

    @Test
    public void testGetTask_InvalidIndex() {
        assertThrows(KryptoExceptions.class, () -> taskList.getTask(0));
    }

    @Test
    public void testEmptyTaskList() {
        assertEquals(0, taskList.getLength());
    }
}
