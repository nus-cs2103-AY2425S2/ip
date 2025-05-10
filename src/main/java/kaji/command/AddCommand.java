package kaji.command;

import kaji.KajiException;
import kaji.TaskList;

/**
 * Deals with the add command.
 */
public class AddCommand extends Command {
    private final String type;
    private final String description;

    public AddCommand(String type, String description) {
        this.type = type;
        this.description = description;
    }

    /**
     * Executes the add command.
     *
     * @param tasklist The task list to operate on.
     * @throws KajiException If an error occurs during command execution.
     */
    @Override
    public String execute(TaskList tasklist) throws KajiException {
        return tasklist.addTask(type, description);
    }

    /**
     * Indicates that this command is not an exit command.
     *
     * @return false, as this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    public String getType() {
        return type;
    }

    /**
     * Returns a string representation of the command.
     *
     * @return A string representation of the command.
     */
    public String getDescription() {
        return description;
    }

}
