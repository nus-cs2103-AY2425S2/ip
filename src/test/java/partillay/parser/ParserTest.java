package partillay.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import partillay.command.AddCommand;
import partillay.command.ByeCommand;
import partillay.command.MarkCommand;
import partillay.exception.PartillayIncompleteDescriptionException;
import partillay.exception.PartillayInvalidCommandException;
import partillay.task.ToDo;

public class ParserTest {

    @Test
    public void testParsingBye() {
        assertEquals(new ByeCommand(), Parser.parse("bye"));
        assertEquals(new ByeCommand(), Parser.parse("bye "));
        assertEquals(new ByeCommand(), Parser.parse("bye  "));
    }

    @Test
    public void testParsingMarkCommand() {
        try {
            assertEquals(new MarkCommand(1), Parser.parse("mark 1"));
            assertEquals(new MarkCommand(2), Parser.parse("mark dsd"));
            fail();
        } catch (PartillayInvalidCommandException e) {
            assertEquals("That's not a valid command, bestie!", e.getMessage());
        }
    }

    @Test
    public void testParsingTodoCommandOne() {
        try {
            assertEquals(new AddCommand(new ToDo("eat breakfast")),
                    Parser.parse("todo eat breakfast"));
            assertEquals(new AddCommand(new ToDo("eat breakfast")),
                    Parser.parse("todo"));
            fail();
        } catch (PartillayIncompleteDescriptionException e) {
            assertEquals("Bestie, your task cannot be empty!", e.getMessage());
        }
    }

    @Test
    public void testParsingTodoCommandTwo() {
        try {
            assertEquals(new AddCommand(new ToDo("eat breakfast")),
                    Parser.parse("todo eat breakfast"));
            assertEquals(new AddCommand(new ToDo("eat breakfast")),
                    Parser.parse("todo     "));
            fail();
        } catch (PartillayIncompleteDescriptionException e) {
            assertEquals("Bestie, your task cannot be empty!", e.getMessage());
        }
    }

    @Test
    public void testParsingTodoCommandThree() {
        try {
            assertEquals(new AddCommand(new ToDo("eat breakfast")),
                    Parser.parse("todo eat breakfast"));
            assertEquals(new AddCommand(new ToDo("eat breakfast")),
                    Parser.parse("todo2"));
            fail();
        } catch (PartillayInvalidCommandException e) {
            assertEquals("That's not a valid command, bestie!", e.getMessage());
        }
    }
}
