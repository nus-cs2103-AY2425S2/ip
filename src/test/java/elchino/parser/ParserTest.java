package elchino.parser;

import elchino.commands.*;
import elchino.exceptions.ElchinoException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ParserTest {
    @Test
    public void testParseEmptyInput() throws ElchinoException {
        Command command = Parser.parse("");
        assertInstanceOf(InvalidCommand.class, command, "Empty input should return InvalidCommand");
    }

    @Test
    public void testParseFindCommand() throws ElchinoException {
        Command command = Parser.parse("find homework");
        assertInstanceOf(FindCommand.class, command, "find command should return FindCommand");
    }
}
