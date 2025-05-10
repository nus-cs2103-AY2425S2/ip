package baymax.command;

import java.io.IOException;

import baymax.Storage;
import baymax.TaskList;
import baymax.task.Task;


/**
 * Represents a command that marks a task as incomplete in the Baymax chatbot.
 * This command updates the task's status, saves the updated task list to storage,
 * and notifies the user.
 */
public class UnmarkCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs an {@code UnmarkCommand} with the specified task number.
     *
     * @param taskNumber The index of the task to be marked as incomplete (1-based).
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the unmark command by marking a task as incomplete,
     * saving the updated task list to storage, and notifying the user.
     *
     * @param tasks   The task list containing the task to be unmarked.
     * @param storage The storage handler to save the updated task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws IOException {
        Task task = tasks.unmark(taskNumber);
        storage.saveTasks(tasks);
        return printUnmark(task);
    }
    /**
     * Prints a message when a task is unmarked as completed.
     *
     * @param task The task that was unmarked.
     */
    private String printUnmark(Task task) {
        return "I have unmarked the task: " + task + " for you!";
    }
    /**
     * Returns the task number associated with this command.
     *
     * @return The task number as an integer.
     */
    public int getTaskNumber() {
        return this.taskNumber;
    }

}

