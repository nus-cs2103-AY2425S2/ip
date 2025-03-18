package clank.command;

import clank.task.TaskList;
import clank.utility.Storage;
import clank.utility.Ui;

/**
 * Represents the command to exit the application.
 */
public class ByeCommand extends Command {

    /**
     * Executes the bye command by displaying a goodbye message.
     *
     * @param taskList The task list (not modified in this command).
     * @param ui The UI instance to show the goodbye message.
     * @param storage The storage system (not modified in this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        assert ui != null : "Ui should not be null.";

        ui.close();
        ui.showByeMessage();
    }

    /**
     * Indicates that this command is an exit command.
     *
     * @return {@code true} since this command exits the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
