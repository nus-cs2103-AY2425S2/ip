package kaji.command;

import kaji.KajiException;
import kaji.TaskList;

/**
 * Deals with the list command.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command.
     *
     * @param tasklist The task list to operate on.
     * @throws KajiException If an error occurs during command execution.
     */
    @Override
    public String execute(TaskList tasklist) throws KajiException {
        return tasklist.showTaskList();
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
