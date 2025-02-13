package zoe.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testParseTodoCommand() {
        Command command = Parser.parse("todo Read book");
        assertInstanceOf(TodoCommand.class, command);
        assertEquals("Read book", ((TodoCommand) command).getTaskName());
    }

    @Test
    public void testParseDeadlineCommand() {
        Command command = Parser.parse("deadline Submit assignment /by 2024-12-01");
        assertInstanceOf(DeadlineCommand.class, command);
        assertEquals("Submit assignment", ((DeadlineCommand) command).getTaskName());
        assertEquals(LocalDate.of(2024, 12, 1), ((DeadlineCommand) command).getBy());
    }

    @Test
    public void testParseIncorrectCommand() {
        Command command = Parser.parse("");
        assertInstanceOf(IncorrectCommand.class, command);
    }
}
