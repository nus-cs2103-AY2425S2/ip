package miki.command;

import miki.exception.MikiException;
import miki.storage.Storage;
import miki.task.Task;
import miki.task.TaskList;
import miki.task.ToDo;
import miki.ui.Ui;

/**
 * Represents a command to add a ToDo task to the task list.
 */
public class AddToDoCommand extends Command {
    private String description;
    private String[] tags;

    /**
     * Constructor for AddToDoCommand.
     *
     * @param description Description of the ToDo task.
     * @param tags        Tags of the ToDo task.
     */
    public AddToDoCommand(String description, String... tags) {
        super(false);
        this.description = description;
        this.tags = tags;
    }

    /**
     * Executes the command to add a ToDo task.
     *
     * @param tasks   List of tasks.
     * @param ui      Ui object.
     * @param storage Storage object.
     *
     * @return The output of the execution
     *
     * @throws MikiException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MikiException {
        Task task = new ToDo(description, tags);
        assert task != null : "Task should not be null";

        tasks.addTask(task);
        storage.save(tasks.getTaskList());
        String output = ui.showTaskAdded(task, tasks.getSize());
        return output;
    }
}
