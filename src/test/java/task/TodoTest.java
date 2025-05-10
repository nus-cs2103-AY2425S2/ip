package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit tests for the Todo class.
 */
public class TodoTest {

    @Test
    public void testTodoToString_notDone() {
        Todo todo = new Todo("read books", false);
        String output = todo.toString();
        // Expect the exact format produced by your code.
        String expected = "[T][ ] read books";
        assertEquals(expected, output, "Todo toString() should match the expected output.");
    }

    @Test
    public void testTodoToFileString_notDone() {
        Todo todo = new Todo("read books", false);
        String expected = "T | 0 | read books";
        assertEquals(expected, todo.toFileString(), "Todo toFileString() output does not match expected string.");
    }
}
