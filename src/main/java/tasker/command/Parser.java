package tasker.command;

import tasker.exception.TaskerException;
import tasker.task.Deadline;
import tasker.task.Event;
import tasker.task.Task;
import tasker.task.Todo;

/**
 * Parses tasks.
 */
public class Parser {
    /**
     * Creates the command to handle the user input.
     *
     * @param command The input of the user.
     * @return The command to handle the user input.
     * @throws TaskerException If there is an error with the command
     */
    public static Command parseCommand(String command) throws TaskerException {
        String[] cmdParts = command.split(" ", 2);
        CommandType mainPart;
        Command toRun = null;

        try {
            mainPart = CommandType.valueOf(cmdParts[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TaskerException(String.format("""
                    Unknown command: %s
                    %s""", cmdParts[0], CommandType.listCommands()));
        }

        switch (mainPart) {
        // Fallthrough
        case DEADLINE:
        case EVENT:
        case TODO:
            Task toAdd = null;

            if (cmdParts.length != 2) {
                throw new TaskerException("Please provide a description for the task.");
            }

            switch (mainPart) {
            case TODO:
                toAdd = new Todo(cmdParts[1]);
                break;

            // Fallthrough
            case DEADLINE:
            case EVENT:
                String[] args = cmdParts[1].split(" /");

                switch (mainPart) {
                case DEADLINE:
                    if (args.length != 2 || !args[1].startsWith("by ")) {
                        throw new TaskerException("Please provide a deadline with: \"/by <deadline>\".");
                    }

                    toAdd = new Deadline(args[0], args[1].substring(3));
                    break;

                case EVENT:
                    if (args.length != 3 || !args[1].startsWith("from ") || !args[2].startsWith("to ")) {
                        throw new TaskerException("""
                                Please provide a start and end time with:
                                \"/from <start> /to <end>\".""");
                    }

                    toAdd = new Event(args[0], args[1].split(" ", 2)[1], args[2].split(" ", 2)[1]);
                    break;

                default:
                    break;
                }

            default:
                break;
            }

            toRun = new AddCommand(toAdd);
            break;

        // Fallthrough
        case DELETE:
        case MARK:
        case UNMARK:
            if (cmdParts.length != 2) {
                throw new TaskerException("Please provide a task number.");
            }

            int index;
            try {
                index = Integer.parseInt(cmdParts[1]) - 1;
            } catch (NumberFormatException e) {
                throw new TaskerException("Please provide a valid task number.");
            }

            switch (mainPart) {
            case DELETE:
                toRun = new DeleteCommand(index);
                break;

            case MARK:
                toRun = new MarkCommand(index);
                break;

            case UNMARK:
                toRun = new UnmarkCommand(index);
                break;

            default:
                break;
            }
            break;

        case LIST:
            toRun = new ListCommand();
            break;

        case BYE:
            toRun = new ByeCommand();
            break;
        }

        return toRun;
    }

    public static Task parseStorage(String line) throws TaskerException {
        String[] parts = line.split("\\|");
        TaskerException incorrectFormat = new TaskerException("Incorrect storage format");
        int length = parts.length;

        if (length < 3) {
            throw incorrectFormat;
        }

        String type = parts[0];
        boolean isDone = Boolean.parseBoolean(parts[1]);
        String description = parts[2];

        switch (type) {
        case "T":
            return new Todo(description, isDone);

        case "D":
            if (length < 4) {
                throw incorrectFormat;
            }

            return new Deadline(description, isDone, parts[3]);

        case "E":
            if (length < 5) {
                throw incorrectFormat;
            }

            return new Event(description, isDone, parts[3], parts[4]);

        default:
            throw new TaskerException(String.format("Unkown task type from storage: %s.", type));
        }
    }
}
