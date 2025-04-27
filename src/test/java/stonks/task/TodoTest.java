package stonks.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {
    @Test
    public void stringTest() {
        Todo todo = new Todo("test");
        assertEquals(todo.toString(), "[T][ ] test" );
    }

    @Test
    public void markTest() {
        Todo todo = new Todo("test");
        todo.markDone();
        assertTrue(todo.isDone);
        assertEquals(todo.toString(), "[T][X] test");
        todo.markNotDone();
        assertFalse(todo.isDone);
        assertEquals(todo.toString(), "[T][ ] test" );
    }
}
