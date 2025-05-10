package parakeet;
import org.junit.jupiter.api.Test;
import parakeet.command.*;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private Parser parser = new Parser();

    @Test
    void parse_invalidCommand_throwException() {
        try{
            Command command = parser.parse("tod");
            fail();
        } catch (InvalidInputError e) {
            assertEquals("Sorry, I don't understand what you mean", e.getMessage());
        }
    }

    @Test
    void parse_byeCommand_returnExitCommand() {
        try {
            Command command = parser.parse("bye");
            assertTrue(command instanceof ExitCommand);
        } catch (InvalidInputError e) {
            fail("Exception should not be thrown for valid exit command");
        }
    }

    @Test
    void parser_listCommand_returnListCommand() {
        try{
            Command command = parser.parse("list");
            assertTrue(command instanceof ListCommand);
        } catch (InvalidInputError e) {
            fail("Exception should not be thrown with valid input");
        }
    }

    @Test
    void parser_markCommand_returnMarkCommand() {
        try {
            Command command = parser.parse("mark 1");
            assertTrue(command instanceof MarkCommand);
        } catch (InvalidInputError e) {
            fail("Exception should not be thrown for valid command");
        }
    }

    @Test
    void parser_unMarkCommand_returnUnmarkCommand() {
        try {
            Command command = parser.parse("unmark 1");
            assertTrue(command instanceof UnmarkCommand);
        } catch (InvalidInputError e) {
            fail("Exception should not be thrown for valid command");
        }
    }

    @Test
    void parser_DeleteCommand_returnDeleteCommand() {
        try {
            Command command = parser.parse("delete 1");
            assertTrue(command instanceof DeleteCommand);
        } catch (InvalidInputError e) {
            fail("Exception should not be thrown for valid command");
        }
    }

    @Test
    void parse_validTodoCommand_returnTodoCommand() {
        try {
            Command command = parser.parse("todo finish individual project");
            assertTrue(command instanceof TodoCommand);
        } catch (InvalidInputError e) {
            fail("Exception is thrown when valid todo command is provided");
        }
    }
    @Test
    void parser_invalidTodoCommand_throwException() {
        try {
            Command command = parser.parse("todo  ");
            Command commandTwo = parser.parse("todo");
            fail("When given invalid todo command, no exception is thrown.");
        } catch (InvalidInputError e ){
            assertEquals("Sorry, the description can not be empty", e.getMessage());
        }
    }

    @Test
    void parser_validDeadlineCommand_returnDeadlineCommand() {
        try {
            Command command = parser.parse("deadline meeting /by 2025-02-01 12:30");
            assertTrue(command instanceof DeadlineCommand);
        } catch (InvalidInputError e) {
            fail();
        }
    }

    @Test
    void parserDeadline_emptyDate_throwException() {
        try {
            Command command = parser.parse("deadline meeting /by ");
            fail();
        } catch (InvalidInputError e) {
            assertEquals("Sorry, this is invalid input, you need to provide description and deadline"
                    , e.getMessage());
        }
    }

    @Test
    void parserDeadline_emptyDescription_throwException() {
        try {
            Command command = parser.parse("deadline /by 2025-02-01 12:30 ");
            fail();
        } catch (InvalidInputError e) {
            assertEquals("Sorry, the description can not be empty"
                    , e.getMessage());
        }
    }

    @Test
    void parserDeadline_invalidDate_throwException() {
        try {
            Command command = parser.parse("deadline individual project /by 2025/02/01 12:30 ");
            fail();
        } catch (InvalidInputError e) {
            assertEquals("Error: Invalid date format or invalid date. valid: yyyy-MM-dd HH:mm."
                    , e.getMessage());
        }
    }

    @Test
    void parserDeadline_invalidDateTwo_throwException() {
        try {
            Command command = parser.parse("deadline individual project /by 2025/02/01 12 ");
            fail();
        } catch (InvalidInputError e) {
            assertEquals("Error: Invalid date format or invalid date. valid: yyyy-MM-dd HH:mm."
                    , e.getMessage());
        }
    }

    @Test
    void parserEvent_validInput_returnEvent() {
        try {
            Command command = parser.parse("event meeting /from 2025-03-12 15:30 /to 2025-03-12 16:35");
            assertTrue(command instanceof EventCommand);
        } catch (InvalidInputError e) {
            fail();
        }
    }
    @Test
    void parserEvent_emptyTask_throwException() {
        try {
            Command command = parser.parse("event  ");
            fail();
        } catch (InvalidInputError e) {
            assertEquals("Sorry, the description can not be empty"
                    , e.getMessage());
        }
    }

    @Test
    void parserEvent_emptyDate_throwException() {
        try {
            Command command = parser.parse("event meeting /from 2025-01-05 12:25 /to  ");
            fail();
        } catch (InvalidInputError e) {
            assertEquals("Sorry, this is invalid input, you need to provide description and exact time"
                    , e.getMessage());
        }
    }

    @Test
    void parserEvent_emptyDescription_throwException() {
        try {
            Command command = parser.parse("event /from 2025-02-01 12:30 /to 2025-05-01 16:35");
            fail();
        } catch (InvalidInputError e) {
            assertEquals("Sorry, the description can not be empty"
                    , e.getMessage());
        }
    }

    @Test
    void parserEvent_invalidDate_throwException() {
        try {
            Command command = parser.parse("event meeting /from 2025/02/01 12:30 /to 2025-02-01 13");
            fail();
        } catch (InvalidInputError e) {
            assertEquals("Error: Invalid date format or invalid date. valid: yyyy-MM-dd HH:mm."
                    , e.getMessage());
        }
    }

}
