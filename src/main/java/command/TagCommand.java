package command;
import core.TaskList;
import ui.Ui;
import storage.Storage;
import exception.BaimiException;
import exception.TaskIndexOutOfBoundsException;
import task.Task;
import task.Todo;

/**
 * Represents a command to tag or untag a task.
 */
public class TagCommand extends Command {
    private int taskIndex;
    private String tag;
    private boolean isAdding;  // True if adding, false if removing

    public TagCommand(int taskIndex, String tag, boolean isAdding) {
        this.taskIndex = taskIndex;
        this.tag = tag;
        this.isAdding = isAdding;
    }

    /**
     * Executes the tag command, adding or removing a tag from a task in the task list.
     *
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage handler.
     * @throws Exception If an error occurs during the execution of the command.
     */
    @Override
    public String executeAndGetResponse(TaskList tasks, Ui ui, Storage storage) throws BaimiException {
        if (isAdding) {
            tasks.tagTask(taskIndex, tag);
            ui.showMessage("Tag added: #" + tag + " to task " + (taskIndex + 1));
        } else {
            tasks.untagTask(taskIndex, tag);
            ui.showMessage("Tag removed: #" + tag + " from task " + (taskIndex + 1));
        }
        storage.save(tasks.getTasks());
        return "Nice baby! I've tagged this task:\n  ";
    }
}
