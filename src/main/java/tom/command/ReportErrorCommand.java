package tom.command;

import tom.exception.TomCommandException;
import tom.storage.Storage;
import tom.tasklist.TaskList;
import tom.ui.Ui;

/**
 * Represents a command that is invalid.
 */
public class ReportErrorCommand extends Command {

    private TomCommandException exception;

    /**
     * Constructs an InvalidCommand with the specified exception.
     *
     * @param exception The exception that caused this command to be invalid.
     */
    public ReportErrorCommand(TomCommandException exception) {
        this.exception = exception;
    }

    /**
     * Executes the command to notify the user that the command is invalid.
     *
     * @param tasks The task list.
     * @param ui The UI for interacting with the user.
     * @param storage The storage for saving tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(id, "Invalid command: %s", exception.getMessage());
    }

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
