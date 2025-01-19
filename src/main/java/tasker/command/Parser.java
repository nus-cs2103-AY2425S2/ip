package tasker.command;

import tasker.exception.TaskerException;
import tasker.task.Deadline;
import tasker.task.Event;
import tasker.task.Task;
import tasker.task.Todo;

/**
 * Parses the user input.
 */
public class Parser {
    /**
     * Creates the command to handle the user input.
     *
     * @param command The input of the user.
     * @return The command to handle the user input.
     * @throws TaskerException If there is an error with the command
     */
    public static Command parse(String command) throws TaskerException {
        String[] cmdParts = command.split(" ", 2);
        CommandType mainPart;
        Command toRun = null;

        try {
            mainPart = CommandType.valueOf(cmdParts[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TaskerException(String.format("""
                    Unknown command: %s
                    %s""",
                    cmdParts[0], CommandType.listCommands()));
        }

        switch (mainPart) {
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

                    case DEADLINE:
                    case EVENT:
                        String[] args = cmdParts[1].split(" /");

                        switch (mainPart) {
                            case DEADLINE:
                                if (args.length != 2 || !args[1].startsWith("by ")) {
                                    throw new TaskerException(
                                            "Please provide a deadline with: \"/by <deadline>\"");
                                }

                                toAdd = new Deadline(args[0], args[1].substring(3));
                                break;

                            case EVENT:
                                if (args.length != 3 || !args[1].startsWith("from ") || !args[2].startsWith("to ")) {
                                    throw new TaskerException("""
                                            Please provide a start and end time with:
                                            \"/from <start> /to <end>\"""");
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

            case DELETE:
            case MARK:
            case UNMARK:
                int index = Integer.parseInt(cmdParts[1]) - 1;

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
        }

        return toRun;
    }
}
