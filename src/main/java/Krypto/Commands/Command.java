package Krypto.Commands;
import Krypto.Exceptions.KryptoExceptions;
import Krypto.IO.GUI;
import Krypto.IO.Storage;
import Krypto.Utils.TaskList;

/**
 * Represents an abstract command in the Krypto application.
 * <p>
 * All specific commands (e.g., mark, unmark, list, delete) should extend this class and implement
 * the {@link #execute(GUI, TaskList, Storage)} and {@link #isExit()} methods.
 * </p>
 */
public abstract class Command {

    /**
     * Executes the command.
     * <p>
     * Each specific command must implement this method to define its behavior when executed.
     * </p>
     *
     * @param gui The GUI object used for displaying information to the user.
     * @param tasks The task list containing all tasks.
     * @param storage The storage object used to persist the task list.
     * @throws KryptoExceptions If there is an issue during command execution.
     */
    public abstract void execute(GUI gui, TaskList tasks, Storage storage) throws KryptoExceptions;

    /**
     * Indicates whether this command will exit the program.
     * <p>
     * Each specific command should define whether it causes the program to exit.
     * </p>
     *
     * @return true if the command exits the program; false otherwise.
     */
    public abstract boolean isExit();
}