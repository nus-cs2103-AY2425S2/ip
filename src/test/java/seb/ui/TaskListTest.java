package seb.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList(new ArrayList<>());
    }

    @Test
    public void testAddTask() throws SebException {
        Task task = new Todo("Read book", false);
        taskList.addTask(task);
        assertEquals(1, taskList.getTaskList().size());
        assertEquals("Read book", taskList.getTask(0).getDescription());
    }

    @Test
    public void testRemoveTask() throws SebException {
        Task task = new Todo("Read book", false);
        taskList.addTask(task);
        taskList.removeTask(0);
        assertTrue(taskList.getTaskList().isEmpty());
    }
}



