package avocado.command;

import avocado.exception.AvocadoException;
import avocado.storage.Storage;
import avocado.task.TaskList;
import avocado.ui.Ui;

/**
 * Represents a command to remove a tag from a task.
 */

public class RemoveTagCommand extends Command {
    private final int taskIndex;
    private final String tag;

    /**
     * Constructor for RemoveTagCommand.
     *
     * @param taskIndex The index of the task to remove the tag from.
     * @param tag The tag to be removed.
     */

    public RemoveTagCommand(int taskIndex, String tag) {
        this.taskIndex = taskIndex;
        this.tag = tag;
    }

    /**
     * Removes the tag from the task and saves the updated task list to the storage file.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface.
     * @param storage The storage object.
     * @throws AvocadoException If an error occurs while removing the tag.
     */
    
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AvocadoException {
        String result = tasks.untagTask(taskIndex, tag);
        storage.saveTasks(tasks.getTasks());
        return result;
    }
}
