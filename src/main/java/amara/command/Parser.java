package amara.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import amara.exceptions.AmaraException;
import amara.task.Deadline;
import amara.task.Event;
import amara.task.ToDo;

/**
 * Parses the given input command string and returns the corresponding
 * {@link Command} object to be executed.
 */
public class Parser {
    /**
     * The method extracts the first word of the input string to determine the command type
     * and parses the remaining string for command-specific parameters. The recognized
     * commands are defined in {@link CommandEnum}.
     *
     * @param fullCommand The full input string representing the user's command.
     * @return A {@link Command} object that corresponds to the parsed command.
     * @throws AmaraException If the command is invalid or if an error occurs while parsing.
     */
    public static Command parseCommand(String fullCommand) throws AmaraException {
        String commandString = Parser.getFirstWord(fullCommand);
        String commandParams = Parser.removeFirstWord(fullCommand);
        CommandEnum commandEnum = CommandEnum.fromString(commandString);
        switch (commandEnum) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkCommand(Parser.getIndex(commandParams));
        case UNMARK:
            return new UnmarkCommand(Parser.getIndex(commandParams));
        case DELETE:
            return new DeleteCommand(Parser.getIndex(commandParams));
        case TODO:
            return new AddToDoCommand(Parser.getToDo(commandParams));
        case DEADLINE:
            return new AddDeadlineCommand(Parser.getDeadline(commandParams));
        case EVENT:
            return new AddEventCommand(Parser.getEvent(commandParams));
        case FIND:
            return new FindCommand(Parser.getStringQuery(commandParams));
        case HELP:
            return new HelpCommand();
        case SORT:
            return new SortCommand();
        default:
            throw AmaraException.invalidCommand();
        }
    }

    private static String getFirstWord(String fullCommand) {
        return fullCommand.trim().split("\\s+")[0];
    }

    private static String removeFirstWord(String fullCommand) {
        return fullCommand.replace(getFirstWord(fullCommand), "").trim();
    }

    private static int getIndex(String commandParams) throws AmaraException {
        try {
            return Integer.parseInt(commandParams);
        } catch (NumberFormatException e) {
            throw AmaraException.numberFormatting();
        }
    }

    private static String getStringQuery(String commandParams) throws AmaraException {
        if (commandParams.isBlank()) {
            throw new AmaraException("OOPS!!! String query cannot be empty :(");
        }
        return commandParams;
    }

    private static ToDo getToDo(String commandParams) throws AmaraException {
        if (commandParams.isBlank()) {
            throw AmaraException.invalidToDoParameter();
        }
        return new ToDo(commandParams);
    }

    private static Deadline getDeadline(String commandParams) throws AmaraException {
        // splits parameters into description and deadline tokens
        String[] tokens = commandParams.split("/by");
        String description = tokens[0].strip();
        try {
            if (tokens.length != 2 || description.isBlank()) {
                throw AmaraException.invalidDeadlineParameter();
            }
            String dateTimeToken = tokens[1].strip();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeToken, formatter);
            assert description.isBlank() : "Deadline description is blank";
            return new Deadline(description, dateTime);
        } catch (DateTimeParseException e) {
            throw AmaraException.dateTimeFormatException();
        }
    }

    private static Event getEvent(String commandParams) throws AmaraException {
        // splits parameters into description and duration tokens
        String[] tokens = commandParams.split("/from");
        String description = tokens[0].strip();
        try {
            if (tokens.length != 2 || description.isBlank()) {
                throw AmaraException.invalidEventParameter();
            }
            // split duration tokens into fromDateTime and toDateTime strings
            String[] duration = tokens[1].split("/to");
            if (duration.length != 2) {
                throw AmaraException.invalidEventParameter();
            }

            String fromDateTimeToken = duration[0].strip();
            String toDateTimeToken = duration[1].strip();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime fromDateTime = LocalDateTime.parse(fromDateTimeToken, formatter);
            LocalDateTime toDateTime = LocalDateTime.parse(toDateTimeToken, formatter);
            assert description.isBlank() : "Event description is blank";
            return new Event(description, fromDateTime, toDateTime);
        } catch (DateTimeParseException e) {
            throw AmaraException.dateTimeFormatException();
        }
    }
}
