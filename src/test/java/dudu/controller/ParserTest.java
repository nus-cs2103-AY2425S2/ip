package dudu.controller;

import dudu.Dudu;
import dudu.utils.DuduException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test
    public void testParseMarkCommand() throws DuduException {
        Command command = Parser.parse("mark 2");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void testParseUnmarkCommand() throws DuduException {
        Command command = Parser.parse("unmark 2");
        assertTrue(command instanceof UnmarkCommand);
    }

    @Test
    public void testParseTodoCommand() throws DuduException {
        Command command = Parser.parse("todo Birthday Party");
        assertTrue(command instanceof TodoCommand);
    }

    @Test
    public void testParseDeadlineCommand() throws DuduException {
        Command command = Parser.parse("deadline CS2103T Tutorial /by 30/1/2025 1500");
        assertTrue(command instanceof DeadlineCommand);
    }

    @Test
    public void testParseEventCommand() throws DuduException {
        Command command = Parser.parse("event CS2103T Lecture /from 31/1/2025 1600 /to 31/1/2025 1800");
        assertTrue(command instanceof EventCommand);
    }

    @Test
    public void testParseDeleteCommand() throws DuduException {
        Command command = Parser.parse("delete 1");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void testParseListCommand() throws DuduException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void testParseHelpCommand() throws DuduException {
        Command command = Parser.parse("help");
        assertTrue(command instanceof HelpCommand);
    }

    @Test
    public void testParseByeCommand() throws DuduException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ByeCommand);
    }

    @Test
    public void testParseInvalidCommand() throws DuduException {
        Command command = Parser.parse("hello");
        assertTrue(command instanceof InvalidCommand);
    }

}
