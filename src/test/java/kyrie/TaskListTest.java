package kyrie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void testTaskListConstructor() {
        TaskList taskList = new TaskList();
        assertEquals(100, taskList.getMaxSize());
        assertEquals(0, taskList.getTaskCount());
    }

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Sample Task");
        taskList.addTask(task);
        assertEquals(1, taskList.getTaskCount());
        assertEquals(task, taskList.getTask(1));
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Sample Task");
        taskList.addTask(task);
        assertEquals(1, taskList.getTaskCount());
        assertEquals(task, taskList.deleteTask(1));
        assertEquals(0, taskList.getTaskCount());
    }

    @Test
    public void testMarkTaskAsDone() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Sample Task");
        taskList.addTask(task);
        assertEquals(false, taskList.getTask(1).isDone());
        taskList.markTaskAsDone(1);
        assertEquals(true, taskList.getTask(1).isDone());
    }
}
