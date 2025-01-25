package tasker.command;

import tasker.Storage;
import tasker.exception.TaskerException;
import tasker.task.Task;
import tasker.task.TaskList;

/**
 * Adds a task to the task list.
 */
class AddCommand extends Command {
    /** Task to be added */
    private Task task;

    /**
     * Class constructor.
     * 
     * @param task Task to be added.
     */
    AddCommand(Task task) {
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
        storage.save(tasks.getTasks());
        return String.format("""
                Got it. I've added this task:
                  %s
                %s""", this.task, response);
    }
}
