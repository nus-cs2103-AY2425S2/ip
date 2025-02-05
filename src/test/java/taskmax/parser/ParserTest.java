package taskmax.parser;

import org.junit.jupiter.api.Test;
import taskmax.command.ListCommand;
import taskmax.exception.TaskmaxException;
import taskmax.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {
    @Test
    void testParse_listCommand() throws TaskmaxException {
        assertTrue(Parser.parse("list") instanceof ListCommand);
    }
}
