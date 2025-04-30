package yuki.command;

import yuki.Storage;
import yuki.TaskList;
import yuki.Ui;
import yuki.YukiException;
import yuki.task.Task;

/**
 * Represents a command to add a task to the task list.
 */
public abstract class Command {
    public static Command lastCommand = null;
    final String[] commands;
    private final String description;

    /**
     * Creates a command object.
     *
     * @param commands The commands that can be used to execute the command.
     * @param description The description of the command.
     * @param isExit Whether the command is an exit command.
     */
    public Command(String[] commands, String description, boolean isExit) {
        this.commands = commands;
        this.description = description;
    }

    /**
     * Gets the command at the specified index.
     *
     * @param i The index of the command.
     * @return The command at the specified index.
     */
    public String getCommand(int i) {
        return commands[i];
    }

    /**
     * Gets the description of the command.
     *
     * @return The description of the command.
     */
    public String getDescription() {
        return description;
    }

    public abstract boolean isExit();

    /**
     * Executes the command.
     *
     * @param tasks TaskList containing the tasks.
     * @param ui Ui object to interact with the user.
     * @param storage Storage object to save the tasks.
     * @return The output of the command.
     * @throws YukiException If an error occurs during the execution of the command.
     */
    public abstract String execute(TaskList<Task> tasks, Ui ui, Storage storage) throws YukiException;

    /**
     * Undoes the command.
     *
     * @param tasks TaskList containing the tasks.
     * @return The output of the undo command.
     * @throws YukiException If an error occurs during the undoing of the command.
     */
    public abstract String undo(TaskList<Task> tasks) throws YukiException;
}
