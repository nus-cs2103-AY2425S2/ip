package tom.command;

import tom.storage.Storage;
import tom.task.Todo;
import tom.tasklist.TaskList;
import tom.ui.Ui;

/**
 * Represents a command to add a todo task to the task list.
 */
public class TodoCommand extends Command {

    private String description;

    /**
     * Constructs a TodoCommand with the specified description.
     *
     * @param description The description of the task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to add a todo task to the task list.
     *
     * @param tasks The task list.
     * @param ui The UI for interacting with the user.
     * @param storage The storage for saving tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Todo task = new Todo(description);
        tasks.addTask(task);

        ui.showMessage(id, "added %s to tasklist (current size: %d)", task, tasks.size());
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
