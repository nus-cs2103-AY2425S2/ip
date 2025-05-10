package nyanko.command;

import nyanko.storage.Storage;
import nyanko.task.TaskList;
import nyanko.ui.Ui;

/**
 * Represents a command that displays the current list of tasks.
 * The list includes all tasks stored in the {@link TaskList}, each
 * task being numbered sequentially.
 */

public class ListCommand extends Command {

    /**
     * Executes the command to list all tasks currently stored in the task list.
     * The list is formatted with numbering and displayed in both CLI and GUI.
     *
     * @param tasks   The {@link TaskList} containing the current tasks.
     * @param ui      The {@link Ui} instance responsible for user interaction.
     * @param storage The {@link Storage} instance used to save task data (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String list = "";
        for (int i = 0; i < tasks.size(); i++) {
            list = list + (i + 1) + ". " + tasks.getTask(i).toString() + "\n";
        }
        ui.showMessage(list);
    }
}
