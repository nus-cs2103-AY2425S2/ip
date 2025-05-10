package baymax.command;

import java.io.IOException;

import baymax.Storage;
import baymax.TaskList;
import baymax.task.Event;

/**
 * Represents a command that adds an event task to the task list in the Baymax chatbot.
 * This command creates a new {@code Event} task and updates the task list and storage accordingly.
 */
public class AddEventCommand extends AddCommand {
    private final String from;
    private final String to;

    /**
     * Constructs an {@code AddEventCommand} with the specified task name, start time, and end time.
     *
     * @param name The name of the event task.
     * @param from The start date/time of the event in the accepted format.
     * @param to   The end date/time of the event in the accepted format.
     */
    public AddEventCommand(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the add event command by creating a new {@code Event} task,
     * adding it to the task list, saving the updated list to storage, and notifying the user via the UI.
     *
     * @param tasks   The task list to which the event task is added.
     * @param storage The storage handler to save the updated task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws IOException {
        Event newTask = new Event(taskName, from, to);
        tasks.addTask(newTask);
        storage.saveTasks(tasks);
        return printNewEvent(newTask);
    }
    /**
     * Prints a message when a new event task is added.
     *
     * @param newTask The newly added event task.
     */
    private String printNewEvent(Event newTask) {
        return "added: " + newTask;
    }
}
