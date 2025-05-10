package ricky.command;

import ricky.Storage;
import ricky.Ui;
import ricky.task.TaskList;

/**
 * Represents a command to find tasks with a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command, printing tasks that match the keyword.
     *
     * @param tasks   The task list to search.
     * @param ui      The UI to print the matching tasks.
     * @param storage The storage (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.find(keyword);
        return ui.getFindMessage(matchingTasks);
    }
}
