package tasker.command;

import tasker.TaskList;

/**
 * Adds a task to the task list
 */
class AddCommand extends Command {
    /** Task to be added */
    private String task;

    /**
     * Class constructor
     * 
     * @param task Task to be added
     */
    AddCommand(String task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks) {
        tasks.add(this.task);
        return "added: " + this.task;
    }
}
