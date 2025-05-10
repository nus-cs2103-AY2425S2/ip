package perrygpt.commands;

import perrygpt.exceptions.PerryGPTException;
import perrygpt.storage.Storage;
import perrygpt.tasks.Event;
import perrygpt.tasks.TaskList;
import perrygpt.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to add a new Event task.
 */
public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Constructs an {@code AddEventCommand} with the specified event description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command by adding a new {@code Event} task to the task list,
     * saving the updated task list to storage, and returning a success message.
     *
     * @param tasks The task list to which the event task will be added.
     * @param ui The user interface to display messages to the user.
     * @param storage The storage system to persist the task list.
     * @return A success message indicating the event task has been added.
     * @throws PerryGPTException If the event description, start time, or end time is empty.
     * @throws IOException If an error occurs while saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PerryGPTException, IOException {
        if (description.isBlank() || from.isBlank() || to.isBlank()) {
            throw new PerryGPTException("The event description, start, and end time cannot be empty.");
        }
        Event event = new Event(description, from, to, false);
        tasks.addTask(event);
        storage.save(tasks.getAllTasks());
        return ui.showSuccess("Added event: " + event);
    }
}