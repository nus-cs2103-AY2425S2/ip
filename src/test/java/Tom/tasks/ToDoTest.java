package Tom.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Tests the functionality of the ToDo class.
 */
public class ToDoTest {

    @Test
    public void testToDoCreation() {
        ToDo todo = new ToDo("Buy groceries");
        assertEquals("Buy groceries", todo.getDescription());
        assertFalse(todo.getStringStatus());
        assertEquals("[T][ ] Buy groceries", todo.toString());
    }

    @Test
    public void testMarkAsCompleted() {
        ToDo todo = new ToDo("Buy groceries");
        todo.markTask();
        assertTrue(todo.getStringStatus());
        assertEquals("[T][X] Buy groceries", todo.toString());
    }

    @Test
    public void testMarkAsIncomplete() {
        ToDo todo = new ToDo("Buy groceries", true);
        todo.unmarkTask();
        assertFalse(todo.getStringStatus());
        assertEquals("[T][ ] Buy groceries", todo.toString());
    }
}