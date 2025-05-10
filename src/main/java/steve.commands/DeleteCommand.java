package steve.commands;

import steve.exceptions.InvalidTaskNumberException;
import steve.tasks.TaskManager;

/**
 * Represents a command that deletes a task from the task list.
 */
public class DeleteCommand implements Command {
    private TaskManager taskManager;
    private String userInput;

    /**
     * Constructs a DeleteCommand with the specified task manager and user input.
     *
     * @param taskManager The task manager handling tasks.
     * @param userInput   The user input containing the task number to be deleted.
     */
    public DeleteCommand(TaskManager taskManager, String userInput) {
        this.taskManager = taskManager;
        this.userInput = userInput;
    }

    /**
     * Executes the delete command by removing the specified task from the task list.
     *
     * @throws InvalidTaskNumberException If the task number is invalid.
     * @throws NumberFormatException      If the task number is not a valid integer.
     */
    @Override
    public String execute() throws InvalidTaskNumberException, NumberFormatException {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
        taskManager.deleteTask(taskNumber);
        return "Task deleted successfully";
    }
}
