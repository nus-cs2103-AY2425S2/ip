package g.commands;

import g.storage.Storage;
import g.tasks.TaskList;
import g.ui.Ui;
import javafx.application.Platform;

public class ExitCommand extends Command {

    /**
     * Executes the exit command, displays a goodbye message, and closes the application.
     *
     * @param tasks   The task list.
     * @param ui      The UI to display messages.
     * @param storage The storage handler.
     * @return The goodbye message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // Schedule the application to close after returning the goodbye message
        Platform.exit();
        return ui.showExit();
    }

    /**
     * Indicates that this command should exit the application.
     *
     * @return True, as this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
