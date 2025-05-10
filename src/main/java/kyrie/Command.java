package kyrie;

/**
 * Represents a command to be executed.
 */
public abstract class Command {
    /**
     * Constructor for Command.
     */
    public Command() {
    }

    /**
     * Returns false as the default value for isExit.
     * 
     * @return False.
     */
    public Boolean isExit() {
        return false;
    }

    /**
     * Executes the command.
     * 
     * @param storage The storage object to save the task list to.
     * @param ui The ui object to show the user the added task.
     * @param tasks The task list to add the task to.
     * @throws KyrieException If an error occurs during execution.
     */
    public abstract void execute(Storage storage, Ui ui, TaskList tasks) throws KyrieException;
}