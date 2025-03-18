package clank.utility;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import clank.command.AddCommand;
import clank.command.ByeCommand;
import clank.command.Command;
import clank.command.ListCommand;
import clank.exception.ClankException;

/**
 * Unit tests for the {@code Parser} class.
 */
public class ParserTest {

    /**
     * Tests that parsing "bye" returns a {@code ByeCommand}.
     */
    @Test
    public void testParseByeCommand() {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ByeCommand);
    }

    /**
     * Tests that parsing "list" returns a {@code ListCommand}.
     */
    @Test
    public void testParseListCommand() {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    /**
     * Tests that parsing a valid "todo" command returns an {@code AddCommand}.
     */
    @Test
    public void testParseAddCommand() {
        Command command = Parser.parse("todo Read book");
        assertTrue(command instanceof AddCommand);
    }

    /**
     * Tests that an unknown command throws an {@code UnknownCommandException}.
     */
    @Test
    public void testParseUnknownCommand() {
        assertThrows(ClankException.class, () -> Parser.parse("unknownCommand"));
    }
}
