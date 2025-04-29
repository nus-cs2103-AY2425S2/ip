package xuxin.main;

import xuxin.command.AddDeadlineCommand;
import xuxin.command.AddEventCommand;
import xuxin.command.AddToDoCommand;
import xuxin.command.DeleteCommand;
import xuxin.command.MarkTaskCommand;
import xuxin.command.ExitCommand;
import xuxin.command.ListTaskCommand;
import xuxin.exception.DukeException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    void parserTest() throws DukeException  {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
        assertTrue(Parser.parse("list") instanceof ListTaskCommand);
        assertTrue(Parser.parse("todo a") instanceof AddToDoCommand);
        assertTrue(Parser.parse("mark 1") instanceof MarkTaskCommand);
        assertTrue(Parser.parse("delete 0") instanceof DeleteCommand);
        assertTrue(Parser.parse("todo todo") instanceof AddToDoCommand);
        assertTrue(Parser.parse("deadline deadline /by 25/07/2015") instanceof AddDeadlineCommand);
        assertTrue(Parser.parse("event event /from 26/07/2014 /to 26/07/2025") instanceof AddEventCommand);
    }
}
