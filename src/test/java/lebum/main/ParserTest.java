package lebum.main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import lebum.command.*;
import lebum.exception.DukeException;

class ParserTest {

    @Test
    void parse_todoCommand_returnsAddToDoCommand() throws DukeException {
        Command result = Parser.parse("todo read book");
        assertTrue(result instanceof AddToDoCommand);
    }
    @Test
    void parse_findCommand_returnsFindCommand() throws DukeException {
        Command result = Parser.parse("find book");
        assertTrue(result instanceof FindCommand);
    }
}