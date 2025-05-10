package ferb.command;

import ferb.Ferb;
import ferb.task.Task;
import ferb.tasklist.TaskList;
import ferb.ui.Ui;
import ferb.filehandler.FerbFileHandler;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command{
    private int index;

    public DeleteCommand(int index) {
        assert index >= 0: "Index should be non-negative";
        this.index = index;
    }

    /**
     * Executes the delete command, removing the task from the task list and printing a confirmation message.
     */
    @Override
    public void execute(Ui ui, FerbFileHandler fileHandler, TaskList tasks) {
        assert index < tasks.size() : "Index should be within the size of the task list";
        Task task = tasks.remove(index);
        ui.showTaskDeleted(task);
    }
}
