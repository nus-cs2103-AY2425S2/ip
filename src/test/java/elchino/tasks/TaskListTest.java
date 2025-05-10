package elchino.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest  {
    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Todo task1 = new Todo("Task 1");
        Todo task2 = new Todo("Task 2");

        // Test adding 1 task
        taskList.addTask(task1);
        assertEquals(1, taskList.getSize(), "TaskList should have 1 task after adding 1 task");

        // Test adding 2 tasks
        taskList.addTask(task2);
        assertEquals(2, taskList.getSize(), "TaskList should have 2 tasks after adding 2 tasks");

        // Verify that first task is task1
        assertEquals("Task 1", taskList.getTask(1).getDescription(), "First task should match task1");

        // Verify that second task is task2
        assertEquals("Task 2", taskList.getTask(2).getDescription(), "Second task should match task2");
    }
}
