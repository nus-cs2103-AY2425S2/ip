package steve.commands;

import steve.exceptions.InvalidTaskNumberException;
import steve.tasks.TaskManager;

/**
 * Represents a command that unmarks a task as not done in the task manager.
 */
public class UnmarkCommand implements Command {
    private TaskManager taskManager;
    private String userInput;

    /**
     * Constructs an UnmarkCommand with the specified task manager and user input.
     *
     * @param taskManager The task manager that manages the list of tasks.
     * @param userInput The user input containing the task number to be unmarked.
     */
    public UnmarkCommand(TaskManager taskManager, String userInput) {
        this.taskManager = taskManager;
        this.userInput = userInput;
    }

    /**
     * Executes the unmark command by parsing the task number from the user input
     * and marking the corresponding task as not done.
     *
     * @throws InvalidTaskNumberException If the specified task number is invalid.
     * @throws NumberFormatException      If the user input does not contain a valid number.
     */
    @Override
    public String execute() throws InvalidTaskNumberException, NumberFormatException {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
        taskManager.markTask(taskNumber, false); // Mark as not done
        return "Successfully unmarked!";
    }
}
