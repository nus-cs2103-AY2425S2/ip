package Tom.commands;

import Tom.TomException;
import Tom.Storage;
import Tom.tasks.TaskList;
import Tom.tasks.Task;

/**
 * Represents a user deleting tasks from the task list.
 */
public class DeleteCommand extends Command {
    private int taskIndex;


    /**
     * Constructs a DeleteCommand with the given input
     *
     * @param input The array of input strings containing task details.
     */
    public DeleteCommand(String[] input) throws TomException {
        try {
            int number = Integer.parseInt(input[1]);
            taskIndex = number - 1;
        } catch (NumberFormatException e) {
            throw new TomException("Invalid command! Use 'delete <task number>'.");
        }
        if (input.length < 2) {
            throw new TomException("Invalid command! Use 'delete <task number>'.");
        }
    }

    /**
     * Executes the delete task command.
     *
     * @param tasks The TaskList instance to remove the task from.
     * @return The string representation of the command's response.
     * @throws TomException If the task index is invalid.
     */
    @Override
    public String execute(TaskList tasks) throws TomException {
        if (taskIndex >= tasks.getTaskListSize() || taskIndex < 0) {
            throw new TomException("Invalid task number! Use a valid number.");
        }
        Task removedTask = tasks.removeTask(taskIndex);
        Storage.saveTasks(tasks.getTaskList());

        return "This task has been removed:  " + removedTask
                + "\nThere are " + tasks.getTaskList().size() + " remaining tasks in the list.";
    }
}