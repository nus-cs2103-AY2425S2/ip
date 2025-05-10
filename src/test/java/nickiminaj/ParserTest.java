package nickiminaj;

import nickiminaj.command.*;
import nickiminaj.tasks.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void testValidCommands() throws DukeException {
        assertInstanceOf(ListCommand.class, Parser.parse("list"));
        assertInstanceOf(ByeCommand.class, Parser.parse("bye"));
        assertInstanceOf(MarkCommand.class, Parser.parse("mark 1"));
        assertInstanceOf(UnmarkCommand.class, Parser.parse("unmark 2"));
    }

    @Test
    public void testTodoCommand() throws DukeException {
        Command command = Parser.parse("todo Read a book");
        assertInstanceOf(AddCommand.class, command);
        AddCommand addCommand = (AddCommand) command;
        assertInstanceOf(Todo.class, addCommand.getTask());
        assertEquals("Read a book", addCommand.getTask().getDescription());
    }

    @Test
    public void testInvalidCommand() {
        Exception exception = assertThrows(DukeException.class, () -> {
            Parser.parse("randomtext");
        });
        assertEquals("I'm sorry, but I don't know what that means :-(", exception.getMessage());
    }

}
