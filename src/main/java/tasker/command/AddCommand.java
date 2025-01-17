package tasker.command;

import tasker.task.TaskList;

/**
 * Adds a task to the task list
 */
class AddCommand extends Command {
    /** Task to be added */
    private String task;

    /**
     * Class constructor
     * 
     * @param description Description of task to be added
     */
    AddCommand(String description) {
        this.task = description;
    }

    @Override
    public String execute(TaskList tasks) {
        tasks.add(this.task);
        return "added: " + this.task;
    }
}
