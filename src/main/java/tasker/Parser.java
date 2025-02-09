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
     * Creates the command to handle the user input.
     *
     * @param command The input of the user.
     * @return The command to handle the user input.
     * @throws TaskerException If there is an error with the command
     */
    public static Command parseCommand(String command) throws TaskerException {
        String[] commandParts = command.split(" ", 2);
        CommandType mainPart;
        Command parsedCommand = null;

        try {
            mainPart = CommandType.valueOf(commandParts[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TaskerException(String.format("""
                    Unknown command: %s
                    %s""", commandParts[0], CommandType.listCommands()));
        }

        switch (mainPart) {
        // Fallthrough
        case DEADLINE:
        case EVENT:
        case TODO:
            Task taskToAdd = null;

            if (commandParts.length != 2 || commandParts[1].startsWith("/")) {
                throw new TaskerException("Please provide a description for the task.");
            }

            switch (mainPart) {
            case TODO:
                taskToAdd = new Todo(commandParts[1]);
                break;

            // Fallthrough
            case DEADLINE:
            case EVENT:
                String[] args = commandParts[1].split(" /");
                String dateTimeInput = DateTimeTask.INPUT_FORMAT;

                switch (mainPart) {
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
                break;
            }

            parsedCommand = new AddCommand(taskToAdd);
            break;

        // Fallthrough
        case DELETE:
        case MARK:
        case UNMARK:
            if (commandParts.length != 2) {
                throw new TaskerException("Please provide a task number.");
            }

            int index;
            try {
                index = Integer.parseInt(commandParts[1]) - 1;
            } catch (NumberFormatException e) {
                throw new TaskerException("Please provide a valid task number.");
            }

            switch (mainPart) {
            case DELETE:
                parsedCommand = new DeleteCommand(index);
                break;

            case MARK:
                parsedCommand = new MarkCommand(index);
                break;

            case UNMARK:
                parsedCommand = new UnmarkCommand(index);
                break;

            default:
                break;
            }
            break;

        case FIND:
            if (commandParts.length != 2) {
                throw new TaskerException("Please provide a search term.");
            }
            parsedCommand = new FindCommand(commandParts[1]);
            break;

        case LIST:
            parsedCommand = new ListCommand();
            break;

        case BYE:
            parsedCommand = new ByeCommand();
            break;

        default:
            break;
        }

        return parsedCommand;
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
