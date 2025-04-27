package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * The ExitCommand class represents a command to terminate the application.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command by displaying a goodbye message.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The user interface for displaying messages.
     * @param storage The storage system (not used in this command).
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        return "Goodbye!";
    }

    /**
     * Determines whether this command causes the application to exit.
     *
     * @return true as this command terminates the application.
     */
    public boolean isExit() {
        return true;
    }
}
