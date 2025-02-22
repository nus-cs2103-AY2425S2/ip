package zea;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import zea.command.Command;
import zea.command.DeadlineCommand;
import zea.command.EventCommand;
import zea.command.ExitCommand;
import zea.command.ListCommand;
import zea.command.MarkCommand;
import zea.command.TodoCommand;
import zea.command.UnmarkCommand;
import zea.task.Task;

public class ParserTest {
    @Test
    public void byeTest() {
        try {
            Command c = Parser.parse("bye");
            assertEquals(new ExitCommand(), c);
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    public void listTest() {
        try {
            Command c = Parser.parse("list");
            assertEquals(new ListCommand(), c);
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    public void markTest() {
        try {
            Command c = Parser.parse("mark 1");
            assertEquals(new MarkCommand(0), c);
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    public void unmarkTest() {
        try {
            Command c = Parser.parse("unmark 2");
            assertEquals(new UnmarkCommand(1), c);
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    public void todoTest() {
        try {
            Command c = Parser.parse("todo buy chicken rice");
            assertEquals(new TodoCommand("buy chicken rice"), c);
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    public void deadlineTest() {
        try {
            Command c = Parser.parse("deadline sell house /by 01/02/2025 10:30");
            assertEquals(c, new DeadlineCommand("sell house", LocalDateTime.parse("01/02/2025 10:30", Task.FORMATTER)));
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    public void eventTest() {
        try {
            Command c = Parser.parse("event wedding dinner /from 01/02/2025 10:30 /to 05/02/2025 18:00");
            assertEquals(c, new EventCommand("wedding dinner",
                    LocalDateTime.parse("01/02/2025 10:30", Task.FORMATTER),
                    LocalDateTime.parse("05/02/2025 18:00", Task.FORMATTER)));
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    public void unknownCommandTest() {
        assertThrows(ParseException.class, () -> Parser.parse("gibberish"));
    }
}

