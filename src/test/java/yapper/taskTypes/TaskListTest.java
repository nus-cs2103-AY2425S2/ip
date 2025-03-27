package yapper.taskTypes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import yapper.tasktypes.Task;
import yapper.tasktypes.TaskList;
import yapper.tasktypes.ToDo;

class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList(); // Initialize a new TaskList before each test
    }

    @Test
    void testAddTask() {
        Task task = new ToDo("todo Read a book");
        taskList.addTask(task);
        assertEquals(1, taskList.getList().size(), "TaskList should contain 1 task.");
        assertEquals("[T][ ] Read a book", taskList.getList().get(0).toString(),
                "Task should be correctly added.");
    }

    @Test
    void testDeleteTask_validIndex() {
        Task task = new ToDo("todo Read a book");
        taskList.activateToPrint();
        taskList.addTask(task);
        String response = taskList.deleteTask("1");
        assertEquals(0, taskList.getList().size(), "TaskList should be empty after deletion.");
        assertEquals("Noted. I've removed this task:\n\t[T][ ] Read a book\nNow you have 0 tasks in the list.",
                response, "Delete confirmation message should match.");
    }

    @Test
    void testDeleteTask_invalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask("1"),
                "Should throw an exception for invalid index.");
    }

    @Test
    void testFindTask_found() {
        Task task = new ToDo("todo Read a book");
        taskList.addTask(task);
        String response = taskList.findTask("Read");
        assertEquals("Here are the matching tasks in your list:\n\t1.[T][ ] Read a book", response,
                "Search should return the correct task.");
    }

    @Test
    void testFindTask_notFound() {
        Task task = new ToDo("todo Read a book");
        taskList.addTask(task);
        String response = taskList.findTask("Write");
        assertEquals("There are no matching tasks in your list containing: Write", response,
                "Search should return a not found message.");
    }

    @Test
    void testMarkItem_validIndex() {
        Task task = new ToDo("todo Read a book");
        taskList.activateToPrint();
        taskList.addTask(task);
        String response = taskList.markItem(0);
        assertTrue(taskList.getList().get(0).getIsCompleted(), "Task should be marked as completed.");
        assertEquals("Nice! I've marked this task as done:\n\t[T][X] Read a book", response,
                "Mark confirmation message should match.");
    }

    @Test
    void testUnmarkItem_validIndex() {
        Task task = new ToDo("todo Read a book");
        taskList.activateToPrint();
        taskList.addTask(task);
        taskList.markItem(0);
        String response = taskList.unmarkItem(0);
        assertFalse(taskList.getList().get(0).getIsCompleted(), "Task should be unmarked as not completed.");
        assertEquals("OK, I've marked this task as not done yet:\n\t[T][ ] Read a book", response,
                "Unmark confirmation message should match.");
    }

    @Test
    void testToString_emptyList() {
        String response = taskList.toString();
        assertEquals("\tHere are the tasks in your list:", response,
                "Empty task list should return correct message.");
    }

    @Test
    void testToString_withTasks() {
        taskList.addTask(new ToDo("todo Read a book"));
        taskList.addTask(new ToDo("todo Write notes"));
        String expected = "\tHere are the tasks in your list:\n\t1.[T][ ] Read a book\n\t2.[T][ ] Write notes";
        assertEquals(expected, taskList.toString(), "TaskList should return the correctly formatted string.");
    }


    @Test
    void testAddDuplicateTask() {
        Task task1 = new ToDo("todo Read a book");
        Task task2 = new ToDo("todo Read a book"); // Duplicate task
        taskList.addTask(task1);
        taskList.addTask(task2);

        assertEquals(1, taskList.getList().size(), "TaskList should allow duplicate tasks.");
        assertEquals("[T][ ] Read a book", taskList.getList().get(0).toString(), "First task should match.");
    }
}
