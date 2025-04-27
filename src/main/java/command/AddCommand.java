package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * The AddCommand class represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command by adding the task to the task list and saving it to storage.
     *
     * @param tasks   The task list to which the task is added.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage system for saving tasks.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String responseToAnswer = tasks.addTask(task);
        storage.saveTasks(tasks.getTasks());
        return responseToAnswer;
    }

    /**
     * Determines whether this command causes the application to exit.
     *
     * @return false as adding a task does not terminate the application.
     */
    public boolean isExit() {
        return false;
    }
}
