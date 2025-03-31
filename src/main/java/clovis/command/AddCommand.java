package clovis.command;

import clovis.ClovisException;
import clovis.Storage;
import clovis.Ui;
import clovis.task.Task;
import clovis.task.TaskList;

/**
 * The {@code AddCommand} class represents an abstract add command that add tasks to a task list.
 */
public abstract class AddCommand extends Command {
    protected String description;

    /**
     * Constructs a {@code AddCommand} instance with the specified task description.
     *
     * @param description the description of a task.
     */
    public AddCommand(String description) {
        this.description = description;
    }

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws ClovisException;

    /**
     * Adds a task to the task list, display relevant messages, and saves the updated list to the storage.
     *
     * @param tasks the list of tasks to which the task will be added to.
     * @param ui the UI for displaying messages.
     * @param storage the storage handler for saving tasks.
     * @param task the task to be added.
     * @return Clovis's response as a String, confirming the addition of the task.
     * @throws ClovisException if an error occurs while saving the updated tasks.
     */
    public String addTask(TaskList tasks, Ui ui, Storage storage, Task task) throws ClovisException {
        assert task != null : "Task should not be null";

        tasks.addTask(task);

        assert !tasks.isEmpty() : "Tasks should not be empty after adding";

        storage.saveTasks(tasks.getTasks());
        return ui.displayAddMessage(task, tasks);
    }
}
