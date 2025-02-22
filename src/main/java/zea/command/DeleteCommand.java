package zea.command;

import zea.Storage;
import zea.ZeaException;
import zea.task.Task;
import zea.task.Tasks;
import zea.ui.Ui;

/**
 * A command for deleting tasks
 */
public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public String execute(Tasks tasks, Storage storage) throws ZeaException {
        if (idx < 0 || idx >= tasks.getTotalTasks()) {
            throw new ZeaException("Invalid index. Please choose an index between 1 and " + tasks.getTotalTasks());
        }
        Task task = tasks.getTask(idx);
        tasks.delete(this.idx);
        storage.save(tasks);
        return Ui.formatItem("Sad to see this task go", task,
                "Now you have " + tasks.getTotalTasks() + " tasks in the list");
    }
}
