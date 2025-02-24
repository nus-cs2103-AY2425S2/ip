package model.command;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import model.exception.AliceException;
import model.exception.CommandFormatException;
import utils.RandomUtils;

public class ParserTest {

    @Test
    public void parseCommand_byeCommand() throws AliceException {
        Command command = Parser.parseCommand("bye");
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void parseCommand_listCommand() throws AliceException {
        Command command = Parser.parseCommand("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void parseCommand_markCommand() throws AliceException {
        Command command = Parser.parseCommand("mark 1");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void parseCommand_unmarkCommand() throws AliceException {
        Command command = Parser.parseCommand("unmark 1");
        assertTrue(command instanceof UnmarkCommand);
    }

    @Test
    public void parseCommand_deleteCommand() throws AliceException {
        Command command = Parser.parseCommand("delete 1");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void parseCommand_todoCommand() throws AliceException {
        String todoName = RandomUtils.getRandomString(10);
        Command command = Parser.parseCommand("todo " + todoName);
        assertTrue(command instanceof TodoCommand);
    }

    @Test
    public void parseCommand_deadlineCommand() throws AliceException {
        String deadlineName = RandomUtils.getRandomString(10);
        String byDate = RandomUtils.getRandomDateTimeString();
        Command command = Parser.parseCommand("deadline " + deadlineName + " /by " + byDate);
        assertTrue(command instanceof DeadlineCommand);
    }

    @Test
    public void parseCommand_eventCommand() throws AliceException {
        String eventName = RandomUtils.getRandomString(10);
        String fromDate = RandomUtils.getRandomDateTimeString();
        String toDate = RandomUtils.getRandomDateTimeString();
        Command command = Parser.parseCommand("event " + eventName + " /from " + fromDate + " /to " + toDate);
        assertTrue(command instanceof EventCommand);
    }

    @Test
    public void parseCommand_findCommand() throws AliceException {
        String keyword = RandomUtils.getRandomString(5);
        Command command = Parser.parseCommand("find " + keyword);
        assertTrue(command instanceof FindCommand);
    }

    @Test
    public void parseCommand_invalidCommand_throwsCommandFormatException() {
        assertThrows(CommandFormatException.class, () -> {
            Parser.parseCommand("invalid command");
        });
    }
}
