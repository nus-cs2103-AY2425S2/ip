package yapper.taskTypes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import yapper.exceptions.MissingTaskArgs;
import yapper.tasktypes.ToDo;

class ToDoTest {

    @Test
    void testValidToDoCreation() {
        ToDo todo = new ToDo("todo Read a book");
        assertEquals("[T][ ] Read a book", todo.toString(), "ToDo string representation should match expected format.");
    }

    @Test
    void testToDoWhitespaceTaskName() {
        Exception exception = assertThrows(MissingTaskArgs.class, () -> {
            new ToDo(" ");
        });
        assertEquals("\tHey! Don't just tell me the type of command, tell me what your task is."
                        + " And leave a space between words, will ya.",
                exception.getMessage(), "Expected exception message for whitespace task name.");
    }

    @Test
    void testToDoMissingTaskArgs() {
        Exception exception = assertThrows(MissingTaskArgs.class, () -> {
            new ToDo("");
        });
        assertEquals("\tHey! Don't just tell me the type of command, tell me what your task is. "
                        + "And leave a space between words, will ya.",
                exception.getMessage(), "Expected exception message for missing task name.");
    }

    @Test
    void testToDoToString() {
        ToDo todo = new ToDo("todo Write a report");
        assertEquals("[T][ ] Write a report", todo.toString(), "ToDo string representation should match.");
    }
}
