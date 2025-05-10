package kaji.command;

import kaji.KajiException;
import kaji.TaskList;
import kaji.Ui;

/**
 * Deals with the add command.
 */
public class InvalidCommand extends Command {
    public final Ui ui = new Ui();

    /**
     * Executes the invalid command.
     *
     * @param tasklist The task list to operate on.
     * @throws KajiException If an error occurs during command execution.
     */
    @Override
    public String execute(TaskList tasklist) throws KajiException {
        return ui.showInvalidCommandMessage();
    }

    /**
     * Indicates that this command is not an exit command.
     *
     * @return false, as this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
