package baymax;

import baymax.command.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parse_listCommand_returnsListCommand() {
        Command command = Parser.parse("list");
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    void parse_markCommand_returnsMarkCommand() {
        Command command = Parser.parse("mark 2");
        assertInstanceOf(MarkCommand.class, command);
        assertEquals(2, ((MarkCommand) command).getTaskNumber()); // Ensure task number is correctly parsed
    }

    @Test
    void parse_unmarkCommand_returnsUnmarkCommand() {
        Command command = Parser.parse("unmark 3");
        assertInstanceOf(UnmarkCommand.class, command);
        assertEquals(3, ((UnmarkCommand) command).getTaskNumber());
    }

    @Test
    void parse_todoCommand_returnsAddTodoCommand() {
        Command command = Parser.parse("todo buy milk");
        assertInstanceOf(AddTodoCommand.class, command);
    }

    @Test
    void parse_deadlineCommand_returnsAddDeadlineCommand() {
        Command command = Parser.parse("deadline return book /by 2025-03-10");
        assertInstanceOf(AddDeadlineCommand.class, command);
    }

    @Test
    void parse_eventCommand_returnsAddEventCommand() {
        Command command = Parser.parse("event project meeting /from 2025-04-01 1400 /to 2025-04-01 1600");
        assertInstanceOf(AddEventCommand.class, command);
    }

    @Test
    void parse_deleteCommand_returnsDeleteCommand() {
        Command command = Parser.parse("delete 5");
        assertInstanceOf(DeleteCommand.class, command);
        assertEquals(5, ((DeleteCommand) command).getTaskNumber());
    }

    @Test
    void parse_findCommand_returnsFindCommand() {
        Command command = Parser.parse("find book");
        assertInstanceOf(FindCommand.class, command);
    }

    @Test
    void parse_invalidCommand_returnsErrorCommand() {
        Command command = Parser.parse("invalidCommand");
        assertInstanceOf(ErrorCommand.class, command);
    }

    @Test
    void parse_missingArgumentForMark_throwsException() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Parser.parse("mark"));
    }

    @Test
    void parse_missingArgumentForDelete_throwsException() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Parser.parse("delete"));
    }

    @Test
    void parse_emptyInput_returnsErrorCommand() {
        Command command = Parser.parse("");
        assertInstanceOf(ErrorCommand.class, command);
    }
}
