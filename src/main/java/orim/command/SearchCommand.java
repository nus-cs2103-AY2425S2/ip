package orim.command;

import orim.storage.Storage;
import orim.task.Task;
import orim.tasklist.TaskList;
import orim.ui.Ui;

/**
 * Represents a command for searching task from taskList
 */
public class SearchCommand extends Command {

    private final String searchKey;
    private final boolean isAdvancedSearch;

    public SearchCommand(String searchKey) {

        super(false);
        this.searchKey = searchKey;
        this.isAdvancedSearch = false;

    }

    public SearchCommand(String searchKey, boolean isAdvancedSearch) {

        super(false);
        this.searchKey = searchKey;
        this.isAdvancedSearch = isAdvancedSearch;

    }

    /**
     * @param tasks TaskList that contain all the tasks.
     * @param ui deals with interactions with the user.
     * @param file deals with loading tasks from the file and saving tasks in the file.
     * @return return a list of filtered tasks.
     */
    public String execute(TaskList tasks, Ui ui, Storage file) {
        if (isAdvancedSearch) {
            return advancedSearch(tasks, ui, file);
        }
        TaskList ans = tasks.find(searchKey);
        return Ui.showListing(ans);
    }

    public String advancedSearch(TaskList tasks, Ui ui, Storage file) {
        TaskList ans = new TaskList();
        switch (searchKey) {
        case "unmarks":
            ans = tasks.findUnmarkTasks();
            break;
        }
        return Ui.showListing(ans);
    }

}
