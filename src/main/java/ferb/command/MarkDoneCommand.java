package ferb.command;

import ferb.task.Task;
import ferb.tasklist.TaskList;
import ferb.ui.Ui;
import ferb.filehandler.FerbFileHandler;

/**
 * Represents a command to mark a task as done based on its index.
 */
public class MarkDoneCommand extends Command {
    private int index;

    public MarkDoneCommand(int index) {
        assert index >= 0 : "Index should be non-negative";
        this.index = index;
    }

    /**
     * Executes the mark done command, marking the task as done and printing a confirmation message.
     */
    @Override
    public void execute(Ui ui, FerbFileHandler fileHandler, TaskList tasks) {
        assert index < tasks.size() : "Index should be within the size of the task list";
        Task task = tasks.get(index);
        task.markDone();
        ui.showTaskMarkedDone(task);
    }
}
