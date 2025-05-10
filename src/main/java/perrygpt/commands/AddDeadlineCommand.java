package perrygpt.commands;

import perrygpt.exceptions.PerryGPTException;
import perrygpt.storage.Storage;
import perrygpt.tasks.Deadline;
import perrygpt.tasks.TaskList;
import perrygpt.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to add a new Deadline task.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;

    /**
     * Constructs an {@code AddDeadlineCommand} with the specified task description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by The due date or time of the deadline task.
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command by adding a new {@code Deadline} task to the task list,
     * saving the updated task list to storage, and returning a success message.
     *
     * @param tasks The task list to which the deadline task will be added.
     * @param ui The user interface to display messages to the user.
     * @param storage The storage system to persist the task list.
     * @return A success message indicating the deadline task has been added.
     * @throws PerryGPTException If the description or deadline is empty.
     * @throws IOException If an error occurs while saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PerryGPTException, IOException {
        if (description.isBlank() || by.isBlank()) {
            throw new PerryGPTException("The description and deadline cannot be empty.");
        }
        Deadline deadline = new Deadline(description, by, false);
        tasks.addTask(deadline);
        storage.save(tasks.getAllTasks());
        return ui.showSuccess("Added deadline: " + deadline);
    }
}