package buddy.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import buddy.command.AddDeadlineCommand;
import buddy.command.AddEventCommand;
import buddy.command.AddTodoCommand;
import buddy.command.ByeCommand;
import buddy.command.Command;
import buddy.command.DeleteCommand;
import buddy.command.FindCommand;
import buddy.command.ListTasksCommand;
import buddy.command.MarkCommand;
import buddy.command.UnmarkCommand;
import buddy.exception.BuddyException;
import buddy.exception.BuddyInvalidCommandException;
import buddy.exception.BuddyMissingCommandInfoException;

public class ParserTest {

    /**
     * Test if invalid input to the parse method will throws BuddyException or not.
     */
    @Test
    void parseCommand_byeCommand_returnsByeCommand() throws BuddyException {
        Command command = Parser.parseCommand("bye");
        assertTrue(command instanceof ByeCommand);
    }

    @Test
    void parseCommand_listCommand_returnsListTasksCommand() throws BuddyException {
        Command command = Parser.parseCommand("list");
        assertTrue(command instanceof ListTasksCommand);
    }

    @Test
    void parseCommand_validTodoCommand_returnsAddTodoCommand() throws BuddyException {
        Command command = Parser.parseCommand("todo read book");
        assertTrue(command instanceof AddTodoCommand);

        AddTodoCommand todoCommand = (AddTodoCommand) command;
        assertEquals("read book", todoCommand.getArgs().get(0));
    }

    @Test
    void parseCommand_validDeadlineCommand_returnsAddDeadlineCommand() throws BuddyException {
        Command command = Parser.parseCommand("deadline submit report /by 2024-02-15 1000");
        assertTrue(command instanceof AddDeadlineCommand);

        AddDeadlineCommand deadlineCommand = (AddDeadlineCommand) command;
        assertEquals("submit report", deadlineCommand.getArgs().get(0));
        assertEquals("2024-02-15 1000", deadlineCommand.getArgs().get(1));
    }

    @Test
    void parseCommand_validEventCommand_returnsAddEventCommand() throws BuddyException {
        Command command = Parser.parseCommand("event project meeting /from 2024-02-15 1000 /to 2024-02-15 1400");
        assertTrue(command instanceof AddEventCommand);

        AddEventCommand eventCommand = (AddEventCommand) command;
        assertEquals("project meeting", eventCommand.getArgs().get(0));
        assertEquals("2024-02-15 1000", eventCommand.getArgs().get(1));
        assertEquals("2024-02-15 1400", eventCommand.getArgs().get(2));
    }

    @Test
    void parseCommand_validMarkCommand_returnsMarkCommand() throws BuddyException {
        Command command = Parser.parseCommand("mark 3");
        assertTrue(command instanceof MarkCommand);

        MarkCommand markCommand = (MarkCommand) command;
        assertEquals("3", markCommand.getArgs().get(0));
    }

    @Test
    void parseCommand_validUnmarkCommand_returnsUnmarkCommand() throws BuddyException {
        Command command = Parser.parseCommand("unmark 2");
        assertTrue(command instanceof UnmarkCommand);

        UnmarkCommand unmarkCommand = (UnmarkCommand) command;
        assertEquals("2", unmarkCommand.getArgs().get(0));
    }

    @Test
    void parseCommand_validDeleteCommand_returnsDeleteCommand() throws BuddyException {
        Command command = Parser.parseCommand("delete 1");
        assertTrue(command instanceof DeleteCommand);

        DeleteCommand deleteCommand = (DeleteCommand) command;
        assertEquals("1", deleteCommand.getArgs().get(0));
    }

    @Test
    void parseCommand_validFindCommand_returnsFindCommand() throws BuddyException {
        Command command = Parser.parseCommand("find homework");
        assertTrue(command instanceof FindCommand);

        FindCommand findCommand = (FindCommand) command;
        assertEquals("homework", findCommand.getArgs().get(0));
    }

    @Test
    void parseCommand_invalidCommand_throwsBuddyInvalidCommandException() {
        assertThrows(BuddyInvalidCommandException.class, () -> {
            Parser.parseCommand("invalidcommand");
        });
    }

    @Test
    void parseCommand_missingArguments_throwsBuddyMissingCommandInfoException() {
        assertThrows(BuddyMissingCommandInfoException.class, () -> {
            Parser.parseCommand("todo");
        });

        assertThrows(BuddyMissingCommandInfoException.class, () -> {
            Parser.parseCommand("deadline");
        });

        assertThrows(BuddyMissingCommandInfoException.class, () -> {
            Parser.parseCommand("event");
        });

        assertThrows(BuddyMissingCommandInfoException.class, () -> {
            Parser.parseCommand("mark");
        });

        assertThrows(BuddyMissingCommandInfoException.class, () -> {
            Parser.parseCommand("unmark");
        });

        assertThrows(BuddyMissingCommandInfoException.class, () -> {
            Parser.parseCommand("delete");
        });

        assertThrows(BuddyMissingCommandInfoException.class, () -> {
            Parser.parseCommand("find");
        });
    }
}
