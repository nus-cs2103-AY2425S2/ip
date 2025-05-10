package nickiminaj;

import nickiminaj.tasks.Task;
import nickiminaj.tasks.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class TaskListTest {
    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task task = new Todo("Read a book");

        taskList.addTask(task);

        assertEquals(1, taskList.getSize());
        assertEquals("Read a book", taskList.getTask(0).getDescription());
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Todo task1 = new Todo("Read a book");
        Todo task2 = new Todo("Write code");

        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.removeTask(0);

        assertEquals(1, taskList.getSize());
        assertEquals("Write code", taskList.getTask(0).getDescription());
    }
}
