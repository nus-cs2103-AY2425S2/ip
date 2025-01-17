package tasker.command;

import tasker.task.TaskList;
import tasker.task.Task;

/**
 * Adds a task to the task list
 */
class AddCommand extends Command {
    /** Task to be added */
    private Task task;

    /**
     * Class constructor
     * 
     * @param task Task to be added
     */
    AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks) {
        tasks.add(this.task);
        return "Got it. I've added this task:\n"
                + "  " + this.task + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }
}
