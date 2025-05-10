package baymax.command;

import java.io.IOException;

import baymax.Storage;
import baymax.TaskList;
import baymax.task.Task;


/**
 * Represents a command that marks a task as completed in the Baymax chatbot.
 * This command updates the task's status, saves the updated task list to storage,
 * and notifies the user.
 */
public class MarkCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs a {@code MarkCommand} with the specified task number.
     *
     * @param taskIndex The index of the task to be marked as completed (1-based index).
     */
    public MarkCommand(int taskIndex) {
        this.taskNumber = taskIndex;
    }

    /**
     * Executes the mark command by marking the task as completed,
     * saving the updated task list to storage, and notifying the user.
     *
     * @param tasks   The task list containing the task to be marked as completed.
     * @param storage The storage handler to save the updated task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws IOException {
        Task task = tasks.markAsDone(taskNumber);
        storage.saveTasks(tasks);
        return printMarkAsDone(task);
    }
    /**
     * Prints a message when a task is marked as completed.
     *
     * @param task The task that was marked as completed.
     */
    private String printMarkAsDone(Task task) {
        return "Good job on completing the task: " + task + " !";
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
