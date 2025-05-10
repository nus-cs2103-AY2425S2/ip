package steve.commands;

import steve.tasks.Event;
import steve.tasks.TaskManager;

/**
 * Represents a command that creates Events
 */
public class EventCommand implements Command {
    private final TaskManager taskManager;
    private final String userInput;

    /**
     * Constructs an EventCommand with the specified task manager and user input.
     *
     * @param taskManager The task manager to manage tasks.
     * @param userInput   The user input containing the event description.
     */
    public EventCommand(TaskManager taskManager, String userInput) {
        this.taskManager = taskManager;
        this.userInput = userInput;
    }

    /**
     * Executes the command by extracting the event description from the user input,
     * creating a new Event task, adding it to the task manager, and displaying its message.
     */
    @Override
    public String execute() {
        String description = userInput.substring("event".length()).trim();
        Event event = new Event(description);
        taskManager.addTask(event);
        return event.messageString();
    }
}
