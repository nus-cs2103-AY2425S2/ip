package kaji.command;

import kaji.KajiException;
import kaji.TaskList;
import kaji.Ui;

/**
 * Deals with the exit command.
 */
public class ExitCommand extends Command {
    /**
     * This method does nothing.
     * Exit command is handled by Kaji.java.
     */
    @Override
    public String execute(TaskList tasklist) throws KajiException {
        return "";
    }

    /**
     * Indicates that this command is an exit command.
     *
     * @return true, as this command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
