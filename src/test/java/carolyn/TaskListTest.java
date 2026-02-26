package carolyn;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    void testDelete() {
        ArrayList<Task> list = new ArrayList();
        list.add(new ToDo("sayHi"));
        TaskList taskList = new TaskList(list);
        taskList.delete(0);
        assertEquals(0, taskList.size(), "TaskList should contain 0 tasks");
    }
}