package Krypto.Commands;
import Krypto.Exceptions.KryptoExceptions;
import Krypto.IO.GUI;
import Krypto.IO.Storage;
import Krypto.Utils.TaskList;
/**
 * Represents a command to unmark a task as completed.
 */
public class UnmarkCommand extends Command {

    private final int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index The index of the task to unmark.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }


    public void execute(GUI gui, TaskList tasks, Storage storage) throws KryptoExceptions {
        String msg = tasks.getTask(index).unmarkTask();
        gui.newResponse(msg);
        storage.store(tasks);
    }
    public boolean isExit() {
        return false;
    }
}