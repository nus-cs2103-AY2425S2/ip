package tasker.command;

import tasker.task.TaskList;

/**
 * Command for unmarking a task as done.
 */
class UnmarkCommand extends MarkingCommand {
    UnmarkCommand(int index) {
        super(index);
    }

    /**
     * Unmarks the task with the corresponding index as done.
     *
     * @param tasks The list of tasks to unmark from.
     * @return Output of unmarking the task.
     */
    @Override
    public String execute(TaskList tasks) {
        tasks.unmarkTask(this.index);
        return "OK, I've marked this task as not done yet:\n"
                + super.execute(tasks);
    }
}
