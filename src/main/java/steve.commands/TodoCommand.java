package steve.commands;

import steve.tasks.TaskManager;
import steve.tasks.ToDo;

/**
 * Represents a command that creates and adds a ToDo task to the task manager.
 */
public class TodoCommand implements Command {
    private TaskManager taskManager;
    private String userInput;

    /**
     * Constructs a TodoCommand with the specified task manager and user input.
     *
     * @param taskManager The task manager that manages the list of tasks.
     * @param userInput The user input containing the description of the ToDo task.
     */
    public TodoCommand(TaskManager taskManager, String userInput) {
        this.taskManager = taskManager;
        this.userInput = userInput;
    }

    /**
     * Executes the ToDo command by extracting the task description,
     * creating a new ToDo task, adding it to the task manager, and displaying a message.
     */
    @Override
    public String execute() {
        String description = userInput.substring("todo".length()).trim();
        ToDo todo = new ToDo(description);
        taskManager.addTask(todo);
        return todo.messageString();
    }
}
