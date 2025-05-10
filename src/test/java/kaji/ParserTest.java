package kaji;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import kaji.command.AddCommand;
import kaji.command.Command;
import kaji.command.DeleteCommand;
import kaji.command.MarkCommand;
import kaji.command.UnmarkCommand;

public class ParserTest {

    @Test
    public void testParseTodoCommand() throws KajiException {
        Command command = Parser.parse("todo Read a book");
        assertInstanceOf(AddCommand.class, command);
        AddCommand addCommand = (AddCommand) command;
        assertEquals("T", addCommand.getType());
        assertEquals("Read a book", addCommand.getDescription());
    }

    @Test
    public void testParseTodoCommandWithoutDescription() {
        Exception exception = assertThrows(KajiException.class, () -> {
            Parser.parse("todo");
        });
        String expectedMessage = "Invalid todo command";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testParseDeadlineCommand() throws KajiException {
        Command command = Parser.parse("deadline Submit report /by 2025-09-01 11:00");
        assertInstanceOf(AddCommand.class, command);
        AddCommand addCommand = (AddCommand) command;
        assertEquals("D", addCommand.getType());
        assertEquals("Submit report /by 2025-09-01T11:00", addCommand.getDescription());
    }

    @Test
    public void testParseDeadlineCommandInvalidFormat() {
        Exception exception = assertThrows(KajiException.class, () -> {
            Parser.parse("deadline Submit report /by invalid-date");
        });
        String expectedMessage = "Invalid date & time format";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testParseEventCommand() throws KajiException {
        Command command = Parser.parse("event Project meeting /from 2025-09-01 10:00 /to 2025-09-01 12:00");
        assertInstanceOf(AddCommand.class, command);
        AddCommand addCommand = (AddCommand) command;
        assertEquals("E", addCommand.getType());
        assertEquals("Project meeting /from 2025-09-01 10:00 /to 2025-09-01 12:00", addCommand.getDescription());
    }

    @Test
    public void testParseEventCommandInvalidFormat() {
        Exception exception = assertThrows(KajiException.class, () -> {
            Parser.parse("event Project meeting /from invalid-date /to invalid-date");
        });
        String expectedMessage = "Invalid event command";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testParseMarkCommand() throws KajiException {
        Command command = Parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, command);
        MarkCommand markCommand = (MarkCommand) command;
        assertEquals(1, markCommand.getTaskId());
    }

    @Test
    public void testParseMarkCommandInvalidFormat() {
        Exception exception = assertThrows(KajiException.class, () -> {
            Parser.parse("mark invalid-number");
        });
        String expectedMessage = "Invalid task list number";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testParseUnmarkCommand() throws KajiException {
        Command command = Parser.parse("unmark 1");
        assertInstanceOf(UnmarkCommand.class, command);
        UnmarkCommand unmarkCommand = (UnmarkCommand) command;
        assertEquals(1, unmarkCommand.getTaskId());
    }

    @Test
    public void testParseUnmarkCommandInvalidFormat() {
        Exception exception = assertThrows(KajiException.class, () -> {
            Parser.parse("unmark invalid-number");
        });
        String expectedMessage = "Invalid task list number";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testParseDeleteCommand() throws KajiException {
        Command command = Parser.parse("delete 1");
        assertInstanceOf(DeleteCommand.class, command);
        DeleteCommand deleteCommand = (DeleteCommand) command;
        assertEquals(1, deleteCommand.getTaskId());
    }

    @Test
    public void testParseDeleteCommandInvalidFormat() {
        Exception exception = assertThrows(KajiException.class, () -> {
            Parser.parse("delete invalid-number");
        });
        String expectedMessage = "Invalid task list number";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testParseUnknownCommand() {
        Exception exception = assertThrows(KajiException.class, () -> {
            Parser.parse("unknown command");
        });
        String expectedMessage = "Unknown command: unknown";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
