package tasker.command;

import tasker.task.Todo;
import tasker.task.Deadline;

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
    public static Command parse(String command) {
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

        if (mainPart.equals("deadline")) {
            String[] args = cmdParts[1].split(" /");
            return new AddCommand(new Deadline(args[0], args[1].split(" ", 2)[1]));
        }

        return new AddCommand(new Todo(cmdParts[1]));
    }
}
