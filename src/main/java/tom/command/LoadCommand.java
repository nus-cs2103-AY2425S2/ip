package tom.command;

import tom.exception.TomCommandException;
import tom.storage.Storage;
import tom.tasklist.TaskList;
import tom.ui.Ui;

/**
 * Represents a command to load the task list from a file.
 */
public class LoadCommand extends Command {

    /**
     * Executes the command to save the task list to a file.
     *
     * @param tasks The task list.
     * @param ui The UI for interacting with the user.
     * @param storage The storage for saving tasks.
     * @throws TomCommandException if an error occurs during file load.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TomCommandException {
        tasks.clear();
        storage.loadFile(tasks);
    };

    /**
     * Indicates whether this command exits the application.
     *
     * @return false as this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
