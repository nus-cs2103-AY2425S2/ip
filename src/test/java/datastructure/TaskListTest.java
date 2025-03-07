package datastructure;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import taskObjects.AbstractTask;
import taskObjects.Todo;
import exception.InvalidInputException;
public class TaskListTest {
    @Test
    void testAddTask() throws InvalidInputException {
        TaskList taskList = new TaskList();
        AbstractTask task = new Todo("Test task", false);
        taskList.add(task);
        assertEquals(1, taskList.getlist().size(), "Task should be added to the task list.");
        assertTrue(taskList.getlist().contains(task), "The task should be in the list.");
    }

    @Test
    void testListTask() {
        TaskList taskList = new TaskList();
//        AbstractTask task1 = new Todo("Test task1", false);
//        AbstractTask task2 = new Todo("Test task2", false);
//        taskList.add(task1);
//        taskList.add(task2);
        assertEquals(taskList.getTaskList(), "Commander, currently you have no outstanding task");
    }
}
