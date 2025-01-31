package taskmax.command;

import taskmax.storage.Storage;
import taskmax.storage.TaskList;
import taskmax.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to exit the application and save tasks.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by saving the tasks and displaying a farewell message.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The UI instance for displaying messages.
     * @param storage The storage handler for saving tasks.
     * @return True, indicating that the application should terminate.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.saveTasks(tasks.getTasks());
            ui.showMessage("Tasks have been saved to my drive!");
        } catch (IOException e) {
            ui.showMessage("Error saving tasks to my drive!");
        }

        ui.showMessage("\nI hope that you are satisfied with your service.\n"
                + "See you again soon!\n");
        return true;
    }
}
