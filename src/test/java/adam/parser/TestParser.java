package adam.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import adam.command.AddCommand;
import adam.command.ByeCommand;
import adam.command.DeleteCommand;
import adam.command.DoneCommand;
import adam.command.ListCommand;
import adam.command.ListOnCommand;
import adam.command.UnmarkCommand;
import adam.exceptions.AdamException;

/**
 * Test class for Parser.
 */
public class TestParser {
    /**
     * Tests the parseInputDate method.
     */
    @Test
    public void testParseInputDate() {
        try {
            assertEquals("2021-12-31", Parser.parseInputDate("31-12-2021").toString());
        } catch (AdamException e) {
            assertEquals(1, 0);
        }
    }

    /**
     * Tests the formatOutputDate method.
     */
    @Test
    public void testFormatOutputDate() {
        try {
            assertEquals("31 Dec 2021", Parser.formatOutputDate(Parser.parseInputDate("31-12-2021")));
        } catch (Exception e) {
            assertEquals(1, 0);
        }
    }

    /**
     * Tests the formatLogDate method.
     */
    @Test
    public void testFormatLogDate() {
        try {
            assertEquals("31-12-2021", Parser.formatLogDate(Parser.parseInputDate("31-12-2021")));
        } catch (Exception e) {
            assertEquals(1, 0);
        }
    }

    /**
     * Tests the parseInput method.
     */
    @Test
    public void testParseInput() {
        try {
            assertEquals(AddCommand.class, Parser.parseInput("todo test").getClass());
            assertEquals(AddCommand.class,
                    Parser.parseInput("deadline test /by 31-12-2021").getClass());
            assertEquals(AddCommand.class,
                    Parser.parseInput("event test /from 15-12-2021 /to 25-12-2021").getClass());
            assertEquals(ListCommand.class, Parser.parseInput("list").getClass());
            assertEquals(ListOnCommand.class, Parser.parseInput("listOn 31-12-2021").getClass());
            assertEquals(DoneCommand.class, Parser.parseInput("mark 1").getClass());
            assertEquals(UnmarkCommand.class, Parser.parseInput("unmark 1").getClass());
            assertEquals(DeleteCommand.class, Parser.parseInput("delete 1").getClass());
            assertEquals(ByeCommand.class, Parser.parseInput("bye").getClass());
        } catch (AdamException e) {
            assertEquals(1, 0);
        }

        try {
            Parser.parseInput("invalid command");
            assertEquals(1, 0);
        } catch (AdamException e) {
            assertEquals(1, 1);
        }
    }
}
