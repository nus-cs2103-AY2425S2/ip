package nickiminaj.command;

import nickiminaj.Storage;
import nickiminaj.TaskList;
import nickiminaj.Ui;

/**
 * Represents a command that signals the program to exit.
 */
public class ByeCommand extends Command {

    /**
     * Executes the bye command by displaying the exit message to the user.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The user interface to display messages.
     * @param storage The storage (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
    }

    /**
     * Indicates that this command is an exit command.
     *
     * @return true, indicating the program should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
