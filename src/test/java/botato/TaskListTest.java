package botato;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import botato.task.Task;

public class TaskListTest {
    @BeforeEach
    void setUp() {
        // Initialize tasklist to 0.
        TaskList taskList = new TaskList();
        TaskList.initialize(0);
        taskList.save();
    }

    @Test
    void testTaskListSize() {
        TaskList tasklist = new TaskList();

        tasklist.addTask(new Task());
        assertEquals(1, TaskList.size());
    }

    @Test
    void testAddTask() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        Storage.save(tasks);
        TaskList taskList = new TaskList();

        taskList.addTask(new Task());
        assertEquals(2, TaskList.size());
    }

    @Test
    void testDeleteTask() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        Storage.save(tasks);
        TaskList taskList = new TaskList();

        taskList.deleteTask(1);
        assertEquals(0, TaskList.size());
    }

    @Test
    void setTaskStatus() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        Storage.save(tasks);
    }
}
