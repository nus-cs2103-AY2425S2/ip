package tom.tasklist;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tom.exception.InvalidIndexException;
import tom.exception.TaskException;
import tom.task.Task;
import tom.task.Todo;

public class TaskListTest {
    private TaskList taskList;
    private Task task1;
    private Task task2;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        task1 = new Todo("Task 1");
        task2 = new Todo("Task 2");
    }

    @Test
    public void constructor_initializesEmptyList() {
        assertEquals(0, taskList.size());
    }

    @Test
    public void addTask_increasesSize() {
        taskList.addTask(task1);
        assertEquals(1, taskList.size());
    }

    @Test
    public void removeTask_decreasesSize() throws InvalidIndexException {
        taskList.addTask(task1);
        taskList.addTask(task2);
        assertDoesNotThrow(() -> taskList.removeTask(1));
        assertEquals(1, taskList.size());
    }

    @Test
    public void removeTask_invalidPosition_throwsException() {
        taskList.addTask(task1);
        assertThrows(InvalidIndexException.class, () -> taskList.removeTask(0));
        assertThrows(InvalidIndexException.class, () -> taskList.removeTask(2));
    }

    @Test
    public void markTask_done() throws TaskException {
        taskList.addTask(task1);
        assertDoesNotThrow(() -> taskList.markTask(1, true));
        assertEquals("X", task1.getStatusIcon());
    }

    @Test
    public void markTask_undone() throws TaskException {
        taskList.addTask(task1);
        assertDoesNotThrow(() -> taskList.markTask(1, true));
        assertDoesNotThrow(() -> taskList.markTask(1, false));
        assertEquals(" ", task1.getStatusIcon());
    }
}
