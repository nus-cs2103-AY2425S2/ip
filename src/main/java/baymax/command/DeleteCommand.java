package baymax.command;

import baymax.Storage;
import baymax.TaskList;

import java.io.IOException;

/**
 * Represents a command that deletes a task from the task list in the Baymax chatbot.
 * This command removes a specified task from the task list, updates storage,
 * and notifies the user.
 */
public class DeleteCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs a {@code DeleteCommand} with the specified task number.
     *
     * @param taskNumber The index of the task to be deleted (1-based).
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the delete command by removing the task from the task list,
     * saving the updated list to storage, and notifying the user.
     * If the task number is invalid, an error message is displayed.
     *
     * @param tasks   The task list from which the task is deleted.
     * @param storage The storage handler to save the updated task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws IOException {
        try {
            tasks.delete(taskNumber);
            storage.saveTasks(tasks);
            return printDeleteTask(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }
    /**
     * Prints a message when a task is deleted.
     *
     * @param taskNumber The number of the deleted task.
     */
    public String printDeleteTask(int taskNumber) {
        String output = "Yay! One less task for you! \n";
        output += "Deleted task number: " + taskNumber;
        return output;
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
