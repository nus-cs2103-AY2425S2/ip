package tasker.command;

import tasker.task.TaskList;

/**
 * Marking related commands.
 */
abstract class MarkingCommand extends Command {
    protected int index;

    /**
     * Constructor class.
     *
     * @param index The index of the task to be operated on.
     */
    MarkingCommand(int index) {
        this.index = index;
    }

    /**
     * Pads the task at the index.
     *
     * @param tasks The task list to get the task from.
     * @return The description of the task at the index.
     */
    @Override
    public String execute(TaskList tasks) {
        return "  " + tasks.getTaskDescription(this.index);
    }
}
