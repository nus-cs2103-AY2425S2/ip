package paimon.tasklist; //same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import paimon.items.Deadline;
import paimon.items.Event;
import paimon.items.Todo;

public class TaskListTest {
    @Test
    public void addTask_successful() {
        TaskList taskList = new TaskList();

        Todo t0 = new Todo("todo borrow book");
        Todo t1 = new Deadline("return book", "2/12/2019 1800");
        Todo t2 = new Event("project meeting", "2/12/2019 1400", "2/12/2022 1900");

        taskList.add(t0);
        taskList.add(t1);
        taskList.add(t2);

        assertEquals(t0, taskList.get(0));
        assertEquals(t1, taskList.get(1));
        assertEquals(t2, taskList.get(2));
    }

    @Test
    public void getSize_successful() {
        TaskList taskList = new TaskList();

        Todo t0 = new Todo("todo borrow book");
        Todo t1 = new Deadline("return book", "2/12/2019 1800");
        Todo t2 = new Event("project meeting", "2/12/2019 1400", "2/12/2022 1900");

        taskList.add(t0);
        taskList.add(t1);
        taskList.add(t2);

        assertEquals(3, taskList.size());
    }

    @Test
    public void markTask_successful() {
        TaskList taskList = new TaskList();

        Todo t0 = new Todo("todo borrow book");
        Todo t1 = new Deadline("return book", "2/12/2019 1800");
        Todo t2 = new Event("project meeting", "2/12/2019 1400", "2/12/2022 1900");

        taskList.add(t0);
        taskList.add(t1);
        taskList.add(t2);

        taskList.mark(0);
        taskList.mark(2);

        assertEquals("[X]", taskList.get(0).getStatus());
        assertEquals("[ ]", taskList.get(1).getStatus());
        assertEquals("[X]", taskList.get(2).getStatus());
    }
}
