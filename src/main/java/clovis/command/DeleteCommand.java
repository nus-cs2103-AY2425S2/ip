package clovis.command;

import clovis.ClovisException;
import clovis.Storage;
import clovis.Ui;
import clovis.task.Task;
import clovis.task.TaskList;

/**
 * The {@code DeleteCommand} class handles the deletion of a task from a task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a {@code DeleteCommand} instance with the specified index.
     *
     * @param index the index of the task to be deleted.
     */
    public DeleteCommand(String index) throws ClovisException {
        try {
            this.index = Integer.parseInt(index);
        } catch (NumberFormatException e) {
            throw new ClovisException("Invalid input! Please enter a valid task number.\nExample: mark 2");
        }
    }

    /**
     * Executes the deletion of a task, displaying the relevant messages, and save the updated list to the storage.
     *
     * @param tasks the task list to be manipulated.
     * @param ui the UI for displaying messages.
     * @param storage the storage handler for storing and retrieving of tasks.
     * @return Clovis's response as a String, confirming the deletion of the task.
     * @throws ClovisException if an error occurs while saving the updated tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ClovisException {
        assert tasks != null : "TaskList should not be null when deleting!";
        assert index >= 1 && index <= tasks.size() : "Task index is out of bounds!";

        validateIndex(index, tasks);
        Task task = tasks.deleteTask(index);
        assert task != null : "Deleted task should not be null!";

        storage.saveTasks(tasks.getTasks());
        return ui.displayDeleteMessage(task, tasks);
    }

    /**
     * Checks if the input index is valid.
     *
     * @param index the index to be checked.
     * @param tasks the task list to get its size.
     * @throws ClovisException if the index is lower than 1 or higher than the size of the task list.
     */
    public void validateIndex(int index, TaskList tasks) throws ClovisException {
        if (index < 1 || index > tasks.size()) {
            throw new ClovisException("Invalid task index!");
        }
    }
}
