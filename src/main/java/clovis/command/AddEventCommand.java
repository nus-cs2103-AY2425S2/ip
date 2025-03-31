package clovis.command;

import clovis.ClovisException;
import clovis.Storage;
import clovis.Ui;
import clovis.task.Event;
import clovis.task.Task;
import clovis.task.TaskList;

/**
 * The {@code AddEventCommand} class represents an add command that add a {@code Event} task to a task list.
 */
public class AddEventCommand extends AddCommand {
    protected String start;
    protected String end;

    /**
     * Constructs a {@code AddEventCommand} instance with the specified description.
     *
     * @param description the description of the {@code Event} task.
     */
    public AddEventCommand(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Executes the command by adding a {@code Event} task to the task list.
     *
     * @param tasks the task list to be manipulated.
     * @param ui the UI for displaying messages.
     * @param storage the storage handler for storing and retrieving of tasks.
     * @return Clovis's response as a String, confirming the addition of the {@code Event} task.
     * @throws ClovisException if an error occurs while saving the tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ClovisException {
        Task task = new Event(description, start, end);
        return addTask(tasks, ui, storage, task);
    }
}
