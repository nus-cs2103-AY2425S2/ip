package baymax.command;

import baymax.Storage;
import baymax.TaskList;

/**
 * Represents a command that handles invalid user input in the Baymax chatbot.
 * This command does not modify the task list or storage but informs the user of an error.
 */
public class ErrorCommand extends Command {
    private final String errorMessage;

    /**
     * Constructs an {@code ErrorCommand} with the specified error message.
     *
     * @param errorMessage The error message to be displayed to the user.
     */
    public ErrorCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Executes the error command by displaying an error message to the user.
     * This command does not modify the task list or storage.
     *
     * @param tasks   The task list (not used in this command).
     * @param storage The storage handler (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return errorMessage;
    }
}
