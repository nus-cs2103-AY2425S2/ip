package Krypto.Commands;
import Krypto.Exceptions.KryptoExceptions;
import Krypto.IO.GUI;
import Krypto.IO.Storage;
import Krypto.Utils.TaskList;

/**
 * Represents a command to display all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand.
     * This constructor does not require any parameters.
     */
    public ListCommand() {}

    @Override
    public void execute(GUI gui, TaskList tasks, Storage storage) throws KryptoExceptions {
        tasks.printList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}