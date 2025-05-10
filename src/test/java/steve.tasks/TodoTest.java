package steve.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodoTest {
    private ToDo validTodo;
    private ToDo invalidTodo;

    @BeforeEach
    public void setUp() {
        validTodo = new ToDo("Read a book");
        invalidTodo = new ToDo(""); // Invalid because description is empty
    }

    @Test
    public void testCodeMethod() {
        assertEquals("T", validTodo.code(), "Code method should return 'T' for ToDo tasks.");
    }

    @Test
    public void testTypeMethod() {
        assertEquals("Todo: ", validTodo.type(), "Type method should return 'Todo: '.");
    }

    @Test
    public void testValidTodoIsValid() {
        assertTrue(validTodo.isValid(), "Valid ToDo task should be marked as valid.");
    }

    @Test
    public void testInvalidTodoIsInvalid() {
        assertFalse(invalidTodo.isValid(), "Invalid ToDo task should be marked as invalid.");
    }

    @Test
    public void testOriginalDescription() {
        assertEquals("Read a book", validTodo.getOriginalDescription(), "Original description should match input.");
    }

    @Test
    public void testMessageStringForValidTodo() {
        String expectedMessage = "_________________________\n"
                + "     Got it. I've added this task:\n"
                + "       [T][ ] Read a book\n"
                + "     Now you have " + TaskManager.getTaskSize() + " tasks in the list.\n"
                + "_________________________\n";

        assertEquals(expectedMessage, validTodo.messageString(), "Message string should match expected output.");
    }

    @Test
    public void testMessageStringForInvalidTodo() {
        assertEquals("Description cannot be empty. Usage: todo <description>",
                invalidTodo.messageString(), "Message should indicate empty description error.");
    }
}
