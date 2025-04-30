package yuki.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import yuki.Parser;
import yuki.command.*;

public class ParserTest {

    @Test
    void testParseByeCommand() {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ExitCommand);
        assertTrue(command.isExit());
    }

    @Test
    void testParseListCommand() {
        Command command = Parser.parse("list");
        assertTrue(command instanceof BasicCommand);
        assertFalse(command.isExit());
    }

    @Test
    void testParseUndoCommand() {
        Command command = Parser.parse("undo");
        assertTrue(command instanceof UndoCommand);
        assertFalse(command.isExit());
    }

    @Test
    void testParseMarkCommand() {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof MarkCommand);
        assertFalse(command.isExit());
    }

    @Test
    void testParseUnmarkCommand() {
        Command command = Parser.parse("unmark 1");
        assertTrue(command instanceof UnmarkCommand);
        assertFalse(command.isExit());
    }

    @Test
    void testParseDeleteCommand() {
        Command command = Parser.parse("delete 1");
        assertTrue(command instanceof DeleteCommand);
        assertFalse(command.isExit());
    }

    @Test
    void testParseAddCommand() {
        Command command = Parser.parse("todo read book");
        assertTrue(command instanceof AddCommand);
        assertFalse(command.isExit());
    }

    @Test
    void testParseInvalidCommand() {
        Command command = Parser.parse("invalid");
        assertTrue(command instanceof ErrorCommand);
        assertFalse(command.isExit());
    }
}

