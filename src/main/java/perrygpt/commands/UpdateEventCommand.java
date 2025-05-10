package perrygpt.commands;

import perrygpt.exceptions.PerryGPTException;
import perrygpt.storage.Storage;
import perrygpt.tasks.Event;
import perrygpt.tasks.Task;
import perrygpt.tasks.TaskList;
import perrygpt.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to update the date and time of an existing event task.
 */
public class UpdateEventCommand extends Command {
    private final int index;
    private final String newFrom;
    private final String newTo;

    /**
     * Constructs an {@code UpdateEventCommand} with the specified index and new event details.
     *
     * @param index   The index of the event task to be updated.
     * @param newFrom The new start time of the event (can be null or blank to keep the existing value).
     * @param newTo   The new end time of the event (can be null or blank to keep the existing value).
     */
    public UpdateEventCommand(int index, String newFrom, String newTo) {
        this.index = index;
        this.newFrom = newFrom;
        this.newTo = newTo;
    }

    /**
     * Executes the command by updating the date and time of an event task,
     * replacing the existing event with an updated version, and saving the changes.
     *
     * @param tasks   The task list containing the event task to be updated.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage system for saving the updated task list.
     * @return A success message indicating that the event has been updated.
     * @throws PerryGPTException If the index is invalid or the selected task is not an event.
     * @throws IOException   If an error occurs while saving the updated task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PerryGPTException, IOException {
        if (index < 0 || index >= tasks.getAllTasks().size()) {
            throw new PerryGPTException("Invalid task index.");
        }
        Task task = tasks.getTask(index);
        if (!(task instanceof Event)) {
            throw new PerryGPTException("The selected task is not an Event.");
        }

        Event event = (Event) task;

        // Preserve existing values if no update is provided
        String updatedFrom = (newFrom != null && !newFrom.isBlank()) ? newFrom : event.getFrom();
        String updatedTo = (newTo != null && !newTo.isBlank()) ? newTo : event.getTo();

        // Create a new Event object with updated values
        Event updatedEvent = new Event(event.getDescription(), updatedFrom, updatedTo, event.isDone());

        // Replace the old event with the updated event
        tasks.updateTask(index, updatedEvent);
        storage.save(tasks.getAllTasks());

        return ui.showSuccess("Updated event: " + updatedEvent);
    }
}
