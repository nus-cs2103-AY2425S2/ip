package g.commands;
import g.storage.Storage;
import g.tasks.TaskList;
import g.ui.Ui;

public abstract class Command {
    /**
     * Executes the command and returns the response as a String.
     * 
     * @param tasks The list of tasks.
     * @param ui The UI to interact with the user.
     * @param storage The storage to save task data.
     * @return The response to be displayed in the GUI.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Determines if the command should exit the application.
     * 
     * @return True if this command should exit the application, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
