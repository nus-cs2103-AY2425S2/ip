package julie.command;

import julie.Storage;
import julie.TaskList;
import julie.UI;

/**
 * Represents a command to terminate the chatbot session.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command to exit the chatbot.
     * Displays a farewell message to the user.
     *
     * @param tasks The task list (not used in this command).
     * @param ui The user interface to display the farewell message.
     * @param storage The storage system (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showGoodbye();
    }
    /**
     * Indicates that this command will terminate the session.
     *
     * @return {@code true}, signaling the program should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
