package Krypto.Task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void testToDoConstructor() {
        ToDo todo = new ToDo("todo read book");
        assertEquals("read book", todo.getDescription(), "ToDo description should be correctly set");
    }

    @Test
    public void testToString() {
        ToDo todo = new ToDo("todo read book");
        assertEquals("[T][] read book", todo.toString(), "ToDo toString format is incorrect");
    }

    @Test
    public void testToStringWhenMarked() {
        ToDo todo = new ToDo("todo read book");
        todo.markTask();
        assertEquals("[T][X] read book", todo.toString(), "ToDo toString should indicate completion");
    }

    @Test
    public void testToFileString() {
        ToDo todo = new ToDo("todo read book");
        assertEquals("T |  | read book", todo.toFileString(), "ToDo toFileString format is incorrect");
    }

    @Test
    public void testToFileStringWhenMarked() {
        ToDo todo = new ToDo("todo read book");
        todo.markTask();
        assertEquals("T | X | read book", todo.toFileString(), "ToDo toFileString should indicate completion");
    }
}