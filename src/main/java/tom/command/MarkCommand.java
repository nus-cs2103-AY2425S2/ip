package tom.command;

import tom.exception.TomCommandException;
import tom.storage.Storage;
import tom.tasklist.TaskList;
import tom.ui.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {

    private int position;

    /**
     * Constructs a MarkCommand with the specified position.
     *
     * @param position The position of the task to be marked as done (1-based index).
     */
    public MarkCommand(int position) {
        this.position = position;
    }

    /**
     * Executes the command to mark a task as done in the task list.
     *
     * @param tasks The task list.
     * @param ui The UI for interacting with the user.
     * @param storage The storage for saving tasks.
     * @throws TomCommandException If an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TomCommandException {
        tasks.markTask(position, true);

        // Let the ListCommand handle the printing of tasks
        Command command = new ListCommand();
        command.setId(id);
        command.execute(tasks, ui, storage);
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
