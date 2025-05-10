package botato;

import botato.command.*;
import botato.exception.InvalidCommandException;
import botato.exception.InvalidTaskNumberException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parse_validExitCommand_returnsExitCommand() {
        Command command = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, command);
    }

    @Test
    void parse_validListCommand_returnsListCommand() {
        Command command = Parser.parse("list");
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    void parse_validMarkCommand_returnsMarkCommand() {
        TaskList.initialize(3); // Mock TaskList with 3 tasks
        Command command = Parser.parse("mark 2");
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    void parse_markInvalidTaskNumber_throwsException() {
        TaskList.initialize(2); // Mock TaskList with 2 tasks
        assertThrows(InvalidTaskNumberException.class, () -> Parser.parse("mark 5"));
    }

    @Test
    void parse_validUnmarkCommand_returnsUnmarkCommand() {
        TaskList.initialize(3); // Mock TaskList with 3 tasks
        Command command = Parser.parse("unmark 1");
        assertInstanceOf(UnmarkCommand.class, command);
    }

    @Test
    void parse_validTodoCommand_returnsTodoCommand() {
        Command command = Parser.parse("todo Read book");
        assertInstanceOf(TodoCommand.class, command);
    }

    @Test
    void parse_validDeadlineCommand_returnsDeadlineCommand() {
        Command command = Parser.parse("deadline Submit assignment /by tomorrow");
        assertInstanceOf(DeadlineCommand.class, command);
    }

    @Test
    void parse_validEventCommand_returnsEventCommand() {
        Command command = Parser.parse("event Party /from 6pm /to 10pm");
        assertInstanceOf(EventCommand.class, command);
    }

    @Test
    void parse_validDeleteCommand_returnsDeleteCommand() {
        TaskList.initialize(5); // Mock TaskList with 5 tasks
        Command command = Parser.parse("delete 3");
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    void parse_deleteInvalidTaskNumber_throwsException() {
        TaskList.initialize(3); // Mock TaskList with 3 tasks
        assertThrows(InvalidTaskNumberException.class, () -> Parser.parse("delete 10"));
    }

    @Test
    void parse_validHelpCommand_returnsHelpCommand() {
        Command command = Parser.parse("help");
        assertInstanceOf(HelpCommand.class, command);
    }

    @Test
    void parse_validFindCommand_returnsFindCommand() {
        Command command = Parser.parse("find homework");
        assertInstanceOf(FindCommand.class, command);
    }

    @Test
    void parse_findCommandWithoutKeyword_throwsException() {
        assertThrows(InvalidCommandException.class, () -> Parser.parse("find"));
    }

    @Test
    void parse_invalidCommand_throwsException() {
        assertThrows(InvalidCommandException.class, () -> Parser.parse("randomCommand"));
    }
}
