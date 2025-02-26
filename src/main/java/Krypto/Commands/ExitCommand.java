package Krypto.Commands;
import Krypto.Exceptions.KryptoExceptions;
import Krypto.IO.GUI;
import Krypto.IO.Storage;
import Krypto.Utils.TaskList;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand.
     * This constructor does not require any parameters.
     */
    public ExitCommand() {}

    @Override
    public void execute(GUI gui, TaskList tasks, Storage storage) throws KryptoExceptions {
        gui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}