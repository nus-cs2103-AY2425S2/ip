package charlie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTest {

    @Test
    void testTodoCreation() {
        Todo todo = new Todo("read book");
        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    void testTodoWithMarking() {
        Todo todo = new Todo("submit assignment", true);
        assertEquals("[T][X] submit assignment", todo.toString());
    }

    @Test
    void testWriteToFile() {
        Todo todo = new Todo("write report", false);
        assertEquals("T|0|write report\n", todo.writeToFile());

        Todo markedTodo = new Todo("write report", true);
        assertEquals("T|1|write report\n", markedTodo.writeToFile());
    }
}