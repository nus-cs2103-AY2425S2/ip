package ricky.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testAddTask() {
        Task task = new ToDo("read book");
        taskList.add(task);
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.get(0));
    }

    @Test
    public void testGetTask() {
        Task task1 = new ToDo("read book");
        Task task2 = new Deadline("submit report", LocalDateTime.parse("2023-12-31T23:59"));
        taskList.add(task1);
        taskList.add(task2);
        assertEquals(task1, taskList.get(0));
        assertEquals(task2, taskList.get(1));
    }

    @Test
    public void testDeleteTask() {
        Task task = new ToDo("read book");
        taskList.add(task);
        taskList.delete(0);
        assertEquals(0, taskList.size());
    }

    @Test
    public void testMarkDone() {
        Task task = new ToDo("read book");
        taskList.add(task);
        taskList.markDone(0);
        assertEquals("X", taskList.get(0).getStatusIcon());
    }

    @Test
    public void testMarkUndone() {
        Task task = new ToDo("read book");
        taskList.add(task);
        taskList.markDone(0);
        taskList.markUndone(0);
        assertEquals(" ", taskList.get(0).getStatusIcon());
    }

    @Test
    public void testSize() {
        assertEquals(0, taskList.size());
        taskList.add(new ToDo("read book"));
        assertEquals(1, taskList.size());
    }

    @Test
    public void testGetTasks() {
        Task task1 = new ToDo("read book");
        Task task2 = new Deadline("submit report", LocalDateTime.parse("2023-12-31T23:59"));
        taskList.add(task1);
        taskList.add(task2);
        ArrayList<Task> tasks = taskList.getTasks();
        assertEquals(2, tasks.size());
        assertEquals(task1, tasks.get(0));
        assertEquals(task2, tasks.get(1));
    }

    @Test
    public void testInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.delete(0));
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.markDone(0));
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.markUndone(0));
    }
}
