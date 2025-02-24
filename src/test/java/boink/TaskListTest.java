package boink;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import boink.exceptions.InvalidIndexException;
import boink.tasks.ToDoTask;

public class TaskListTest {
    @Test
    public void testAddTask() {
        TaskList tasks = new TaskList();
        ToDoTask todo = new ToDoTask("Test");
        tasks.addTask(todo);
        assertEquals(tasks.getSize(), 1);
    }

    @Test
    public void testDeleteTask() throws InvalidIndexException {
        TaskList tasks = new TaskList();
        ToDoTask todo = new ToDoTask("Test");
        tasks.addTask(todo);
        tasks.deleteTask(0);
        assertEquals(tasks.getSize(), 0);
    }
}
