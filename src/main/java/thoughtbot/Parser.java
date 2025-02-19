package thoughtbot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.DateTimeFormatException;
import exceptions.EmptyDescException;
import exceptions.ThoughtBotException;
import exceptions.UnrecognisedCmdException;
import exceptions.UnrecognisedKeywordException;
import usercommands.UserCommand;
import usercommands.UserCommandDeadline;
import usercommands.UserCommandDelete;
import usercommands.UserCommandEvent;
import usercommands.UserCommandFind;
import usercommands.UserCommandList;
import usercommands.UserCommandMarkUnmark;
import usercommands.UserCommandRemind;
import usercommands.UserCommandTodo;
import utilities.Command;
import utilities.CommandFormats;

/**
 * This is an uninitializable class with one method, that is used to parse the user input
 * and return a UserCommand object that contains the details of what the user is trying
 * to do
 */
public class Parser {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private Parser() {
        // to prevent instantiation
    }

    /**
     * Returns a UserCommand object with the details of the input after parsing them
     *
     * @param userInput String object of what the user input to the chatbot
     * @return UserCommand object that contains details of the user input
     * @throws ThoughtBotException If there is something wrong with the formatting of user input
     */
    public static UserCommand parseInput(String userInput) throws ThoughtBotException {
        userInput = userInput.toLowerCase();
        String firstWord = userInput.split(" ")[0];

        switch (firstWord) {
        case "list":
            return getUserCommandList();
        case "todo":
            return getUserCommandTodo(userInput);
        case "deadline":
            return getUserCommandDeadline(userInput);
        case "event":
            return getUserCommandEvent(userInput);
        case "mark":
            return getUserCommandMarkUnmark(userInput);
        case "unmark":
            return getCommandMarkUnmark(userInput);
        case "delete":
            return getUserCommandDelete(userInput);
        case "find":
            return getUserCommandFind(userInput);
        case "remind":
            return getUserCommandRemind();
        default:
            throw new UnrecognisedCmdException();
        }
    }

    /**
     * Returns a UserCommandList object, capturing the Command
     *
     * @return UserCommandList object encapsulating relevant information
     */
    private static UserCommandList getUserCommandList() {
        return new UserCommandList();
    }

    /**
     * Returns a UserCommandTodo object, capturing the Command and description of the task
     *
     * @param userInput The user's String input
     * @return UserCommandTodo object encapsulating relevant information
     * @throws EmptyDescException If the description for the task is not provided
     */
    private static UserCommandTodo getUserCommandTodo(String userInput) throws EmptyDescException {
        try {
            String todoTaskName = userInput.split(" ", 2)[1];
            return new UserCommandTodo(todoTaskName);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDescException(CommandFormats.TODO);
        }
    }

    /**
     * Returns a UserCommandDeadline object, capturing the Command, description of the task, and the deadline
     *
     * @param userInput The user's String input
     * @return UserCommandDeadline object encapsulating relevant information
     * @throws UnrecognisedKeywordException If the "/by" keyword was not typed correctly
     * @throws EmptyDescException If the description for the task is not provided
     * @throws DateTimeFormatException If the format for the deadline is not correct
     */
    private static UserCommandDeadline getUserCommandDeadline(String userInput)
            throws UnrecognisedKeywordException, EmptyDescException, DateTimeFormatException {
        if (!userInput.contains(" /by ")) {
            throw new UnrecognisedKeywordException(CommandFormats.DEADLINE);
        }

        String[] splitSlashBy = userInput.split(" /by ");
        String [] splitDeadline = splitSlashBy[0].split(" ", 2);
        if (splitSlashBy.length != 2 || splitDeadline.length != 2) {
            throw new EmptyDescException(CommandFormats.DEADLINE);
        }

        String deadlineTaskName = splitDeadline[1];
        String deadlineString = splitSlashBy[1];

        LocalDateTime deadlineDateTime;
        try {
            deadlineDateTime = LocalDateTime.parse(deadlineString, formatter);
        } catch (DateTimeParseException e) {
            throw new DateTimeFormatException();
        }

        return new UserCommandDeadline(deadlineTaskName, deadlineDateTime);
    }

    /**
     * Returns a UserCommandEvent object, capturing the Command, description of the task, and the to and from times
     * of the task
     *
     * @param userInput The user's String input
     * @return UserCommandEvent object encapsulating relevant information
     * @throws UnrecognisedKeywordException If the "/to" and/or "/from" keyword was not typed correctly
     * @throws EmptyDescException If the description for the task is not provided
     * @throws DateTimeFormatException If the format for the to or from date is not correct
     */
    private static UserCommandEvent getUserCommandEvent(String userInput)
            throws UnrecognisedKeywordException, EmptyDescException, DateTimeFormatException {
        if (!userInput.contains(" /from ") || !userInput.contains(" /to ")) {
            throw new UnrecognisedKeywordException(CommandFormats.EVENT);
        }

        String[] splitSlashFrom = userInput.split(" /from ");
        String[] splitSlashTo = splitSlashFrom[1].split(" /to ");
        if (splitSlashFrom.length != 2 || splitSlashTo.length != 2) {
            throw new EmptyDescException(CommandFormats.EVENT);
        }

        String eventTaskName = splitSlashFrom[0].split(" ", 2)[1];
        String eventFrom = splitSlashTo[0];
        String eventTo = splitSlashTo[1];

        LocalDateTime fromDateTime;
        LocalDateTime toDateTime;
        try {
            fromDateTime = LocalDateTime.parse(eventFrom, formatter);
            toDateTime = LocalDateTime.parse(eventTo, formatter);
        } catch (DateTimeParseException e) {
            throw new DateTimeFormatException();
        }

        return new UserCommandEvent(eventTaskName, fromDateTime, toDateTime);
    }

    /**
     * Returns a UserCommandMarkUnmark object, capturing the Command of Mark, and the number of the task to mark
     *
     * @param userInput The user's String input
     * @return UserCommandMarkUnmark object encapsulating relevant information
     * @throws EmptyDescException If no number was provided with the command
     */
    private static UserCommandMarkUnmark getUserCommandMarkUnmark(String userInput) throws EmptyDescException {
        try {
            int markNumber = Integer.parseInt(userInput.split(" ", 2)[1]);
            return new UserCommandMarkUnmark(Command.MARK, markNumber);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDescException(CommandFormats.MARK);
        }
    }

    /**
     * Returns a UserCommandMarkUnmark object, capturing the Command of Unmark, and the number of the task to unmark
     *
     * @param userInput The user's String input
     * @return UserCommandMarkUnmark object encapsulating relevant information
     * @throws EmptyDescException If no number was provided with the command
     */
    private static UserCommandMarkUnmark getCommandMarkUnmark(String userInput) throws EmptyDescException {
        try {
            int unmarkNumber = Integer.parseInt(userInput.split(" ", 2)[1]);
            return new UserCommandMarkUnmark(Command.UNMARK, unmarkNumber);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDescException(CommandFormats.UNMARK);
        }
    }

    /**
     * Returns a UserCommandDelete object, capturing the Command, and the number of the task to delete
     *
     * @param userInput The user's String input
     * @return UserCommandDelete object encapsulating relevant information
     * @throws EmptyDescException If no number was provided with the command
     */
    private static UserCommandDelete getUserCommandDelete(String userInput) throws EmptyDescException {
        try {
            int deleteNumber = Integer.parseInt(userInput.split(" ", 2)[1]);
            return new UserCommandDelete(deleteNumber);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDescException(CommandFormats.DELETE);
        }
    }

    /**
     * Returns a UserCommandFind object, capturing the Command, and the string of what the user is finding
     *
     * @param userInput The user's String input
     * @return UserCommandFind object encapsulating relevant information
     * @throws EmptyDescException If no string was provided for matching
     */
    private static UserCommandFind getUserCommandFind(String userInput) throws EmptyDescException {
        try {
            String findString = userInput.split(" ", 2)[1];
            return new UserCommandFind(findString);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDescException(CommandFormats.FIND);
        }
    }

    /**
     * Returns a UserCommandRemind object, capturing the Command
     *
     * @return UserCommandRemind object encapsulating relevant information
     */
    private static UserCommandRemind getUserCommandRemind() {
        return new UserCommandRemind();
    }
}
