package a.tasklist;

import a.task.Todo;
import a.tasklist.TaskList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    @Test
    void testAddTask() {
        TaskList tasks = new TaskList(new ArrayList<>());
        tasks.add(new Todo("Read book"));
        assertEquals(1, tasks.size());
    }

    @Test
    void testDeleteTask() {
        TaskList tasks = new TaskList(new ArrayList<>());
        tasks.add(new Todo("Read book"));
        tasks.remove(0);
        assertEquals(0, tasks.size());
    }
}
