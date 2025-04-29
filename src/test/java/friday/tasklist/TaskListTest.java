package friday.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import friday.fridayexceptions.FridayException;
import friday.tasks.Task;
import friday.tasks.TodoTask;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void taskList_setUp() {
        taskList = new TaskList();
    }

    @Test
    public void taskList_emptyTaskList_emptyList() {
        assertTrue(TaskList.returnList().isEmpty(), "New TaskList should be empty.");
    }

    @Test
    public void taskList_todoTask_taskListWithTodoTask() {
        Task todo = new TodoTask("Finish homework");
        String response = TaskList.addToList(todo);
        assertTrue(response.contains("Got it. I've added this task:"), "Task should be added successfully");
        assertEquals(1, TaskList.returnList().size(), "TaskList should have one task.");
    }

    @Test
    public void taskList_getTask_taskListThrowsExceptionForInvalidIndex() {
        Exception exception = assertThrows(FridayException.class, () -> TaskList.getTask(0));
        assertEquals("please input an acceptable integer",
                exception.getMessage(), "Exception message should match");
    }

    @Test
    public void taskList_delete_taskListThrowsExceptionForInvalidIndex() {
        Exception exception = assertThrows(FridayException.class, () -> TaskList.delete(0));
        assertEquals("please input an acceptable integer",
                exception.getMessage(), "Deleting from an empty list should throw an exception");
    }

    @Test
    public void taskList_mark_taskListThrowsExceptionForInvalidIndex() {
        Exception exception = assertThrows(FridayException.class, () -> TaskList.mark(0));
        assertEquals("please input an acceptable integer",
                exception.getMessage(), "Marking an invalid task should throw an exception");
    }
}
