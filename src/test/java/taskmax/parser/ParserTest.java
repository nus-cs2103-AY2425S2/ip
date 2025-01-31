package taskmax.parser;

import org.junit.jupiter.api.Test;
import taskmax.command.ListCommand;
import taskmax.exception.TaskmaxException;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    void testParse_listCommand() throws TaskmaxException {
        assertTrue(Parser.parse("list") instanceof ListCommand);
    }
}
