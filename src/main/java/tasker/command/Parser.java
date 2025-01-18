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

        try {
            mainPart = CommandType.valueOf(cmdParts[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TaskerException(String.format("""
                    Unknown command: %s
                    %s""",
                    cmdParts[0], CommandType.listCommands()));
        }

        if (mainPart.equals(CommandType.DEADLINE) || mainPart.equals(CommandType.EVENT)
                || mainPart.equals(CommandType.TODO)) {
            Task toAdd;

            if (mainPart.equals(CommandType.TODO)) {
                if (cmdParts.length == 1) {
                    throw new TaskerException("Please provide a description for the todo task.");
                }

                toAdd = new Todo(cmdParts[1]);
            } else {
                String[] args = cmdParts[1].split(" /");

                if (mainPart.equals(CommandType.DEADLINE)) {
                    toAdd = new Deadline(args[0], args[1].split(" ", 2)[1]);
                } else {
                    toAdd = new Event(args[0], args[1].split(" ", 2)[1], args[2].split(" ", 2)[1]);
                }

            }

            return new AddCommand(toAdd);
        }

        if (mainPart.equals(CommandType.MARK) || mainPart.equals(CommandType.UNMARK)) {
            int index = Integer.parseInt(cmdParts[1]) - 1;

            if (mainPart.equals(CommandType.MARK)) {
                return new MarkCommand(index);
            } else {
                return new UnmarkCommand(index);
            }
        }

        return new ListCommand();
    }
}
