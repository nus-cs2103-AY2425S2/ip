package adam.command;

import adam.core.TaskList;
import adam.exceptions.AdamException;

/**
 * Represents a command to mark a task as done.
 */
public class DoneCommand extends Command {
    /** The index of the task to mark as done */
    private int index;

    /**
     * Constructor for DoneCommand.
     *
     * @param input The input to the command.
     * @throws AdamException If an error occurs while parsing the input.
     */
    public DoneCommand(String input) throws AdamException {
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
        return inputParts[0].equals("mark") && inputParts.length == 2;
    }

    /**
     * Marks the task as done and outputs the task to the user.
     *
     * @param manager The task list to add the task to.
     * @return The output to show to the user.
     * @throws AdamException If an error occurs while adding the task.
     */
    @Override
    public String execute(TaskList manager) throws AdamException {
        try {
            String taskText = manager.markDone(this.index);
            return "Nice! I've marked this task as done:\n  " + taskText;
        } catch (IndexOutOfBoundsException e) {
            return "Task index out of bounds!";
        }
    }
}
