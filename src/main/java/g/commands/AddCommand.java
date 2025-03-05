package g.commands;

import g.storage.Storage;
import g.tasks.Task;
import g.tasks.TaskList;
import g.ui.Ui;

/**
 * Represents the command to add a task to the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command to add a task to the task list.
     *
     * @param tasks   The task list where the task will be added.
     * @param ui      The UI to display messages.
     * @param storage The storage to save data if needed.
     * @return A formatted message confirming task addition or indicating a duplicate.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert task != null : "Task should not be null before adding!";
        
        String message = tasks.addTask(task, ui);
        storage.save(tasks.getTasks());
        return message;
    }
}
