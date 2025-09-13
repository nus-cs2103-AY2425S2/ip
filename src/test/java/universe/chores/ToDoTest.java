package universe.chores;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit test class for ToDo.
 * This class tests the string representation and file format conversion of an Event object.
 */
public class ToDoTest {
    private ToDo todo = new ToDo("buy pens");

    /**
     * Tests the toString() method of the ToDo class.
     * Ensures that the string representation matches the expected format.
     */
    @Test
    public void toStringTest() {
        assertEquals("[T][ ] buy pens", todo.toString());
    }

    /**
     * Tests the toFileString() method of the ToDo class.
     * Ensures that the file format representation matches the expected format.
     */
    @Test
    public void toFileStringTest() {
        assertEquals("T | 0 | buy pens", todo.toFileString());
    }
}
