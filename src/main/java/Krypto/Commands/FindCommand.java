package Krypto.Commands;

import Krypto.Exceptions.KryptoExceptions;
import Krypto.IO.Storage;
import Krypto.IO.GUI;
import Krypto.Utils.TaskList;


/**
 * Executes a command to find tasks that contain a specific keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Creates a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in tasks.
     */
    public FindCommand(String keyword) {

        this.keyword = keyword;
    }

    @Override
    public void execute(GUI gui, TaskList tasks, Storage storage) throws KryptoExceptions {
        tasks.getTasksWithKeyword(keyword);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}