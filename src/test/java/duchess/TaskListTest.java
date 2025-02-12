package duchess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    void testSize() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size());
        taskList.add(new Task("Task 1"));
        taskList.add(new Task("Task 2"));
        assertEquals(2, taskList.size());
    }

    @Test
    void testGetInvalidIndex() {
        TaskList taskList = new TaskList();
        taskList.add(new Task("Task 1"));
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.get(1));
    }

}
