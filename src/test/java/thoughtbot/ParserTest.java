package thoughtbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import exceptions.DateTimeFormatException;
import exceptions.EmptyDescException;
import exceptions.ThoughtBotException;
import exceptions.UnrecognisedKeywordException;
import usercommands.UserCommandDeadline;
import usercommands.UserCommandList;
import usercommands.UserCommandTodo;
import utilities.Command;

public class ParserTest {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void parseInput_listInput_success() {
        Command expectedCommand = Command.LIST;

        try {
            UserCommandList uc = (UserCommandList) Parser.parseInput("list");
            assertEquals(expectedCommand, uc.getCommandType());
        } catch (ThoughtBotException e) {
            System.out.println(e);
        }
    }

    @Test
    public void parseInput_correctToDoInput_success() {
        Command expectedCommand = Command.TODO;
        String expectedTaskName = "eat food";

        try {
            UserCommandTodo uc = (UserCommandTodo) Parser.parseInput("todo eat food");
            assertEquals(expectedCommand, uc.getCommandType());
            assertEquals(expectedTaskName, uc.getTaskName());
        } catch (ThoughtBotException e) {
            System.out.println(e);
        }
    }

    @Test
    public void parseInput_emptyDescToDoInput_exceptionThrown() {
        boolean hasBeenThrown = false;

        try {
            Parser.parseInput("todo");
        } catch (ThoughtBotException e) {
            if (e.getClass() == EmptyDescException.class) {
                hasBeenThrown = true;
            }
        }

        assertTrue(hasBeenThrown);
    }

    @Test
    public void parseInput_correctDeadlineInput_success() {
        Command expectedCommand = Command.DEADLINE;
        String expectedTaskName = "do homework";
        LocalDateTime expectedDeadline = LocalDateTime.parse("2000-01-01 12:00", formatter);

        try {
            UserCommandDeadline uc =
                    (UserCommandDeadline) Parser.parseInput("deadline do homework /by 2000-01-01 12:00");
            assertEquals(expectedCommand, uc.getCommandType());
            assertEquals(expectedTaskName, uc.getTaskName());
            assertEquals(expectedDeadline, uc.getDeadline());
        } catch (ThoughtBotException e) {
            System.out.println(e);
        }
    }

    @Test
    public void parseInput_emptyDescDeadlineInput_exceptionThrown() {
        boolean hasBeenThrown = false;

        try {
            Parser.parseInput("deadline /by 2000-01-01 12:00");
        } catch (ThoughtBotException e) {
            if (e.getClass() == EmptyDescException.class) {
                hasBeenThrown = true;
            }
        }

        assertTrue(hasBeenThrown);
    }

    @Test
    public void parseInput_missingIncompleteKeywordDeadlineInput_exceptionThrown() {
        boolean hasBeenThrown1 = false;
        boolean hasBeenThrown2 = false;

        try {
            Parser.parseInput("deadline do homework 2000-01-01 12:00");
        } catch (ThoughtBotException e) {
            if (e.getClass() == UnrecognisedKeywordException.class) {
                hasBeenThrown1 = true;
            }
        }

        try {
            Parser.parseInput("deadline do homework by 2000-01-01 12:00");
        } catch (ThoughtBotException e) {
            if (e.getClass() == UnrecognisedKeywordException.class) {
                hasBeenThrown2 = true;
            }
        }

        assertTrue(hasBeenThrown1);
        assertTrue(hasBeenThrown2);
    }

    @Test
    public void parseInput_wrongFormatDateTimeDeadlineInput_exceptionThrown() {
        boolean hasBeenThrown1 = false;
        boolean hasBeenThrown2 = false;

        try {
            Parser.parseInput("deadline do homework /by 2000 01 01 12 00");
        } catch (ThoughtBotException e) {
            if (e.getClass() == DateTimeFormatException.class) {
                hasBeenThrown1 = true;
            }
        }

        try {
            Parser.parseInput("deadline do homework /by 01-01-2000 12:00PM");
        } catch (ThoughtBotException e) {
            if (e.getClass() == DateTimeFormatException.class) {
                hasBeenThrown2 = true;
            }
        }

        assertTrue(hasBeenThrown1);
        assertTrue(hasBeenThrown2);
    }
}
