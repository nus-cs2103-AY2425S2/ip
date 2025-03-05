package g.commands;

import g.storage.Storage;
import g.tasks.TaskList;
import g.ui.Ui;

public class InvalidCommand extends Command {
    private final String error;

    public InvalidCommand(String error) {
        this.error = error;
    }

    /**
     * Executes the invalid command to show an invalid message.
     *
     * @param tasks   The task list.
     * @param ui      The UI to display the error messages.
     * @param storage The storage to save data if needed.
     * @return An invalid command with the error.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showError("Invalid command: " + this.error);
    }
}
