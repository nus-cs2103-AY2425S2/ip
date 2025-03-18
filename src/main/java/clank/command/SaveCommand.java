package clank.command;

import clank.task.TaskList;
import clank.utility.Storage;
import clank.utility.Ui;

/**
 * Represents a command to save the current tasks to storage.
 */
public class SaveCommand extends Command {

    /**
     * Executes the save command, saving all tasks to the storage file.
     *
     * @param taskList The task list to save.
     * @param ui The UI instance (not used in this command).
     * @param storage The storage system to save the tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        assert storage != null : "Storage should not be null.";

        storage.saveTasks(taskList);
        System.out.println("All the tasks are saved!");
    }
}
