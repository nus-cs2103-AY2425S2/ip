package ferb.parser;

import ferb.command.*;
import ferb.exception.FerbException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import ferb.tasklist.TaskList;
import ferb.task.*;

public class ParserTest {

    @Test
    public void parse_validAddCommands_success() throws FerbException {
        TaskList test = new TaskList();
        Parser p = new Parser(test, null);
        assertTrue(p.parse("todo run") instanceof AddCommand);
        assertTrue(p.parse("deadline study /by 2019-02-19") instanceof AddCommand);
        assertTrue(p.parse("event meeting /from 2025-02-08 /to 2025-02-09") instanceof AddCommand);
    }

    @Test
    public void parse_validMarkCommand_success() throws FerbException {
        TaskList test = new TaskList();
        test.add(new ToDo("run"));
        Parser p = new Parser(test, null);
        assertTrue(p.parse("mark 1") instanceof MarkDoneCommand);
    }

    @Test
    public void parse_validUnmarkCommand_success() throws FerbException {
        TaskList test = new TaskList();
        test.add(new ToDo("run"));
        Parser p = new Parser(test, null);
        assertTrue(p.parse("unmark 1") instanceof UnmarkDoneCommand);
    }

    @Test
    public void parse_validDeleteCommand_success() throws FerbException {
        TaskList test = new TaskList();
        test.add(new ToDo("run"));
        Parser p = new Parser(test, null);
        assertTrue(p.parse("delete 1") instanceof DeleteCommand);
    }

    @Test
    public void parse_invalidCommand_throwsFerbException() {
        TaskList test = new TaskList();
        Parser p = new Parser(test, null);
        try {
            p.parse("invalid command");
            fail("Expected a FerbException to be thrown");
        } catch (FerbException e) {
            // Test passes
        }
    }

    @Test
    public void parse_validSortByDateCommand_success() throws FerbException {
        TaskList test = new TaskList();
        Parser p = new Parser(test, null);
        assertTrue(p.parse("sort date") instanceof SortCommand);
    }

    @Test
    public void parse_validSortByDescriptionCommand_success() throws FerbException {
        TaskList test = new TaskList();
        Parser p = new Parser(test, null);
        assertTrue(p.parse("sort description") instanceof SortCommand);
    }

    @Test
    public void parse_sortCommandWithInvalidType_throwsFerbException() {
        TaskList test = new TaskList();
        Parser p = new Parser(test, null);
        try {
            p.parse("sort invalidType");
            fail("Expected a FerbException to be thrown");
        } catch (FerbException e) {
            // Test passes
        }
    }

    @Test
    public void parse_sortCommandWithNoType_throwsFerbException() {
        TaskList test = new TaskList();
        Parser p = new Parser(test, null);
        try {
            p.parse("sort");
            fail("Expected a FerbException to be thrown");
        } catch (FerbException e) {
            // Test passes
        }
    }
}