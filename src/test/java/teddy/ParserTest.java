package teddy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    void splitInput_validInput_splitsCorrectly() {
        String input = "todo read book";
        String[] result = Parser.splitInput(input);

        assertEquals(2, result.length, "Input should split into two parts");
        assertEquals("todo", result[0], "First part should be 'todo'");
        assertEquals("read book", result[1], "Second part should be 'read book'");
    }

    @Test
    void splitInput_singleWord_commandSplitsCorrectly() {
        String input = "list";
        String[] result = Parser.splitInput(input);

        assertEquals(1, result.length, "Single-word input should not split further");
        assertEquals("list", result[0], "First part should be 'list'");
    }

    @Test
    void parseCommand_validCommand_returnsCorrectCommand() throws TeddyException {
        String[] parts = {"todo"};
        Command result = Parser.parseCommand(parts);

        assertEquals(Command.TODO, result, "Command should be parsed as TODO");
    }

    @Test
    void parseCommand_invalidCommand_throwsException() {
        String[] parts = {"unknown"};

        Exception exception = assertThrows(TeddyException.class, () -> Parser.parseCommand(parts));
        assertEquals("I don't understand the command: unknown", exception.getMessage(), "Should throw exception for unknown command");
    }
}
