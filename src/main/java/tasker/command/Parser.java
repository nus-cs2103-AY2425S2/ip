package tasker.command;

import tasker.task.Todo;

/**
 * Parses the user input
 */
public class Parser {
    /**
     * Creates the command to handle the user input.
     *
     * @param command The input of the user
     * @return The command to handle the user input
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

        return new AddCommand(new Todo(cmdParts[1]));
    }
}
