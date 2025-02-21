package bobandsteve.command;

import bobandsteve.exception.BobAndSteveException;
import bobandsteve.storage.Storage;
import bobandsteve.tasklist.TaskList;
import bobandsteve.ui.Ui;

/**
 * An abstract class representing a command in the application.
 * Subclasses of this class implement specific commands to be executed
 * during the program's lifecycle.
 */
public abstract class Command {

    protected String response;

    /**
     * Executes the specific command. The implementation of this method
     * should define the behavior of the command, such as modifying
     * the task list, interacting with the UI, or manipulating storage.
     *
     * @param taskList The list of tasks, which may be modified by the command.
     * @param ui The user interface, which may be updated by the command.
     * @param storage The storage system, which may be interacted with by the command.
     * @throws BobAndSteveException If an error occurs during the command's execution.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws BobAndSteveException;

    /**
     * Returns true if the command should cause the program to exit.
     * This method is used to determine whether the application should
     * stop executing or continue based on the command's behavior.
     *
     * @return true if the command ends the program, false otherwise.
     */
    public abstract boolean isExit();

    public abstract String getString();
}
