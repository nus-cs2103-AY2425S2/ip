package adam.command;

import adam.core.TaskList;
import adam.exceptions.AdamException;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand extends Command {
    /** The index of the task to unmark */
    private int index;

    /**
     * Constructor for UnmarkCommand.
     *
     * @param input User input.
     * @throws AdamException If an error occurs while parsing the input.
     */
    public UnmarkCommand(String input) throws AdamException {
        super();
        this.index = Integer.parseInt(input.split(" ")[1]) - 1;
    }

    /**
     * Checks if the input matches the command.
     *
     * @param input The input to check.
     * @return True if the input matches the command, false otherwise.
     */
    public static boolean isMatch(String input) {
        String[] inputParts = input.split(" ");
        return inputParts[0].equals("unmark") && inputParts.length == 2;
    }

    /**
     * Unmarks the task as done and outputs the task to the user.
     *
     * @param manager The task list to add the task to.
     * @return The output to show to the user.
     * @throws AdamException If an error occurs while adding the task.
     */
    @Override
    public String execute(TaskList manager) throws AdamException {
        try {
            String taskText = manager.unmarkDone(this.index);
            return "OK, I've marked this task as not done yet:\n  " + taskText;
        } catch (IndexOutOfBoundsException e) {
            return "Task index out of bounds!";
        }
    }
}
