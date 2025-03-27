package yapper.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import yapper.exceptions.MissingTaskArgs;
import yapper.tasktypes.ToDo;


class ParserTest {

    @Test
    void testValidToDoCreation() {
        ToDo todo = new ToDo("todo Read a book");
        assertEquals("[T][ ] Read a book", todo.toString(), "String representation should be formatted correctly.");
    }

    @Test
    void testToDoMissingTaskArgs() {
        Exception exception = assertThrows(MissingTaskArgs.class, () -> new ToDo("todo"),
                "Should throw MissingTaskArgs exception when no task name is provided.");
        assertEquals("\tHey! Don't just tell me the type of command, tell me what your task is. And leave a space between words, will ya.",
                exception.getMessage(), "Error message should match expected output.");
    }

    @Test
    void testToDoWhitespaceTaskName() {
        Exception exception = assertThrows(MissingTaskArgs.class, () -> new ToDo("todo "),
                "Should throw MissingTaskArgs exception when task name is only whitespace.");
        assertEquals("\tHey! Don't just tell me the type of command, tell me what your task is. And leave a space between words, will ya.",
                exception.getMessage(), "Error message should match expected output.");
    }

    @Test
    void testToDoToString() {
        ToDo todo = new ToDo("todo Finish homework");
        assertEquals("[T][ ] Finish homework", todo.toString(), "ToString should return formatted ToDo task.");
    }
}
