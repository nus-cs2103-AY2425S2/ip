package parakeet.command;


import parakeet.DuplicateTaskError;
import parakeet.Storage;
import parakeet.TaskList;

public class UnmarkCommand extends Command {
    private int taskIndex;
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Unmarks the task at the specified index as completed.
     * Sends a confirmation message displaying the task unmarked as done.
     *
     * @param taskList List of tasks where the task is to be unmarked as completed.
     * @param storage  The storage used to save the tasks (not used in this method).
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DuplicateTaskError {
        taskList.unDone(taskIndex);

        String response = "Ok, I've marked this task as not done yet: \n" + taskList.print(taskIndex);
        return response;

    }
}
