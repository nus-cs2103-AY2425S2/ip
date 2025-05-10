package helix.command;

import java.util.Locale;

import helix.enums.CommandType;
import helix.enums.TaskType;
import helix.exception.HelixException;
import helix.exception.InvalidCommandException;
import helix.exception.InvalidDateFormatException;
import helix.exception.InvalidNumberFormatException;
import helix.exception.MissingArgumentException;
import helix.exception.TooManyArgumentsException;
import helix.task.Deadline;
import helix.task.Event;
import helix.task.Todo;

/**
 * Creates {@code Command} instances based on user input.
 */
public class CommandFactory {

    /**
     * Parses user input and creates a {@link Command} based on the given input.
     *
     * @param input the user input specifying the type of helix.command
     * @return a {@link Command} instance corresponding to the input
     * @throws InvalidCommandException if the input helix.command type is invalid
     * @throws HelixException if the helix.command is invalid or contains improper arguments
     */
    public static Command parseCommand(String input) throws HelixException {
        String[] parts = input.trim().split(" ", 2);
        String command = parts[0].toUpperCase(Locale.ROOT);
        String args = parts.length > 1 ? parts[1].trim() : "";

        CommandType commandType;
        try {
            commandType = CommandType.valueOf(command);
            assert commandType != null : "commandType should never be null!";
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException();
        }

        return switch (commandType) {
        case TODO -> createTodoCommand(args);
        case DEADLINE -> createDeadlineCommand(args);
        case EVENT -> createEventCommand(args);
        case LIST -> createListCommand(args);
        case MARK -> createMarkCommand(args);
        case UNMARK -> createUnmarkCommand(args);
        case DELETE -> createDeleteCommand(args);
        case BYE -> createExitCommand(args);
        case FIND -> createFindCommand(args);
        case UPDATE -> createUpdateCommand(args);
        };
    }

    /**
     * Creates a {@link Todo} helix.command after validating arguments.
     *
     * @param args the arguments for the {@code todo} helix.command (helix.task description)
     * @return a {@link Command} instance for adding a {@link Todo} helix.task
     * @throws MissingArgumentException if the description is missing
     * @throws TooManyArgumentsException if the helix.command contains too many arguments
     */
    private static Command createTodoCommand(String args) throws HelixException {
        if (args.isEmpty()) {
            throw new MissingArgumentException(
                    TaskType.TODO.name().toLowerCase(Locale.ROOT),
                    "todo <description>"
            );
        }
        return new AddCommand(CommandType.TODO, new Todo(args));
    }

    /**
     * Creates a {@link Deadline} helix.command after validating arguments.
     *
     * @param args the arguments for the {@code deadline} helix.command, which must include
     *             a description and a due date separated by {@code /by}
     * @return a {@link Command} instance for adding a {@link Deadline} helix.task
     * @throws MissingArgumentException if the arguments are missing or improperly formatted
     * @throws TooManyArgumentsException if the helix.command contains too many arguments
     */
    private static Command createDeadlineCommand(String args) throws HelixException {
        if (!args.contains(" /by ")) {
            throw new MissingArgumentException(
                    TaskType.DEADLINE.name().toLowerCase(Locale.ROOT),
                    "deadline <description> /by <due date>"
            );
        }
        String[] details = args.split(" /by ", 2);
        if (details.length != 2 || details[1].split(" ").length > 2) {
            throw new TooManyArgumentsException(
                    TaskType.DEADLINE.name().toLowerCase(Locale.ROOT),
                    "deadline <description> /by <due date>"
            );
        }
        try {
            return new AddCommand(CommandType.DEADLINE, new Deadline(details[0].trim(), details[1].trim()));
        } catch (IllegalArgumentException e) {
            throw new InvalidDateFormatException(
                    "Invalid date format. Please use 'd/M/yyyy HHmm' or 'yyyy-MM-dd HHmm'."
            );
        }
    }

    /**
     * Creates an {@link Event} helix.command after validating arguments.
     *
     * @param args the arguments for the {@code event} helix.command, which must include
     *             a description, a start time (specified using {@code /from}),
     *             and an end time (specified using {@code /to})
     * @return a {@link Command} instance for adding an {@link Event} helix.task
     * @throws MissingArgumentException if the arguments are missing or improperly formatted
     * @throws TooManyArgumentsException if the helix.command contains too many arguments
     * @throws InvalidDateFormatException if the date format is invalid
     */
    private static Command createEventCommand(String args) throws HelixException {
        // Ensure the input contains valid format markers
        if (!args.contains(" /from ") || !args.contains(" /to ")) {
            throw new MissingArgumentException(
                    TaskType.EVENT.name().toLowerCase(Locale.ROOT),
                    "event <description> /from <start date/time> /to <end date/time>"
            );
        }

        try {
            // Extract description and date/time details
            String[] parts = args.split(" /from ", 2);
            String description = parts[0].trim();
            String[] dateTimes = parts[1].split(" /to ", 2);

            // Ensure start and end date/times are present
            if (dateTimes.length < 2 || dateTimes[0].isBlank() || dateTimes[1].isBlank()) {
                throw new MissingArgumentException(
                        TaskType.EVENT.name().toLowerCase(Locale.ROOT),
                        "event <description> /from <start date/time> /to <end date/time>"
                );
            }

            return new AddCommand(
                    CommandType.EVENT,
                    new Event(description, dateTimes[0].trim(), dateTimes[1].trim())
            );
        } catch (IllegalArgumentException e) {
            throw new InvalidDateFormatException(
                    "Invalid date format. Supported formats:\n"
                        + "1. d/M/yyyy HHmm\n"
                        + "2. yyyy-MM-dd HHmm."
            );
        }
    }

    /**
     * Creates a {@link ListCommand}.
     * The list helix.command is used to display all tasks currently in the helix.task list.
     *
     * @param args the arguments for the {@code list} helix.command (should be empty)
     * @return a {@link Command} instance for listing all tasks
     * @throws TooManyArgumentsException if the helix.command contains unnecessary arguments
     */
    private static Command createListCommand(String args) throws HelixException {
        if (!args.isEmpty()) {
            throw new TooManyArgumentsException(
                    CommandType.LIST.name().toLowerCase(Locale.ROOT),
                    "list"
            );
        }
        return new ListCommand();
    }

    /**
     * Creates an {@link ExitCommand}.
     * The exit helix.command is used to terminate the chatbot session.
     *
     * @param args the arguments for the {@code bye} helix.command (should be empty)
     * @return a {@link Command} instance for exiting the application
     * @throws TooManyArgumentsException if the helix.command contains unnecessary arguments
     */
    private static Command createExitCommand(String args) throws HelixException {
        if (!args.isEmpty()) {
            throw new TooManyArgumentsException(
                    CommandType.BYE.name().toLowerCase(Locale.ROOT),
                    "bye"
            );
        }
        return new ExitCommand();
    }

    /**
     * Validates and extracts a task number from the given command arguments.
     *
     * <p>This method ensures that the input contains exactly one valid positive integer representing
     * the task number. If the input is empty, contains non-numeric characters, or includes extra
     * arguments, an appropriate exception is thrown.</p>
     *
     * @param args the raw arguments provided by the user
     * @param commandName the name of the command being validated (e.g., "mark", "unmark", "delete")
     * @return the zero-based task index parsed from the input
     * @throws MissingArgumentException if no task number is provided
     * @throws TooManyArgumentsException if multiple arguments are provided instead of a single task number
     * @throws InvalidNumberFormatException if the task number is not a valid positive integer
     */
    private static int validateTaskNumber(String args, String commandName) throws HelixException {
        if (args.isEmpty()) {
            throw new MissingArgumentException(commandName, commandName + " <task number>");
        }

        String[] splitArgs = args.split(" ");

        if (splitArgs.length > 1) {
            throw new TooManyArgumentsException(commandName, commandName + " <task number>");
        }

        if (!splitArgs[0].matches("\\d+")) {
            throw new InvalidNumberFormatException("The task number must be a positive integer.");
        }

        return Integer.parseInt(splitArgs[0]) - 1;
    }

    /**
     * Creates a {@link MarkCommand} after validating arguments.
     *
     * @param args the arguments for the {@code mark} helix.command (helix.task number)
     * @return a {@link Command} instance for marking a helix.task as done
     * @throws MissingArgumentException if the helix.task number is missing
     * @throws InvalidNumberFormatException if the helix.task number is not a valid positive integer
     * @throws TooManyArgumentsException if the helix.command contains too many arguments
     */
    private static Command createMarkCommand(String args) throws HelixException {
        int taskNumber = validateTaskNumber(args, CommandType.MARK.name().toLowerCase(Locale.ROOT));
        return new MarkCommand(taskNumber);
    }

    /**
     * Creates an {@link UnmarkCommand} after validating arguments.
     *
     * @param args the arguments for the {@code unmark} helix.command (helix.task number)
     * @return a {@link Command} instance for marking a helix.task as not done
     * @throws MissingArgumentException if the helix.task number is missing
     * @throws InvalidNumberFormatException if the helix.task number is not a valid positive integer
     * @throws TooManyArgumentsException if the helix.command contains too many arguments
     */
    private static Command createUnmarkCommand(String args) throws HelixException {
        int taskNumber = validateTaskNumber(args, CommandType.UNMARK.name().toLowerCase(Locale.ROOT));
        return new UnmarkCommand(taskNumber);
    }

    /**
     * Creates a {@link DeleteCommand} after validating arguments.
     *
     * @param args the arguments for the {@code delete} helix.command (helix.task number)
     * @return a {@link Command} instance for deleting a helix.task
     * @throws MissingArgumentException if the helix.task number is missing
     * @throws InvalidNumberFormatException if the helix.task number is not a valid positive integer
     * @throws TooManyArgumentsException if the helix.command contains too many arguments
     */
    private static Command createDeleteCommand(String args) throws HelixException {
        int taskNumber = validateTaskNumber(args, CommandType.DELETE.name().toLowerCase(Locale.ROOT));
        return new DeleteCommand(taskNumber);
    }

    /**
     * Creates a FindCommand instance after validating arguments.
     *
     * @param args the arguments for the find command
     * @return a FindCommand instance
     * @throws MissingArgumentException if the keyword is missing
     */
    private static Command createFindCommand(String args) throws HelixException {
        if (args.isEmpty()) {
            throw new MissingArgumentException("find", "find <keyword>");
        }
        return new FindCommand(args);
    }

    /**
     * Creates an {@link UpdateCommand} after validating arguments.
     *
     * @param args the arguments for the {@code update} command (task number, task type, new details)
     * @return an {@link UpdateCommand} instance
     * @throws MissingArgumentException if any required argument is missing
     * @throws InvalidNumberFormatException if the task number is not a valid positive integer
     * @throws InvalidCommandException if the task type is not recognized
     */
    private static Command createUpdateCommand(String args) throws HelixException {
        if (args.isEmpty()) {
            throw new MissingArgumentException("update", "update <taskNum> <taskType> <newDetails>");
        }

        String[] parts = args.split(" ", 3);
        if (parts.length < 3) {
            throw new MissingArgumentException("update", "update <taskNum> <taskType> <newDetails>");
        }

        if (!parts[0].matches("\\d+")) {
            throw new InvalidNumberFormatException("Invalid task number. Must be a positive integer.");
        }
        int taskIndex = Integer.parseInt(parts[0]) - 1;

        TaskType taskType;
        try {
            taskType = TaskType.valueOf(parts[1].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException();
        }
        String newDetails = parts[2];
        return new UpdateCommand(taskIndex, taskType, newDetails);
    }
}
