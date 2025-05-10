package gigi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import gigi.commands.ByeCommand;
import gigi.commands.Command;
import gigi.commands.DeadlineCommand;
import gigi.commands.DeleteCommand;
import gigi.commands.EventCommand;
import gigi.commands.FindCommand;
import gigi.commands.HelpCommand;
import gigi.commands.ListCommand;
import gigi.commands.MarkCommand;
import gigi.commands.ToDoCommand;
import gigi.commands.UnmarkCommand;
import gigi.exceptions.GigiException;

/**
 * Parses user input and converts it into corresponding command objects.
 * This class handles extracting command words and arguments,
 * as well as parsing date and time inputs.
 */
public class Parser {
    /**
     * Parses user input and returns the corresponding command object.
     *
     * @param input The raw user input.
     * @return The corresponding {@code Command} object.
     * @throws GigiException If the command is invalid.
     */
    public static Command parse(String input) throws GigiException {
        assert input != null && !input.isBlank() : "Command input should not be null or empty";
        input = input.toLowerCase();
        String[] parts = input.split("\\s+", 2);
        String commandWord = parts[0];
        String details = parts.length > 1 ? parts[1] : "";

        return switch (commandWord) {
        case ToDoCommand.COMMAND_WORD -> startToDo(details);
        case DeadlineCommand.COMMAND_WORD -> startDeadline(details);
        case EventCommand.COMMAND_WORD -> startEvent(details);
        case DeleteCommand.COMMAND_WORD -> startDelete(details);
        //case ClearCommand.COMMAND_WORD -> new ClearCommand();
        case ByeCommand.COMMAND_WORD -> new ByeCommand();
        case ListCommand.COMMAND_WORD -> new ListCommand();
        case FindCommand.COMMAND_WORD -> new FindCommand(details);
        case MarkCommand.COMMAND_WORD -> {
            try {
                yield new MarkCommand(Integer.parseInt(details));
            } catch (NumberFormatException e) {
                throw new GigiException("MEOW! Please provide a valid task number to mark.");
            }
        }
        case UnmarkCommand.COMMAND_WORD -> new UnmarkCommand(Integer.parseInt(details));
        case HelpCommand.COMMAND_WORD -> new HelpCommand();
        default -> throw new GigiException(
                "MEOW! Invalid command. What do you mean?\n"
                + "Type \"help\" for list of commands");
        };
    }

    private static Command startToDo(String details) throws GigiException {
        return new ToDoCommand(details);
    }

    private static Command startDeadline(String details) throws GigiException {
        if (!details.contains(" /by ")) {
            throw new GigiException("MEOW!!! The deadline must include a '/by' clause.");
        }
        String[] deadlineDetails = details.split(" /by ", 2);
        if (deadlineDetails.length < 2 || deadlineDetails[0].isBlank() || deadlineDetails[1].isBlank()) {
            throw new GigiException("MEOW!!! The description and date of a deadline cannot be empty.");
        }
        return new DeadlineCommand(deadlineDetails[0], parseDateTime(deadlineDetails[1]));
    }

    private static Command startEvent(String details) throws GigiException {
        String[] eventDetails = details.split(" /from | /to ", 3);
        if (eventDetails.length < 3) {
            throw new GigiException("MEOW! Events must have a description, '/from' date, and '/to' deadline.");
        }
        return new EventCommand(eventDetails[0], parseDateTime(eventDetails[1]), parseDateTime(eventDetails[2]));
    }

    @SuppressWarnings("checkstyle:Indentation")
    private static Command startDelete(String details) throws GigiException {
        try {
            return new DeleteCommand(Integer.parseInt(details));
        } catch (NumberFormatException e) {
            throw new GigiException("MEOW! Please provide a valid task number to delete.");
        }
    }

    /**
     * Parses a date-time string into a {@code LocalDateTime} object.
     *
     * @param dateTimeString The date-time string to be parsed.
     * @return A {@code LocalDateTime} object representing the parsed date and time.
     * @throws GigiException If the date format is incorrect.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) throws GigiException {
        dateTimeString = dateTimeString.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            throw new GigiException("MEOW! Invalid date format. "
                    + "Please use 'yyyy-MM-dd HH:mm' (e.g., 2024-02-12 14:30).");
        }
    }

}
