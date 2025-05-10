package baymax.command;

/**
 * Represents an abstract command for adding tasks in the Baymax chatbot.
 * This class serves as a base for specific commands that add different types of tasks
 * (e.g., to-do, deadline, and event tasks).
 */
public abstract class AddCommand extends Command {
    protected final String taskName;

    /**
     * Constructs an {@code AddCommand} with the specified task name.
     *
     * @param taskName The name of the task to be added.
     */
    public AddCommand(String taskName) {
        this.taskName = taskName;
    }
}
