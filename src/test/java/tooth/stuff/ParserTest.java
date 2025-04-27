package tooth.stuff;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import tooth.command.ByeCommand;
import tooth.command.Command;
import tooth.command.DeadlineCommand;
import tooth.command.DeleteCommand;
import tooth.command.EventCommand;
import tooth.command.ListCommand;
import tooth.command.LoadCommand;
import tooth.command.MarkCommand;
import tooth.command.SaveCommand;
import tooth.command.TodoCommand;
import tooth.command.UnmarkCommand;
import tooth.exception.InvalidParamException;



public class ParserTest {
    @Test
    public void byeTest() {
        Parser p = new Parser();
        Command c = p.parse("bye");
        assertInstanceOf(ByeCommand.class, c);
        assertThrows(InvalidParamException.class, () -> p.parse("bye 1"));
    }

    @Test
    public void listTest() {
        Parser p = new Parser();
        Command c = p.parse("list");
        assertInstanceOf(ListCommand.class, c);
        assertThrows(InvalidParamException.class, () -> p.parse("list 1"));
    }

    @Test
    public void markTest() {
        Parser p = new Parser();
        Command c = p.parse("mark 1");
        assertInstanceOf(MarkCommand.class, c);
        assertThrows(InvalidParamException.class, () -> p.parse("mark"));
        assertThrows(InvalidParamException.class, () -> p.parse("mark a"));
    }

    @Test
    public void unmarkTest() {
        Parser p = new Parser();
        Command c = p.parse("unmark 1");
        assertInstanceOf(UnmarkCommand.class, c);
        assertThrows(InvalidParamException.class, () -> p.parse("unmark"));
        assertThrows(InvalidParamException.class, () -> p.parse("unmark a"));
    }

    @Test
    public void todoTest() {
        Parser p = new Parser();
        Command c = p.parse("todo abc");
        assertInstanceOf(TodoCommand.class, c);
        assertThrows(InvalidParamException.class, () -> p.parse("todo"));
    }

    @Test
    public void eventTest() {
        Parser p = new Parser();
        Command c = p.parse("event abc /from 2000-01-01 /to 2000-01-01");
        assertInstanceOf(EventCommand.class, c);
        assertThrows(InvalidParamException.class, () -> p.parse("event"));
        assertThrows(InvalidParamException.class, () -> p.parse("event abc"));
    }

    @Test
    public void deadlineTest() {
        Parser p = new Parser();
        Command c = p.parse("deadline abc /by 2000-01-01");
        assertInstanceOf(DeadlineCommand.class, c);
        assertThrows(InvalidParamException.class, () -> p.parse("deadline"));
        assertThrows(InvalidParamException.class, () -> p.parse("deadline abc"));
    }

    @Test
    public void deleteTest() {
        Parser p = new Parser();
        Command c = p.parse("delete 1");
        assertInstanceOf(DeleteCommand.class, c);
        assertThrows(InvalidParamException.class, () -> p.parse("delete"));
        assertThrows(InvalidParamException.class, () -> p.parse("delete a"));
    }

    @Test
    public void saveTest() {
        Parser p = new Parser();
        Command c = p.parse("save");
        assertInstanceOf(SaveCommand.class, c);
        assertThrows(InvalidParamException.class, () -> p.parse("save 1"));
    }

    @Test
    public void loadTest() {
        Parser p = new Parser();
        Command c = p.parse("load");
        assertInstanceOf(LoadCommand.class, c);
        assertThrows(InvalidParamException.class, () -> p.parse("load 1"));
    }
}
