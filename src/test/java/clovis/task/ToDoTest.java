package clovis.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void testTodoCreation() {
        ToDo todo = new ToDo("read book");
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void testMarkTodoAsDone() {
        ToDo todo = new ToDo("read book");
        todo.markAsDone();
        assertEquals("[T][X] read book", todo.toString());
    }

    @Test
    public void testMarkTodoAsNotDone() {
        ToDo todo = new ToDo("read book");
        todo.markAsNotDone();
        assertEquals("[T][ ] read book", todo.toString());
    }
}
