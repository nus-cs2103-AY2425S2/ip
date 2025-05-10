package tasker.command;

import tasker.Storage;
import tasker.exception.TaskerException;
import tasker.task.Task;
import tasker.task.TaskList;

/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {
    /** Task to be added */
    private Task task;

    /**
     * Class constructor.
     *
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds a task to the task list.
     *
     * @param tasks The list to add the task to.
     * @return The ouput from adding a task.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws TaskerException {
        String response = tasks.add(this.task);
        storage.save(tasks);
        return String.format("""
                Got it. I've added this task:
                  %s
                %s""", this.task, response);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }

        AddCommand that = (AddCommand) obj;
        return task != null ? task.equals(that.task) : that.task == null;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + (task == null ? 0 : task.hashCode());
    }
}
