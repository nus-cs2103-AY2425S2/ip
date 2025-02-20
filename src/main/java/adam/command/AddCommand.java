package adam.command;

import adam.core.TaskList;
import adam.exceptions.AdamException;
import adam.tasks.Task;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    /** The new task to add */
    private Task newTask;

    /**
     * Constructor for an AddCommand.
     *
     * @param input The input to create the task from.
     * @throws AdamException If an error occurs while creating the task.
     */
    public AddCommand(String input) throws AdamException {
        super();
        this.newTask = Task.of(input);
    }

    /**
     * Checks if the input matches the command.
     *
     * @param input The input to check.
     * @return True if the input matches the command, false otherwise.
     */
    public static boolean isMatch(String input) {
        try {
            Task.of(input);
            return true;
        } catch (AdamException e) {
            return false;
        }
    }

    /**
     * Adds the new task to the task list and outputs the task to the user.
     *
     * @param manager The task list to add the task to.
     * @return The output to show to the user.
     * @throws AdamException If an error occurs while adding the task.
     */
    @Override
    public String execute(TaskList manager) throws AdamException {
        manager.addTask(this.newTask);
        return "Got it. I've added this task:\n " + this.newTask;
    }
}
