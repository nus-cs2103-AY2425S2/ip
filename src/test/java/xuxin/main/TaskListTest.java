package xuxin.main;

import xuxin.exception.DukeException;
import xuxin.task.Deadline;
import xuxin.task.Event;
import xuxin.task.Task;
import xuxin.task.Todo;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {
    @Test
    void defaultTaskConstructorTest() throws DukeException {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getSize(), "Initial size of list should be 0");

        Todo test = new Todo("todo");
        taskList.addTask(test);
        assertEquals(test, taskList.getTask(0), "Test insert todo into list");

        taskList.removeTask(0);
        assertEquals(0, taskList.getSize(), "Size of list after deletion should be 0");
    }

    @Test
    void overloadedTaskConstructorTest() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        Todo todo = new Todo("test");
        Deadline deadline = new Deadline("deadline", "31/01/2025");

        tasks.add(todo);
        tasks.add(deadline);

        TaskList taskList = new TaskList(tasks);
        assertEquals(2, taskList.getSize(), "Initial size of list with provided values should be 2");

        Event event = new Event("event", "12/01/2025", "12/01/2025");
        taskList.addTask(event);
        assertEquals(3, taskList.getSize(), "Size of list after addition should be 3");
        assertEquals(todo, taskList.getTask(0), "Test get todo from list");
        assertEquals(deadline, taskList.getTask(1), "Test get deadline from list");
        assertEquals(event, taskList.getTask(2), "Test get event from list");

        taskList.removeTask(0);
        assertEquals(2, taskList.getSize(), "Size of list after deletion should be 2");
        assertEquals(deadline, taskList.getTask(0), "Test get deadline from list");
        assertEquals(event, taskList.getTask(1), "Test get event from list");
    }
}