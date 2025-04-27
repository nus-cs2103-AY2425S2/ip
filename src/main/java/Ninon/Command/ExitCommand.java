package Ninon.Command;

import Ninon.Storage;
import Ninon.TaskList;
import Ninon.Ui;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand instance.
     */
    public ExitCommand() {
    }

    /**
     * Returns true since this command exits the program.
     *
     * @return true, indicating the program should terminate.
     */
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the exit command by displaying the exit message to the user.
     *
     * @param taskList The task list (not modified in this command).
     * @param ui The user interface to interact with the user.
     * @param storage The storage (not modified in this command).
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ui.exit();
        return "Bye. Hope to see you again soon!\n";
    }
}
