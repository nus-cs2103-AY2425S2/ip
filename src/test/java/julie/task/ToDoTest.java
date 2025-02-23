package julie.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import julie.Parser;
import julie.exception.WrongFormatException;


public class ToDoTest {

    @Test
    public void testToDoCreation() {
        ToDo todo = new ToDo("Read a book");
        assertEquals("[L] [T] [ ] Read a book",
                todo.toString(),
                "ToDo string representation is incorrect!");
    }

    @Test
    public void testMarkDone() {
        ToDo todo = new ToDo("Read a book");
        todo.markDone();
        assertEquals("[L] [T] [X] Read a book", todo.toString(), "Marking ToDo as done failed!");
    }

    @Test
    public void testMarkUndone() {
        ToDo todo = new ToDo("Read a book");
        todo.markDone();
        todo.markUndone();
        assertEquals("[L] [T] [ ] Read a book", todo.toString(), "Marking ToDo as undone failed!");
    }

    @Test
    public void testToFileFormat() {
        ToDo todo = new ToDo("Read a book");
        assertEquals("T | 0 | Read a book | L",
                todo.toFileFormat(),
                "ToFileFormat representation is incorrect!");

        todo.markDone();
        assertEquals("T | 1 | Read a book | L",
                todo.toFileFormat(),
                "ToFileFormat representation is incorrect");
    }

    @Test
    public void testToDoWithoutDescription() {
        Exception e = assertThrows(WrongFormatException.class, () -> {
            Parser.parse("todo");
        });
        assertEquals("Oops! The correct format for a todo is:\ntodo <description>",
                e.getMessage(),
                "Exception message for missing ToDo description is incorrect!");
    }
}
