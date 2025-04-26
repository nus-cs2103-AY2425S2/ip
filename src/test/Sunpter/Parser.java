package parser;

import command.*;
import exception.SunpterException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private final Parser parser = new Parser();

    @Test
    void parseCommand_validCommands_returnCorrectCommandInstances() throws SunpterException {
        assertInstanceOf(AddCommand.class, parser.parseCommand("ADD task"));
        assertInstanceOf(DeleteCommand.class, parser.parseCommand("DELETE 1"));
        assertInstanceOf(ListCommand.class, parser.parseCommand("LIST"));
        assertInstanceOf(MarkCommand.class, parser.parseCommand("MARK 2"));
        assertInstanceOf(UnmarkCommand.class, parser.parseCommand("UNMARK 3"));
        assertInstanceOf(ByeCommand.class, parser.parseCommand("BYE"));
        assertInstanceOf(SearchCommand.class, parser.parseCommand("SEARCH keyword"));
        assertInstanceOf(ListPriorityCommand.class, parser.parseCommand("PRIORITY"));
        assertInstanceOf(HelpCommand.class, parser.parseCommand("HELP"));
    }

    @Test
    void parseCommand_invalidCommand_returnsInvalidCommand() throws SunpterException {
        Command command = parser.parseCommand("RANDOMCOMMAND");
        assertInstanceOf(InvalidCommand.class, command);
    }

    @Test
    void parseCommand_emptyOrNullInput_throwsSunpterException() {
        assertThrows(SunpterException.class, () -> parser.parseCommand(""));
        assertThrows(SunpterException.class, () -> parser.parseCommand("   "));
        assertThrows(SunpterException.class, () -> parser.parseCommand(null));
    }

    @Test
    void getNumber_invalidNumberFormat_throwsNumberFormatException() {
        assertThrows(NumberFormatException.class, () -> parser.parseCommand("DELETE abc"));
    }

    @Test
    void removeFirstWord_validString_returnsCorrectSubstring() throws SunpterException {
        assertEquals("task details", Parser.removeFirstWord("ADD task details"));
    }

    @Test
    void removeFirstWord_invalidInput_throwsSunpterException() {
        assertThrows(SunpterException.class, () -> Parser.removeFirstWord(""));
        assertThrows(SunpterException.class, () -> Parser.removeFirstWord("   "));
        assertThrows(SunpterException.class, () -> Parser.removeFirstWord("ADD"));
    }
}
