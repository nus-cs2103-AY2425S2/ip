package softess;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void testAddTask() {
        Task todo = new ToDo("Read book", false);
        taskList.addTask(todo);
        assertEquals(todo, taskList.getTasks().get(0), "Added task should match the expected task.");
    }
}