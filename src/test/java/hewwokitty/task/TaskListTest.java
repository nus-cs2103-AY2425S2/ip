package hewwokitty.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        taskList.addTask("todo cry");
        assertEquals(taskList.getSize(), 1);
    }
}
