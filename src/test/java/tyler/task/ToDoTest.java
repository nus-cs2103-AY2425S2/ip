package tyler.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    void testToDoCreation() {
        ToDo todo = new ToDo("borrow book");
        assertEquals("[T][ ] borrow book", todo.toString());
    }

    @Test
    void testMarkToDo() {
        ToDo todo = new ToDo("borrow book");
        todo.markAsDone();
        assertEquals("[T][X] borrow book", todo.toString());
    }

    @Test
    void testUnmarkToDo() {
        ToDo todo = new ToDo("borrow book");
        todo.markAsDone();
        todo.markAsUndone();

        assertEquals("[T][ ] borrow book", todo.toString());
    }
}
