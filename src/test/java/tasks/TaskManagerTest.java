package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.TaskException;

class TaskManagerTest {

    private TaskManager taskManager;
    private Todo todoTask1;

    @BeforeEach
    void setUp() throws TaskException {
        taskManager = new TaskManager();
        todoTask1 = Todo.create("Buy groceries /priority HIGH");
    }

    @Test
    void testAddTask() throws TaskException, IOException {
        String response = taskManager.addTask(todoTask1);
        assertEquals(
                "______________________________________________________________________________________\n"
                        + "I've added this to tasks:\n"
                        + todoTask1
                        + "\nCool. You have 1 tasks now. Anything else?\n"
                        + "______________________________________________________________________________________\n",
                response
        );
    }

    @Test
    void testAddTaskEmptyDescription() {
        assertThrows(TaskException.class, () -> Todo.create("todo /priority HIGH"));
    }

    @Test
    void testAddTaskInvalidPriority() {
        assertThrows(TaskException.class, () -> Todo.create("Buy groceries /priority INVALID"));
    }
}
