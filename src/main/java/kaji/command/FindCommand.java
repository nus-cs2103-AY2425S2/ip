package kaji.command;

import kaji.KajiException;
import kaji.TaskList;

/**
 * Deals with the find command.
 */
public class FindCommand extends Command {
    private final String pattern;

    public FindCommand(String pattern) {
        this.pattern = pattern;
    }

    /**
     * Executes the find command.
     *
     * @param tasklist The task list to operate on.
     * @throws KajiException If an error occurs during command execution.
     */
    @Override
    public String execute(TaskList tasklist) throws KajiException {
        return tasklist.findTasks(pattern);
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
