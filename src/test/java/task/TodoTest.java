package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodoTest {

    private Todo todo;

    @BeforeEach
    void setUp() {
        todo = new Todo("Test Todo");
    }

    @Test
    void constructor_initializesCorrectly() {
        assertFalse(todo.isDone, "Todo should not be marked as done initially");
    }

    @Test
    void markAsDone_changesState() {
        todo.markStatusIcon();
        assertTrue(todo.isDone, "Todo should be marked as done after calling markAsDone()");
    }

    @Test
    void toString_formatsCorrectly() {
        assertEquals("[T][ ] Test Todo", todo.toString(),
                "toString should match expected format before marking as done");

        todo.markStatusIcon();
        assertEquals("[T][X] Test Todo", todo.toString(),
                "toString should reflect marked as done state");
    }

    @Test
    void toFileFormat_formatsCorrectly() {
        assertEquals("T | 0 | Test Todo", todo.toFileFormat(),
                "toFileFormat should match expected format before marking as done");

        todo.markStatusIcon();
        assertEquals("T | 1 | Test Todo", todo.toFileFormat(), "toFileFormat should reflect marked as done state");
    }
}
