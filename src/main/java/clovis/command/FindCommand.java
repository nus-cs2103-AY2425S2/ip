package clovis.command;

import java.util.ArrayList;

import clovis.ClovisException;
import clovis.Storage;
import clovis.Ui;
import clovis.task.Task;
import clovis.task.TaskList;

/**
 * The {@code FindCommand} class handles searching for tasks that contains a specific keyword.
 */
public class FindCommand extends Command {
    protected String keyword;

    /**
     * Constructs a {@code FindCommand} instance with the specified keyword.
     *
     * @param keyword the keyword used to search for matching tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command by searching for tasks that contains the specified keyword
     *     and lists all the tasks that contains the specified keyword.
     *
     * @param tasks the task list to be searched.
     * @param ui the UI for displaying messages.
     * @param storage the storage handler for storing and retrieving of tasks.
     * @return Clovis's response as a String, finding all the tasks in the task list that matches the specified keyword.
     * @throws ClovisException never thrown in this implementation.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ClovisException {
        assert tasks != null : "TaskList should not be null";

        ArrayList<Task> matchingTasks = tasks.findTask(keyword);
        return ui.listMatchingTasks(matchingTasks, keyword);
    }
}
