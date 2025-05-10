package steve.commands;

import steve.exceptions.InvalidTaskNumberException;
import steve.tasks.TaskManager;

/**
 * Represents a command that marks a task as done in the task manager.
 */
public class MarkCommand implements Command {
    private TaskManager taskManager;
    private String userInput;

    /**
     * Constructs a MarkCommand with the specified task manager and user input.
     *
     * @param taskManager The task manager that manages the list of tasks.
     * @param userInput The user input containing the task number to be marked as done.
     */
    public MarkCommand(TaskManager taskManager, String userInput) {
        this.taskManager = taskManager;
        this.userInput = userInput;
    }

    /**
     * Executes the mark command by parsing the task number from the user input
     * and marking the corresponding task as done.
     *
     * @throws InvalidTaskNumberException If the specified task number is invalid.
     * @throws NumberFormatException      If the user input does not contain a valid number.
     */
    @Override
    public String execute() throws InvalidTaskNumberException, NumberFormatException {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
        taskManager.markTask(taskNumber, true); // Mark as done
        return "Task successfully marked!";
    }
}

