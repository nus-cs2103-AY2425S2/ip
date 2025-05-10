package ferb.command;
import ferb.task.Task;
import ferb.tasklist.TaskList;
import ferb.ui.Ui;
import ferb.filehandler.FerbFileHandler;

/**
 * Represents a command to unmark a task as done in the task list.
 */
public class UnmarkDoneCommand extends Command {
    private int index;

    public UnmarkDoneCommand(int index) {
        assert index >= 0 : "Index should be non-negative";
        this.index = index;
    }

    /**
     * Executes the unmark done command, unmarking the task as done in the task list and printing a confirmation message.
     */
    @Override
    public void execute(Ui ui, FerbFileHandler fileHandler, TaskList tasks) {
        assert index < tasks.size() : "Index should be within the size of the task list";
        Task task = tasks.get(this.index);
        task.unmarkDone();
        ui.showTaskUnmarkedDone(task);
    }
}
