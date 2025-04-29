package friday.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import friday.command.AddCommand;
import friday.command.BasicCommand;
import friday.command.Command;
import friday.command.DeleteCommand;
import friday.fridayexceptions.FridayException;

public class ParserTest {
    @Test
    public void parser_parse_parserThrowsExceptionForInvalidCommand() {
        Exception exception = assertThrows(FridayException.class, () -> Parser.parse("invalidCommand"));
        assertEquals("please input a valid action",
                exception.getMessage(), "Expected error message for invalid command");
    }

    @Test
    public void parser_parse_parserThrowsExceptionForMissingDescription() {
        Exception exception = assertThrows(FridayException.class, () -> Parser.parse("todo"));
        assertEquals("please provide a description for your action",
                exception.getMessage(), "Expected error message for missing description");
    }

    @Test
    public void parser_parse_parserParseAddCommand() throws FridayException {
        Command command = Parser.parse("todo buy book");
        assertTrue(command instanceof AddCommand, "Expected AddCommand");
    }

    @Test
    public void parser_parse_parserParseDeleteCommand() throws FridayException {
        Command command = Parser.parse("delete 3");
        assertTrue(command instanceof DeleteCommand, "Expected DeleteCommand");
    }

    @Test
    public void parser_parse_parserParseBasicCommand() throws FridayException {
        Command command = Parser.parse("mark 2");
        assertTrue(command instanceof BasicCommand, "Expected BasicCommand");
    }
}
