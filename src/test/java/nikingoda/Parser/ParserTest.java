package nikingoda.Parser;

import nikingoda.Command.Command;
import nikingoda.NikingodaException.NikingodaException;
import nikingoda.Task.Deadline;
import nikingoda.Task.Event;
import nikingoda.Task.Task;
import nikingoda.Task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void testParseTask_Todo_NotDone() {
        String line = "T|0|Read book";
        Task task = Parser.parseTask(line);
        assertInstanceOf(Todo.class, task, "Expected instance of Todo");
        assertEquals("[T][ ] Read book", task.toString());
    }

    @Test
    public void testParseTask_Todo_Done() {
        String line = "T|1|Read book";
        Task task = Parser.parseTask(line);
        assertInstanceOf(Todo.class, task, "Expected instance of Todo");
        assertEquals("[T][X] Read book", task.toString());
    }

    @Test
    public void testParseTask_Deadline_NotDone() {
        String line = "D|0|Submit assignment|2359 30/09/2023";
        Task task = Parser.parseTask(line);
        assertInstanceOf(Deadline.class, task, "Expected instance of Deadline");
        // Assuming the Deadline class formats "2359 30/09/2023" to "11:59 PM, Sep 30 2023"
        assertEquals("[D][ ] Submit assignment (by: 11:59 PM, Sep 30 2023)", task.toString());
    }

    @Test
    public void testParseTask_Event_Done() {
        String line = "E|1|Meeting|1200 15/10/2023|1400 15/10/2023";
        Task task = Parser.parseTask(line);
        assertInstanceOf(Event.class, task, "Expected instance of Event");
        // Assuming the Event class formats the times as follows:
        // Begin time: "1200 15/10/2023" -> "12:00 PM, Oct 15 2023"
        // End time: "1400 15/10/2023" -> "2:00 PM, Oct 15 2023"
        assertEquals("[E][X] Meeting (from: 12:00 PM, Oct 15 2023 to: 2:00 PM, Oct 15 2023)", task.toString());
    }

    @Test
    public void testParse_ValidCommand() {
        // This test assumes that "list" is a valid command that can be found by Command.findCommand().
        try {
            Command command = Parser.parse("list");
            assertNotNull(command, "Expected a non-null Command for a valid command string");
        } catch (NikingodaException e) {
            fail("Unexpected nikingodaException for a valid command: " + e.getMessage());
        }
    }

    @Test
    public void testParse_InvalidCommand() {
        // This test assumes that "abc" is not a valid command and should throw a nikingodaException.
        assertThrows(NikingodaException.class, () -> Parser.parse("abc"));
    }
}
