package clank.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@code Todo} class.
 */
public class TodoTest {

    /**
     * Tests the creation of a Todo task and its string representation.
     */
    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("Buy groceries");
        assertEquals("[T][ ] Buy groceries", todo.toString());
    }

    /**
     * Tests that the save format of a Todo task is correctly formatted.
     */
    @Test
    public void testSaveFormat() {
        Todo todo = new Todo("Buy groceries");
        assertEquals("T|false|Buy groceries", todo.toSaveFormat());
    }
}
