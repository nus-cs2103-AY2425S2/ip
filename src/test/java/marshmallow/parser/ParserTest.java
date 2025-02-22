package marshmallow.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import marshmallow.MarshmallowException;
import marshmallow.commands.Command;
import marshmallow.commands.ExitCommand;
import marshmallow.commands.ListAddDeadlineCommand;
import marshmallow.commands.ListAddEventCommand;
import marshmallow.commands.ListAddTodoCommand;
import marshmallow.commands.ListDeleteTaskCommand;
import marshmallow.commands.ListDisplayCommand;
import marshmallow.commands.ListTaskMarkCommand;
import marshmallow.commands.ListTaskUnmarkCommand;

public class ParserTest {
    @Test
    public void parse_listCommand_success() {
        Parser parser = new Parser();
        Command command = parser.parse(new Scanner("list"));
        assertInstanceOf(ListDisplayCommand.class, command);
    }

    @Test
    public void parse_addTodoCommand_success() {
        Parser parser = new Parser();
        Command command = parser.parse(new Scanner("todo test"));
        assertInstanceOf(ListAddTodoCommand.class, command);
    }

    @Test
    public void parse_addDeadlineCommand_success() {
        Parser parser = new Parser();
        Command command = parser.parse(new Scanner("deadline test /by 02-02-2025 1700"));
        assertInstanceOf(ListAddDeadlineCommand.class, command);
    }

    @Test
    public void parse_addDeadlineCommandWithoutEndTime_failure() {
        Parser parser = new Parser();
        assertThrows(MarshmallowException.class, () -> parser.parse(new Scanner("deadline test")));
    }

    @Test
    public void parse_addEventCommand_success() {
        Parser parser = new Parser();
        Command command = parser.parse(new Scanner("event test /from 02-02-2025 1700 /to 02-02-2025 1800"));
        assertInstanceOf(ListAddEventCommand.class, command);
    }

    @Test
    public void parse_addEventCommandWithoutStartTime_failure() {
        Parser parser = new Parser();
        assertThrows(MarshmallowException.class, () -> parser.parse(new Scanner("event test /to 02-02-2025 1800")));
    }

    @Test
    public void parse_addEventCommandWithoutEndTime_failure() {
        Parser parser = new Parser();
        assertThrows(MarshmallowException.class, () -> parser.parse(new Scanner("event test /from 02-02-2025 1700")));
    }

    @Test
    public void parse_addEventCommandWithoutTime_failure() {
        Parser parser = new Parser();
        assertThrows(MarshmallowException.class, () -> parser.parse(new Scanner("event test")));
    }

    @Test
    public void parse_markTaskCommand_success() {
        Parser parser = new Parser();
        Command command = parser.parse(new Scanner("mark 1"));
        assertInstanceOf(ListTaskMarkCommand.class, command);
    }

    @Test
    public void parse_unmarkTaskCommand_success() {
        Parser parser = new Parser();
        Command command = parser.parse(new Scanner("unmark 1"));
        assertInstanceOf(ListTaskUnmarkCommand.class, command);
    }

    @Test
    public void parse_deleteCommand_success() {
        Parser parser = new Parser();
        Command command = parser.parse(new Scanner("delete 1"));
        assertInstanceOf(ListDeleteTaskCommand.class, command);
    }

    @Test
    public void parse_exitCommand_success() {
        Parser parser = new Parser();
        Command command = parser.parse(new Scanner("bye"));
        assertInstanceOf(ExitCommand.class, command);
    }

    @Test
    public void parse_unknownCommand_failure() {
        Parser parser = new Parser();
        Command command = parser.parse(new Scanner("unknown"));
        assertNull(command);
    }

    @Test
    public void parse_unknownCommandWithArguments_failure() {
        Parser parser = new Parser();
        Command command = parser.parse(new Scanner("unknown command with arguments"));
        assertNull(command);
    }

}
