package laffy.tasklist.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void task_desc_taskWithDesc() {
        ToDo task = new ToDo("task desc");
        assertEquals("task desc", task.getDescription());
    }

    @Test
    public void task_descAndIsDone_taskWithDescAndIsDone() {
        ToDo task = new ToDo("task desc", true);
        assertEquals("task desc", task.getDescription());
        assertTrue(task.isDone());

        ToDo task2 = new ToDo("task desc", false);
        assertEquals("task desc", task2.getDescription());
        assertFalse(task2.isDone());
    }

    @Test
    public void markAsDone_task_taskIsDone() {
        ToDo task = new ToDo("task desc");
        assertFalse(task.isDone());
        task.markAsDone();
        assertTrue(task.isDone());
    }

    @Test
    public void markAsUndone_task_taskIsUndone() {
        ToDo task = new ToDo("task desc", true);
        assertTrue(task.isDone());
        task.markAsUndone();
        assertFalse(task.isDone());
    }

    @Test
    public void toString_task_taskString() {
        ToDo task = new ToDo("task desc");
        assertEquals("[T][ ] task desc", task.toString());

        ToDo task2 = new ToDo("task desc", true);
        assertEquals("[T][X] task desc", task2.toString());
    }

    @Test
    public void toToDoData_task_taskData() {
        ToDo task = new ToDo("task desc");
        ArrayList<String> taskData = task.toTaskData();
        assertEquals("T", taskData.get(0));
        assertEquals("0", taskData.get(1));
        assertEquals("task desc", taskData.get(2));

        ToDo task2 = new ToDo("task desc", true);
        ArrayList<String> taskData2 = task2.toTaskData();
        assertEquals("T", taskData2.get(0));
        assertEquals("1", taskData2.get(1));
        assertEquals("task desc", taskData2.get(2));
    }

    @Test
    public void isUpcoming_task_false() {
        ToDo task = new ToDo("task desc");
        assertFalse(task.isUpcoming());
    }

}
