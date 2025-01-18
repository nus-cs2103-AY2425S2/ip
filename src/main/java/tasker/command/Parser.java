package tasker.command;

import tasker.task.Todo;
import tasker.task.Deadline;
import tasker.task.Event;
import tasker.exception.TaskerException;

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
        String mainPart = cmdParts[0];

        if (mainPart.equals("list")) {
            return new ListCommand();
        }

        if (mainPart.equals("mark") || mainPart.equals("unmark")) {
            int index = Integer.parseInt(cmdParts[1]) - 1;

            if (mainPart.equals("mark")) {
                return new MarkCommand(index);
            } else {
                return new UnmarkCommand(index);
            }
        }

        if (mainPart.equals("deadline") || mainPart.equals("event")) {
            String[] args = cmdParts[1].split(" /");

            if (mainPart.equals("deadline")) {
                return new AddCommand(new Deadline(args[0], args[1].split(" ", 2)[1]));
            } else {
                return new AddCommand(new Event(args[0], args[1].split(" ", 2)[1], args[2].split(" ", 2)[1]));
            }
        }

        if (mainPart.equals("todo")) {
            return new AddCommand(new Todo(cmdParts[1]));
        }

        throw new TaskerException("Unknown command: " + mainPart);
    }
}
