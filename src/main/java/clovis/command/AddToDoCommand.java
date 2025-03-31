package clovis.command;

import clovis.ClovisException;
import clovis.Storage;
import clovis.Ui;
import clovis.task.Task;
import clovis.task.TaskList;
import clovis.task.ToDo;

/**
 * The {@code AddToDoCommand} class represents an add command that add a {@code ToDo} task to a task list.
 */
public class AddToDoCommand extends AddCommand {
    /**
     * Constructs a {@code AddToDoCommand} instance with the specified description.
     *
     * @param description the description of the {@code ToDo} task.
     */
    public AddToDoCommand(String description) {
        super(description);
    }

    /**
     * Executes the command by adding a {@code ToDo} task to the task list.
     *
     * @param tasks the task list to be manipulated.
     * @param ui the UI for displaying messages.
     * @param storage the storage handler for storing and retrieving of tasks.
     * @return Clovis's response as a String, confirming the addition of the {@code ToDo} task.
     * @throws ClovisException if an error occurs while saving the tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ClovisException {
        Task task = new ToDo(description);
        return addTask(tasks, ui, storage, task);
    }
}
