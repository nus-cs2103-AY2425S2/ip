package tasker.command;

import tasker.task.TaskList;

/**
 * Command for marking a task as done.
 */
class MarkCommand extends MarkingCommand {
    MarkCommand(int index) {
        super(index);
    }

    /**
     * Marks the task with the corresponding index as done.
     *
     * @param tasks The list of tasks to mark from
     * @return Notice of marking the task
     */
    @Override
    public String execute(TaskList tasks) {
        tasks.markTask(this.index);
        return "Nice! I've marked this task as done:\n"
                + super.execute(tasks);
    }
}
