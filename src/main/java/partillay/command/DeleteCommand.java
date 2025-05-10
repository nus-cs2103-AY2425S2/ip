package partillay.command;

import partillay.exception.PartillayIndexException;
import partillay.task.Task;
import partillay.task.TaskList;
import partillay.ui.Ui;

/**
 * Represents a delete command to delete a task.
 */
public class DeleteCommand extends Command {
    private final int taskIndexToDelete;

    /**
     * Constructs a new Delete command.
     *
     * @param index the index of the task to be deleted from the task list
     */
    public DeleteCommand(int index) {
        this.taskIndexToDelete = index;
    }

    /**
     * Deletes task in task list with given task index and displays the updated task list.
     *
     * @param tasks the task list that stores current tasks
     * @param ui    the user interface for displaying output
     * @throws PartillayIndexException if index <= 0 or index > number of tasks (1-based indexing)
     */
    @Override
    public String execute(TaskList tasks, Ui ui) throws PartillayIndexException {
        try {
            if (taskIndexToDelete > tasks.size() | taskIndexToDelete <= 0) {
                this.isExit = true;
                throw new PartillayIndexException("No such index in your task list, bestie!");
            }
            int numTasksBefore = tasks.size();
            String result = "Noted. I've removed this task:\n";
            Task removedTask = tasks.deleteTask(taskIndexToDelete - 1);
            result += removedTask.toString() + "\n";
            result += "Now you have " + tasks.size() + " tasks in the list.";
            int numTasksAfter = tasks.size();
            assert numTasksBefore == numTasksAfter + 1 : "Number of tasks should have decremented by 1";
            return ui.getLinedMessage(result);
        } catch (PartillayIndexException e) {
            return ui.getErrorMessage(e.getMessage());
        }
    }
}
