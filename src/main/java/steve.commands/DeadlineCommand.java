package steve.commands;

import steve.tasks.Deadline;
import steve.tasks.TaskManager;

/**
 * Represents a command that creates a task with a deadline.
 * This command parses the user input to extract the description of the task
 * and creates a {@link Deadline} task. The task is then added to the
 * {@link TaskManager} and a message related to the task is displayed.
 */
public class DeadlineCommand implements Command {
    private TaskManager taskManager;
    private String userInput;

    /**
     * Constructs a {@link DeadlineCommand} with the given task manager and user input.
     *
     * @param taskManager The TaskManager instance responsible for managing tasks.
     * @param userInput   The input string provided by the user, containing the task description.
     */
    public DeadlineCommand(TaskManager taskManager, String userInput) {
        this.taskManager = taskManager;
        this.userInput = userInput;
    }

    /**
     * Executes the command, creating a task with a deadline.
     * <p>
     * This method extracts the task description from the user input,
     * creates a new {@link Deadline} task, adds it to the {@link TaskManager},
     * and displays the task's associated message.
     */
    @Override
    public String execute() {
        String description = userInput.substring("deadline".length()).trim();
        Deadline deadline = new Deadline(description);
        taskManager.addTask(deadline);
        return deadline.messageString();
    }
}

