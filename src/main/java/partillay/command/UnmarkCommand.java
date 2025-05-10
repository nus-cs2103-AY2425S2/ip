package partillay.command;

import partillay.exception.PartillayIndexException;
import partillay.task.TaskList;
import partillay.ui.Ui;

/**
 * Represents an unmark command that unmarks a task.
 */
public class UnmarkCommand extends Command {
    private final int taskIndexToUnmark;

    /**
     * Constructs a new Unmark command.
     *
     * @param index the index of the task to be unmarked
     */
    public UnmarkCommand(int index) {
        this.taskIndexToUnmark = index;
    }

    /**
     * Marks task in task list with given task index and displays the updated task list.
     *
     * @param tasks the task list that stores current tasks
     * @param ui    the user interface for displaying output
     * @throws PartillayIndexException if index > number of tasks or index <= 0
     */
    @Override
    public String execute(TaskList tasks, Ui ui) throws PartillayIndexException {
        try {
            if (taskIndexToUnmark > tasks.size() || taskIndexToUnmark <= 0) {
                throw new PartillayIndexException("No such index in your task list, bestie!");
            }
            String result = "OK, I've marked this task as not done yet:\n";
            tasks.unmarkTask(taskIndexToUnmark - 1);
            result += tasks.getTaskAsString(taskIndexToUnmark - 1) + "\n";
            return ui.getLinedMessage(result);
        } catch (PartillayIndexException e) {
            return ui.getErrorMessage(e.getMessage());
        }
    }
}
