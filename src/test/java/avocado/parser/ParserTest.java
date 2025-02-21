package avocado.parser;

import avocado.command.Command;
import avocado.command.ExitCommand;
import avocado.exception.AvocadoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    void testParseExitCommand() throws AvocadoException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ExitCommand, "Parser should return an ExitCommand.");
    }

    @Test
    void testParseInvalidCommand() throws AvocadoException {
        Exception exception = assertThrows(AvocadoException.class, () -> {
            Parser.parse("invalidCommand");
        });
        assertEquals("Oops! I don't understand this command.", exception.getMessage());
    }
}
