package nikingoda.Command;

import nikingoda.NikingodaException.NikingodaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {

    @Test
    public void testFindCommandBye() throws NikingodaException {
        Command cmd = Command.findCommand("bye");
        assertInstanceOf(ExitCommand.class, cmd, "Expected an instance of ExitCommand");
    }

    @Test
    public void testFindCommandList() throws NikingodaException {
        Command cmd = Command.findCommand("list");
        assertInstanceOf(ListCommand.class, cmd, "Expected an instance of ListCommand");
    }

    @Test
    public void testFindCommandMarkValid() throws NikingodaException {
        Command cmd = Command.findCommand("mark 2");
        assertInstanceOf(MarkCommand.class, cmd, "Expected an instance of MarkCommand");
    }

    @Test
    public void testFindCommandMarkInvalid() {
        Exception exception = assertThrows(NikingodaException.class, () -> {
            Command.findCommand("mark a");
        });
        assertEquals(" Id must be in form of number", exception.getMessage());
    }

    @Test
    public void testFindCommandUnmarkValid() throws NikingodaException {
        Command cmd = Command.findCommand("unmark 3");
        assertInstanceOf(UnmarkCommand.class, cmd, "Expected an instance of UnmarkCommand");
    }

    @Test
    public void testFindCommandUnmarkInvalid() {
        Exception exception = assertThrows(NikingodaException.class, () -> {
            Command.findCommand("unmark xyz");
        });
        assertEquals(" Id must be in form of number", exception.getMessage());
    }

    @Test
    public void testFindCommandTodo() throws NikingodaException {
        Command cmd = Command.findCommand("todo read book");
        assertInstanceOf(AddTodoCommand.class, cmd, "Expected an instance of addTodoCommand");
    }

    @Test
    public void testFindCommandEvent() throws NikingodaException {
        Command cmd = Command.findCommand("event team meeting /at conference room");
        assertInstanceOf(AddEventCommand.class, cmd, "Expected an instance of addEventCommand");
    }

    @Test
    public void testFindCommandDeadline() throws NikingodaException {
        Command cmd = Command.findCommand("deadline submit assignment /by 2359 30/09/2023");
        assertInstanceOf(AddDeadlineCommand.class, cmd, "Expected an instance of addDeadlineCommand");
    }

    @Test
    public void testFindCommandDeleteValid() throws NikingodaException {
        Command cmd = Command.findCommand("delete 1");
        assertInstanceOf(DeleteCommand.class, cmd, "Expected an instance of DeleteCommand");
    }

    @Test
    public void testFindCommandDeleteInvalid() {
        Exception exception = assertThrows(NikingodaException.class, () -> {
            Command.findCommand("delete one");
        });
        assertEquals("Task id must be integer", exception.getMessage());
    }

    @Test
    public void testFindCommandFindValid() throws NikingodaException {
        Command cmd = Command.findCommand("find book");
        assertInstanceOf(FindCommand.class, cmd, "Expected an instance of FindCommand");
    }

    @Test
    public void testFindCommandFindMissingKeyword() {
        Exception exception = assertThrows(NikingodaException.class, () -> {
            Command.findCommand("find");
        });
        assertEquals("Please add keyword", exception.getMessage());
    }

    @Test
    public void testFindCommandUpdateDescriptionValid() throws NikingodaException {
        Command cmd = Command.findCommand("update 1 /description new description");
        assertInstanceOf(UpdateDescriptionCommand.class, cmd, "Expected an instance of UpdateDescriptionCommand");
    }

    @Test
    public void testFindCommandUpdateDeadlineValid() throws NikingodaException {
        Command cmd = Command.findCommand("update 2 /by 2359 30/09/2023");
        assertInstanceOf(UpdateDeadlineCommand.class, cmd, "Expected an instance of UpdateDeadlineCommand");
    }

    @Test
    public void testFindCommandUpdateBeginValid() throws NikingodaException {
        Command cmd = Command.findCommand("update 3 /begin 1200 15/10/2023");
        assertInstanceOf(UpdateBeginCommand.class, cmd, "Expected an instance of UpdateBeginCommand");
    }

    @Test
    public void testFindCommandUpdateEndValid() throws NikingodaException {
        Command cmd = Command.findCommand("update 4 /end 1400 15/10/2023");
        assertInstanceOf(UpdateEndCommand.class, cmd, "Expected an instance of UpdateEndCommand");
    }

    @Test
    public void testFindCommandUnknown() {
        Exception exception = assertThrows(NikingodaException.class, () -> {
            Command.findCommand("goodbye");
        });
        assertEquals("I don't understand you", exception.getMessage());
    }
}
