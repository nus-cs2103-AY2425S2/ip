package taskmax.parser;

import org.junit.jupiter.api.Test;

import taskmax.command.ListCommand;

import taskmax.exception.TaskmaxException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for the {@link Parser} class.
 */
class ParserTest {
    /**
     * Tests the parsing of a "list" command.
     *
     * @throws TaskmaxException If an error occurs during parsing.
     */
    @Test
    void testParse_listCommand() throws TaskmaxException {
        assertTrue(Parser.parse("list") instanceof ListCommand);
    }
}
