package tyler.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import tyler.command.Command;
import tyler.command.DateCommand;
import tyler.command.DeadlineCommand;
import tyler.command.DeleteCommand;
import tyler.command.EndCommand;
import tyler.command.EventCommand;
import tyler.command.ListCommand;
import tyler.command.MarkCommand;
import tyler.command.ToDoCommand;
import tyler.command.UnmarkCommand;


public class ParserTest {

    @Test
    void testParseCommandValid() {
        Command command = Parser.parse("todo read book");
        assertInstanceOf(ToDoCommand.class, command);

        command = Parser.parse("deadline return book /by 31/1/2025 2359");
        assertInstanceOf(DeadlineCommand.class, command);

        command = Parser.parse("event meeting /from 24/1/2025 1400 /to 24/1/2025 1600");
        assertInstanceOf(EventCommand.class, command);

        command = Parser.parse("mark 5");
        assertInstanceOf(MarkCommand.class, command);

        command = Parser.parse("unmark 4");
        assertInstanceOf(UnmarkCommand.class, command);

        command = Parser.parse("delete 3");
        assertInstanceOf(DeleteCommand.class, command);

        command = Parser.parse("date 2024-04-05");
        assertInstanceOf(DateCommand.class, command);

        command = Parser.parse("list");
        assertInstanceOf(ListCommand.class, command);

        command = Parser.parse("bye");
        assertInstanceOf(EndCommand.class, command);

    }
}
