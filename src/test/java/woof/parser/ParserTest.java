package woof.parser;

import org.junit.jupiter.api.Test;
import woof.command.AddTodoCommand;
import woof.command.Command;
import woof.command.MarkCommand;
import woof.task.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void testParseCreateTodo_validInput_success() throws Exception {
        String input = "todo Read a book";
        Command command = Parser.parse(input);
        assertTrue(command instanceof AddTodoCommand);
    }

    @Test
    public void testParseCreateDeadline_missingByKeyword_throwsException() {
        String input = "deadline Submit assignment";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Parser.parse(input);
        });

        assertEquals("""
                WERWER! Something in deadline is missing!
                Follow this template woof: deadline (your task) /by (deadline)""", exception.getMessage());
    }

    @Test
    public void testParseMark_validInput_success() throws Exception {
        TaskList.addTodo("Read a book");
        String input = "mark 1";
        Command command = Parser.parse(input);

        assertTrue(command instanceof MarkCommand);

        MarkCommand markCommand = (MarkCommand) command;
    }
}
