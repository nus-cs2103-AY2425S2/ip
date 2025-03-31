package clovis.command;

import clovis.ClovisException;
import clovis.Storage;
import clovis.Ui;
import clovis.task.Task;
import clovis.task.TaskList;

/**
 * The {@code UnmarkCommand} class handles the marking of a task in the task list as uncompleted.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs a {@code UnmarkCommand} instance with the specified index.
     *
     * @param index the index of the task to be marked as uncompleted.
     */
    public UnmarkCommand(String index) throws ClovisException {
        try {
            this.index = Integer.parseInt(index);
        } catch (NumberFormatException e) {
            throw new ClovisException("Invalid input! Please enter a valid task number.\nExample: mark 2");
        }
    }

    /**
     * Executes the marking of a task in the task list as uncompleted, displaying relevant messages,
     * and saving the updated task list to the storage.
     *
     * @param tasks the task list to be manipulated.
     * @param ui the UI for displaying messages.
     * @param storage the storage handler for storing and retrieving of tasks.
     * @return Clovis's response as a String, confirming that the task have been marked as uncompleted.
     * @throws ClovisException If an error occurs while saving the updated task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ClovisException {
        assert tasks != null : "TaskList should not be null when unmarking!";
        assert index >= 1 && index <= tasks.size() : "Task index is out of bounds!";

        validateIndex(index, tasks);
        Task task = tasks.markTask(index, false);
        storage.saveTasks(tasks.getTasks());
        return ui.displayUnmarkMessage(task);
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
