package adam.command;

import adam.core.TaskList;
import adam.exceptions.AdamException;
import adam.tasks.Task;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    /** The index of the task to delete */
    private int index;

    /**
     * Constructor for a delete command.
     *
     * @param input The input to the command.
     * @throws AdamException If an error occurs while creating the command.
     */
    public DeleteCommand(String input) throws AdamException {
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
        return inputParts[0].equals("delete") && inputParts.length == 2;
    }

    /**
     * Deletes the task from the task list and outputs the task to the user.
     *
     * @param manager The task list to add the task to.
     * @return The output to show to the user.
     * @throws AdamException If an error occurs while adding the task.
     */
    @Override
    public String execute(TaskList manager) throws AdamException {
        try {
            Task task = manager.deleteTask(this.index);
            return "OK, I've deleted this task:\n " + task;
        } catch (IndexOutOfBoundsException e) {
            return "Task index out of bounds!";
        }
    }
}
