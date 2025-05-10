package ferb.command;

import ferb.tasklist.TaskList;
import ferb.ui.Ui;
import ferb.filehandler.FerbFileHandler;
/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {

    /**
     * Executes the command.
     */
    public abstract void execute(Ui ui, FerbFileHandler fileHandler, TaskList tasks);

    /**
     * Returns true if the command is an exit command.
     *
     * @return true if the command is an exit command
     */
    public boolean isExit() {
        return false;
    }
}
