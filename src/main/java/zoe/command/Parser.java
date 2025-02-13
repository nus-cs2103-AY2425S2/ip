package zoe.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code Parser} class processes user inputs and converts them into executable {@code Command} objects.
 * It supports various command formats for a task management application.
 */
public class Parser {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses the full user input command and returns the corresponding {@code Command}.
     *
     * @param fullCommand The raw string input entered by the user.
     * @return A {@code Command} object representing the user's intent.
     */
    public static Command parse(String fullCommand) {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(fullCommand);
        if (!matcher.matches()) {
            return new IncorrectCommand();
        }

        String commandWord = matcher.group("commandWord");
        String arguments = matcher.group("arguments").trim();
        return switch (commandWord) {
        case TodoCommand.COMMAND_WORD, TodoCommand.ALIAS -> prepareTodo(arguments);
        case DeadlineCommand.COMMAND_WORD, DeadlineCommand.ALIAS -> prepareDeadline(arguments);
        case EventCommand.COMMAND_WORD, EventCommand.ALIAS -> prepareEvent(arguments);
        case ListCommand.COMMAND_WORD -> new ListCommand();
        case DeleteCommand.COMMAND_WORD -> prepareDelete(arguments);
        case MarkCommand.COMMAND_WORD -> prepareMark(arguments);
        case UnmarkCommand.COMMAND_WORD -> prepareUnmark(arguments);
        case ByeCommand.COMMAND_WORD -> new ByeCommand();
        case FindCommand.COMMAND_WORD -> prepareFind(arguments);
        default -> new IncorrectCommand();
        };
    }

    /**
     * Prepares a {@code TodoCommand} after validating the provided arguments.
     *
     * @param arguments The description of the todo task.
     * @return A {@code TodoCommand} object if arguments are valid, otherwise an {@code IncorrectCommand}.
     */
    private static Command prepareTodo(String arguments) {
        if (arguments.isEmpty()) {
            return new IncorrectCommand("OOPS!!! The description of a todo cannot be empty.");
        }
        return new TodoCommand(arguments);
    }

    /**
     * Prepares a {@code DeadlineCommand} after validating and parsing the provided arguments.
     *
     * @param arguments The description and deadline of the task, formatted as "task /by YYYY-MM-DD".
     * @return A {@code DeadlineCommand} object if arguments are valid, otherwise an {@code IncorrectCommand}.
     */
    private static Command prepareDeadline(String arguments) {
        final Pattern pattern = Pattern.compile("^(.+) /by (\\d{4}-\\d{2}-\\d{2})$");
        Matcher matcher = pattern.matcher(arguments);
        if (matcher.matches()) {
            String taskName = matcher.group(1);
            try {
                LocalDate by = LocalDate.parse(matcher.group(2));
                return new DeadlineCommand(taskName, by);
            } catch (DateTimeParseException e) {
                return new IncorrectCommand();
            }
        }
        return new IncorrectCommand();
    }

    /**
     * Prepares an {@code EventCommand} after validating and parsing the provided arguments.
     *
     * @param arguments The description, start date, and end date of the event.
     * @return An {@code EventCommand} object if arguments are valid, otherwise an {@code IncorrectCommand}.
     */
    private static Command prepareEvent(String arguments) {
        final Pattern pattern = Pattern.compile("^(.+) /from (\\d{4}-\\d{2}-\\d{2}) /to (\\d{4}-\\d{2}-\\d{2})$");
        Matcher matcher = pattern.matcher(arguments);
        if (matcher.matches()) {
            String taskName = matcher.group(1);
            try {
                LocalDate from = LocalDate.parse(matcher.group(2));
                LocalDate to = LocalDate.parse(matcher.group(3));
                return new EventCommand(taskName, from, to);
            } catch (DateTimeParseException e) {
                return new IncorrectCommand();
            }
        }
        return new IncorrectCommand();
    }

    /**
     * Prepares a {@code DeleteCommand} after validating the provided arguments.
     *
     * @param arguments The index of the task to delete.
     * @return A {@code DeleteCommand} object if arguments are valid, otherwise an {@code IncorrectCommand}.
     */
    private static Command prepareDelete(String arguments) {
        try {
            int index = Integer.parseInt(arguments.trim());
            return new DeleteCommand(index - 1);
        } catch (NumberFormatException e) {
            return new IncorrectCommand();
        }
    }

    /**
     * Prepares a {@code MarkCommand} after validating the provided arguments.
     *
     * @param arguments The index of the task to mark as completed.
     * @return A {@code MarkCommand} object if arguments are valid, otherwise an {@code IncorrectCommand}.
     */
    private static Command prepareMark(String arguments) {
        try {
            int index = Integer.parseInt(arguments.trim());
            return new MarkCommand(index - 1);
        } catch (NumberFormatException e) {
            return new IncorrectCommand();
        }
    }

    /**
     * Prepares an {@code UnmarkCommand} after validating the provided arguments.
     *
     * @param arguments The index of the task to unmark as completed.
     * @return An {@code UnmarkCommand} object if arguments are valid, otherwise an {@code IncorrectCommand}.
     */
    private static Command prepareUnmark(String arguments) {
        try {
            int index = Integer.parseInt(arguments.trim());
            return new UnmarkCommand(index - 1);
        } catch (NumberFormatException e) {
            return new IncorrectCommand();
        }
    }

    private static Command prepareFind(String arguments) {
        if (arguments.isEmpty()) {
            return new IncorrectCommand("OOPS!!! The description of a find cannot be empty.");
        }
        return new FindCommand(arguments);
    }
}
