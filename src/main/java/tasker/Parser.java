package tasker;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import tasker.command.AddCommand;
import tasker.command.ByeCommand;
import tasker.command.Command;
import tasker.command.CommandType;
import tasker.command.DeleteCommand;
import tasker.command.FindCommand;
import tasker.command.ListCommand;
import tasker.command.MarkCommand;
import tasker.command.UnmarkCommand;
import tasker.exception.TaskerException;
import tasker.task.DateTimeTask;
import tasker.task.Deadline;
import tasker.task.Event;
import tasker.task.Task;
import tasker.task.TaskType;
import tasker.task.Todo;

/**
 * Parses tasks.
 */
class Parser {
    /**
     * Creates a command to add a task.
     *
     * @param command      The command to determine the task type.
     * @param commandParts Other parts of the command for information.
     * @returns A command to add the correct task when executed.
     * @throws TaskerException If there is an error with the command.
     */
    private static AddCommand CreateAddCommand(CommandType command, String[] commandParts) throws TaskerException {
        if (commandParts.length != 2 || commandParts[1].startsWith("/")) {
            throw new TaskerException("Please provide a description for the task.");
        }

        Task taskToAdd = null;

        switch (command) {
        case TODO:
            taskToAdd = new Todo(commandParts[1]);
            break;

        // Fallthrough
        case DEADLINE:
        case EVENT:
            String[] args = commandParts[1].split(" /");
            String dateTimeInput = DateTimeTask.INPUT_FORMAT;

            switch (command) {
            case DEADLINE:
                TaskerException deadlineException = new TaskerException(
                        String.format("Please provide a deadline with: \"/by %s\".", dateTimeInput));

                if (args.length != 2 || !args[1].startsWith("by ")) {
                    throw deadlineException;
                }

                try {
                    LocalDateTime time = DateTimeTask.parseInput(args[1].substring(3));
                    taskToAdd = new Deadline(args[0], time);
                } catch (DateTimeParseException e) {
                    throw deadlineException;
                }

                break;

            case EVENT:
                TaskerException eventException = new TaskerException(
                        String.format("Please provide a start and end time with: \"/from %s /to %s\".",
                                dateTimeInput, dateTimeInput));

                if (args.length != 3 || !args[1].startsWith("from ") || !args[2].startsWith("to ")) {
                    throw eventException;
                }

                try {
                    taskToAdd = new Event(args[0], DateTimeTask.parseInput(args[1].substring(5)),
                            DateTimeTask.parseInput(args[2].substring(3)));
                } catch (DateTimeParseException e) {
                    throw eventException;
                }
                break;

            default:
                break;
            }
            break;

        default:
            throw new TaskerException("Not a command to add tasks.");
        }

        return new AddCommand(taskToAdd);
    }

    /**
     * Creates a command to modify the task list based on a provided index.
     *
     * @param command      The command to determine the task type.
     * @param commandParts Other parts of the command for information.
     * @returns A command to modify the task list based on the index in the command.
     * @throws TaskerException If there is an error with the command.
     */
    private static Command createIndexedCommand(CommandType command, String[] commandParts) throws TaskerException {
        if (commandParts.length != 2) {
            throw new TaskerException("Please provide a task number.");
        }

        int index;
        try {
            index = Integer.parseInt(commandParts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new TaskerException("Please provide a valid task number.");
        }

        switch (command) {
        case DELETE:
            return new DeleteCommand(index);

        case MARK:
            return new MarkCommand(index);

        case UNMARK:
            return new UnmarkCommand(index);

        default:
            throw new TaskerException("Not a command to modify tasks.");
        }
    }

    /**
     * Creates a command to find a task with a string.
     *
     * @param commandParts Other parts of the command for information.
     * @returns A command to find a task with the string.
     * @throws TaskerException If there is an error with the command.
     */
    private static FindCommand createFindCommand(String[] commandParts) throws TaskerException {
        if (commandParts.length != 2) {
            throw new TaskerException("Please provide a search term.");
        }

        return new FindCommand(commandParts[1]);
    }

    /**
     * Creates a command to list contents of the task list.
     *
     * @returns A command that lists tasks.
     */
    private static ListCommand createListCommand() {
        return new ListCommand();
    }

    /**
     * Creates a command to exit the program.
     *
     * @returns A command that exits the program.
     */
    private static ByeCommand createByeCommand() {
        return new ByeCommand();
    }

    /**
     * Creates the command to handle the user input.
     *
     * @param command The input of the user.
     * @return The command to handle the user input.
     * @throws TaskerException If there is an error with the command.
     */
    public static Command parseCommand(String command) throws TaskerException {
        String[] commandParts = command.split(" ", 2);
        CommandType mainPart;
        TaskerException unknownCommandException = new TaskerException(String.format("""
                Unknown command: %s
                %s""", commandParts[0], CommandType.listCommands()));

        try {
            mainPart = CommandType.valueOf(commandParts[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw unknownCommandException;
        }

        switch (mainPart) {
        // Fallthrough
        case DEADLINE:
        case EVENT:
        case TODO:
            return CreateAddCommand(mainPart, commandParts);

        // Fallthrough
        case DELETE:
        case MARK:
        case UNMARK:
            return createIndexedCommand(mainPart, commandParts);

        case FIND:
            return createFindCommand(commandParts);

        case LIST:
            return createListCommand();

        case BYE:
            return createByeCommand();

        default:
            throw unknownCommandException;
        }
    }

    public static Task parseStorage(String line) throws TaskerException {
        String[] parts = line.split("\\|");
        TaskerException incorrectFormat = new TaskerException("Incorrect storage format");
        int length = parts.length;

        if (length < 3) {
            throw incorrectFormat;
        }

        TaskType type = TaskType.valueOf(parts[0]);
        boolean isDone = Boolean.parseBoolean(parts[1]);
        String description = parts[2];

        switch (type) {
        case T:
            return new Todo(description, isDone);

        case D:
            if (length < 4) {
                throw incorrectFormat;
            }

            try {
                return new Deadline(description, isDone, LocalDateTime.parse(parts[3]));
            } catch (DateTimeParseException e) {
                throw incorrectFormat;
            }

        case E:
            if (length < 5) {
                throw incorrectFormat;
            }

            try {
                return new Event(description, isDone, LocalDateTime.parse(parts[3]), LocalDateTime.parse(parts[4]));
            } catch (DateTimeParseException e) {
                throw incorrectFormat;
            }

        default:
            throw new TaskerException(String.format("Unkown task type from storage: %s.", type));
        }
    }
}
