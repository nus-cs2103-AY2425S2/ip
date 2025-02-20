package tasker.command;

import tasker.task.TaskList;

/**
 * Command that makes use of task index.
 */
abstract class IndexCommand extends Command {
    /** Index of task to be operated on */
    private int index;

    /**
     * Constructor class.
     *
     * @param index The index of the task to be operated on.
     */
    IndexCommand(int index) {
        this.index = index;
    }

    /**
     * Formats the task at the index.
     *
     * @param tasks The task list to get the task from.
     * @return The description of the task at the index.
     */
    public String getIndexTaskDescription(TaskList tasks) {
        return "  " + tasks.getTaskDescription(this.index);
    }

    /**
     * Returns the index of the task to be operated on.
     *
     * @return The index of the task to be operated on.
     */
    int getIndex() {
        return this.index;
    }

    /**
     * Returns the notification when an invalid index is used.
     *
     * @return The notification when an invalid index is used.
     */
    String getInvalidIndex() {
        return String.format("Task %d does not exist.", this.index + 1);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }

        IndexCommand other = (IndexCommand) obj;
        return this.index == other.index;
    }

    @Override
    public int hashCode() {
        return super.hashCode() * 31 + Integer.hashCode(this.index);
    }
}
