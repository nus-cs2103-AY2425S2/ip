package zea.command;

import zea.Storage;
import zea.ZeaException;
import zea.task.Task;
import zea.task.Tasks;
import zea.ui.Ui;

///**
// */

/**
 * A command for tagging tasks
 */
public class TagCommand extends Command {
    private int idx;
    private String[] tags;

    /**
     * Constructor for a TagCommand
     * @param idx - index of the task to be tagged
     * @param tags - a list of tags to be added for a task
     */
    public TagCommand(int idx, String[] tags) {
        this.idx = idx;
        this.tags = tags;
    }

    @Override
    public String execute(Tasks tasks, Storage storage) throws ZeaException {
        Task t = tasks.getTask(this.idx);
        for (String tag : tags) {
            t.addTag(tag);
        }
        storage.save(tasks);
        return Ui.formatItem("", t, "");
    }
}
