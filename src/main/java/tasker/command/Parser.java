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
        String mainPart = cmdParts[0];

        if (mainPart.equals(CommandType.LIST.toString())) {
            return new ListCommand();
        }

        if (mainPart.equals(CommandType.DEADLINE.toString()) || mainPart.equals(CommandType.EVENT.toString())
                || mainPart.equals(CommandType.TODO.toString())) {
            Task toAdd;

            if (mainPart.equals(CommandType.TODO.toString())) {
                toAdd = new Todo(cmdParts[1]);
            } else {
                String[] args = cmdParts[1].split(" /");

                if (mainPart.equals(CommandType.DEADLINE.toString())) {
                    toAdd = new Deadline(args[0], args[1].split(" ", 2)[1]);
                } else {
                    toAdd = new Event(args[0], args[1].split(" ", 2)[1], args[2].split(" ", 2)[1]);
                }

            }

            return new AddCommand(toAdd);
        }

        if (mainPart.equals(CommandType.MARK.toString()) || mainPart.equals(CommandType.UNMARK.toString())) {
            int index = Integer.parseInt(cmdParts[1]) - 1;

            if (mainPart.equals(CommandType.MARK.toString())) {
                return new MarkCommand(index);
            } else {
                return new UnmarkCommand(index);
            }
        }

        throw new TaskerException(String.format("""
                Unknown command: %s
                %s""",
                mainPart, CommandType.listCommands()));
    }
}
