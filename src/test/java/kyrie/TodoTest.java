package kyrie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Represents a test class for Todo.
 */
public class TodoTest {
    /**
     * Tests the constructor for Todo.
     */
    @Test
    public void testToDoConstructor() {
        Todo todo = new Todo("Sample Task");
        assertEquals("Sample Task", todo.getName());
        assertEquals(false, todo.isDone());
        todo.markAsDone();
        assertEquals(true, todo.isDone());
        todo.unmarkAsDone();
        assertEquals(false, todo.isDone());
    }

    /**
     * Tests the toString method for Todo.
     */
    @Test
    public void testToString() {
        Todo todo = new Todo("Sample Task");
        assertEquals("[T][ ] Sample Task", todo.toString());
        todo.markAsDone();
        assertEquals("[T][X] Sample Task", todo.toString());
    }

    /**
     * Tests the toFileString method for Todo.
     */
    @Test
    public void testToFileString() {
        Todo todo = new Todo("Sample Task");
        assertEquals("TODO | 0 | Sample Task", todo.toFileString());
        todo.markAsDone();
        assertEquals("TODO | 1 | Sample Task", todo.toFileString());
    }
}