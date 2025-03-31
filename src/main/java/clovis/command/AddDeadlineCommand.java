package clovis.command;

import clovis.ClovisException;
import clovis.Storage;
import clovis.Ui;
import clovis.task.Deadline;
import clovis.task.Task;
import clovis.task.TaskList;

/**
 * The {@code AddDeadlineCommand} class represents an add command that add a {@code Deadline} task to a task list.
 */
public class AddDeadlineCommand extends AddCommand {
    protected String deadline;

    /**
     * Constructs a {@code AddDeadlineCommand} instance with the specified description.
     *
     * @param description the description of the {@code Deadline} task.
     */
    public AddDeadlineCommand(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Executes the command by adding a {@code Deadline} task to the task list.
     *
     * @param tasks the task list to be manipulated.
     * @param ui the UI for displaying messages.
     * @param storage the storage handler for storing and retrieving of tasks.
     * @return Clovis's response as a String, confirming the addition of the {@code Deadline} task.
     * @throws ClovisException if an error occurs while saving the tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ClovisException {
        Task task = new Deadline(description, deadline);
        return addTask(tasks, ui, storage, task);
    }
}
