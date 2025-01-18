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

                switch (mainPart) {
                    case TODO:
                        if (cmdParts.length == 1) {
                            throw new TaskerException("Please provide a description for the todo task.");
                        }

                        toAdd = new Todo(cmdParts[1]);

                    case DEADLINE:
                    case EVENT:
                        String[] args = cmdParts[1].split(" /");
                        switch (mainPart) {
                            case DEADLINE:
                                toAdd = new Deadline(args[0], args[1].split(" ", 2)[1]);
                                break;

                            case EVENT:
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

            case MARK:
            case UNMARK:
                int index = Integer.parseInt(cmdParts[1]) - 1;

                switch (mainPart) {
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
