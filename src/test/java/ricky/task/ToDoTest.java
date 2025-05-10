package ricky.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testToString() {
        ToDo todo = new ToDo("read book");
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void testStore() {
        ToDo todo = new ToDo("read book");
        assertEquals("T | 0 | read book", todo.storeInfo());
    }
}
