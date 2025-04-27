package Ninon;

import Ninon.Command.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void testParseByeCommand() throws NinonException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    void testParseListCommand() throws NinonException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    void testParseMarkCommand() throws NinonException {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    void testParseUnmarkCommand() throws NinonException {
        Command command = Parser.parse("unmark 1");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    void testParseTodoCommand() throws NinonException {
        Command command = Parser.parse("todo Read book");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    void testParseTodoCommandException() {
        Exception exception = assertThrows(NinonException.class, () -> Parser.parse("todo"));
        assertEquals("OOPS!!! The description of a todo cannot be empty.", exception.getMessage());
    }

    @Test
    void testParseDeadlineCommand() throws NinonException {
        Command command = Parser.parse("deadline Submit report /by 2025-02-08");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    void testParseDeadlineCommandException() {
        Exception exception = assertThrows(NinonException.class, () -> Parser.parse("deadline"));
        assertEquals("OOPS!!! The description of a deadline cannot be empty.", exception.getMessage());
    }

    @Test
    void testParseEventCommand() throws NinonException {
        Command command = Parser.parse("event Conference /from 2025-02-08 /to 2025-02-09");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    void testParseEventCommandException() {
        Exception exception = assertThrows(NinonException.class, () -> Parser.parse("event"));
        assertEquals("OOPS!!! The description of a event cannot be empty.", exception.getMessage());
    }

    @Test
    void testParseDeleteCommand() throws NinonException {
        Command command = Parser.parse("delete 1");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    void testParseUnknownCommandException() {
        Exception exception = assertThrows(NinonException.class, () -> Parser.parse("unknown"));
        assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", exception.getMessage());
    }
}
