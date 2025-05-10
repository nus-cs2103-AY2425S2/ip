package kaji.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToDoTest {

    @Test
    public void testToDoCreation() {
        ToDo todo = new ToDo("T", false, "Read a book");
        assertEquals("T", todo.getType());
        assertFalse(todo.isDone());
        assertEquals("Read a book", todo.getDescription());
    }

    @Test
    public void testToDoToString() {
        ToDo todo = new ToDo("T", false, "Read a book");
        assertEquals("[T][ ] Read a book", todo.toString());
    }

    @Test
    public void testToDoMarkTask() {
        ToDo todo = new ToDo("T", false, "Read a book");
        todo.markTask();
        assertTrue(todo.isDone());
        assertEquals("[T][X] Read a book", todo.toString());
    }

    @Test
    public void testToDoUnmarkTask() {
        ToDo todo = new ToDo("T", true, "Read a book");
        todo.unmarkTask();
        assertFalse(todo.isDone());
        assertEquals("[T][ ] Read a book", todo.toString());
    }
}
