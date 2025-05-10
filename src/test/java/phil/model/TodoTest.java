package phil.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * Represents Testing Class for Todo Class.
 */
public class TodoTest {

    /**
     * Tests the toString method of the Todo class with a new Todo.
     */
    @Test
    public void toString_newTodo_correctStringOutput() {
        Todo todo = new Todo("read a book");
        assertEquals("[T][ ] read a book", todo.toString());
        todo.markDone();
        assertEquals("[T][X] read a book", todo.toString());
        todo.markNotDone();
        assertEquals("[T][ ] read a book", todo.toString());
    }

    /**
     * Tests the toLoadString method of the Todo class with a new Todo.
     */
    @Test
    public void toLoadString_newTodo_correctStringOutput() {
        Todo todo = new Todo("read a book");
        assertEquals("Todo -   - read a book", todo.toLoadString());
        todo.markDone();
        assertEquals("Todo - X - read a book", todo.toLoadString());
        todo.markNotDone();
        assertEquals("Todo -   - read a book", todo.toLoadString());
    }

}
