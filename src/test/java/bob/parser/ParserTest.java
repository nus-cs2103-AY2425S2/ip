package bob.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import bob.command.Command;
import bob.command.DeadlineCommand;
import bob.command.DeleteCommand;
import bob.command.EventCommand;
import bob.command.ExitCommand;
import bob.command.ListCommand;
import bob.command.MarkCommand;
import bob.command.TodoCommand;
import bob.command.UnmarkCommand;
import bob.command.WrongCommandException;

/**
 * Tests whether, based on a given user input, whether the correct command will be returned.
 * Does NOT execute the command.
 */
public class ParserTest {
    @Test
    public void testParseExit_validInput_returnsExitCommand() throws WrongCommandException, IOException {
        String userInput = "bye";
        Command c = Parser.parse(userInput);
        assertTrue(c instanceof ExitCommand);
    }

    @Test
    public void testParseList_validInput_returnsListcommand() throws WrongCommandException, IOException {
        String userInput = "list";
        Command c = Parser.parse(userInput);
        assertTrue(c instanceof ListCommand);
    }

    @Test
    public void testParseMark_validInput_returnsMarkCommand() throws WrongCommandException, IOException {
        String userInput = "mark 1";
        Command c = Parser.parse(userInput);
        assertTrue(c instanceof MarkCommand);
    }

    @Test
    public void testParseUnmark_validInput_returnsUnmarkCommand() throws WrongCommandException, IOException {
        String userInput = "unmark 1";
        Command c = Parser.parse(userInput);
        assertTrue(c instanceof UnmarkCommand);
    }

    @Test
    public void testParseTodo_validInput_returnsTodoCommand() throws WrongCommandException, IOException {
        String userInput = "todo homework";
        Command c = Parser.parse(userInput);
        assertTrue(c instanceof TodoCommand);
    }

    @Test
    public void testParseDeadline_validInput_returnsDeadlineCommand() throws WrongCommandException, IOException {
        String userInput = "deadline assignment /by 01/03/2025 2359";
        Command c = Parser.parse(userInput);
        assertTrue(c instanceof DeadlineCommand);
    }

    @Test
    public void testParseEvent_validInput_returnsEventCommand() throws WrongCommandException, IOException {
        String userInput = "event meeting /from 05/03/2025 1400 /to 05/03/2025 1630";
        Command c = Parser.parse(userInput);
        assertTrue(c instanceof EventCommand);
    }

    @Test
    public void testParseDelete_validInput_returnsDeleteCommand() throws WrongCommandException, IOException {
        String userInput = "delete 1";
        Command c = Parser.parse(userInput);
        assertTrue(c instanceof DeleteCommand);
    }

    @Test
    public void testParseMark_invalidNonNumericalInput_throwsNumberFormatException() {
        String userInput = "unmark abc";

        assertThrows(NumberFormatException.class, () -> {
            Parser.parse(userInput);
        });
    }

    @Test
    public void testParseUnmark_invalidNonNumericalInput_throwsNumberFormatException() {
        String userInput = "unmark abc";

        assertThrows(NumberFormatException.class, () -> {
            Parser.parse(userInput);
        });
    }

    @Test
    public void testParse_invalidInput_throwsWrongCommandException() {
        String userInput = "blah blah blah";

        assertThrows(WrongCommandException.class, () -> {
            Parser.parse(userInput);
        });
    }
}
