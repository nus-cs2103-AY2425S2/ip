package avocado.command;

import avocado.exception.AvocadoException;
import avocado.storage.Storage;
import avocado.task.TaskList;
import avocado.ui.Ui;

/**
 * Represents a command to add a tag to a task.
 */

public class AddTagCommand extends Command {
    private final int taskIndex;
    private final String tag;

    /**
     * Constructor for AddTagCommand.
     *
     * @param taskIndex The index of the task to add the tag to.
     * @param tag The tag to be added.
     */

    public AddTagCommand(int taskIndex, String tag) {
        this.taskIndex = taskIndex;
        this.tag = tag;
    }

    /**
     * Adds the tag to the task and saves the updated task list to the storage file.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface.
     * @param storage The storage object.
     * @throws AvocadoException If an error occurs while adding the tag.
     */

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AvocadoException {
        String result = tasks.tagTask(taskIndex, tag);
        storage.saveTasks(tasks.getTasks());
        return result;
    }
}
