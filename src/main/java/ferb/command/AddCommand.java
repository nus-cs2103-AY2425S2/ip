package ferb.command;

import ferb.task.Task;
import ferb.tasklist.TaskList;
import ferb.ui.Ui;
import ferb.filehandler.FerbFileHandler;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command{
    private Task task;

    /**
     * Constructs an AddCommand with the specified task list and task.
     *
     * @param task the task to be added
     */
    public AddCommand(Task task) {
        assert task != null : "Task should not be null";
        this.task = task;
    }

    /**
     * Executes the add command, adding the task to the task list and printing a confirmation message.
     */
    @Override
    public void execute(Ui ui, FerbFileHandler fileHandler, TaskList tasks) {
        tasks.add(task);
        ui.showTaskAdded(task);
    }
}
