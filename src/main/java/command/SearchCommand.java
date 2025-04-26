package command;

import storage.Storage;
import task.PriorityRoster;
import task.Task;
import ui.Ui;

import java.util.ArrayList;

/**
 * Searches for commands containing a keyword
 * Displays a roster of all the commands
 */
public class SearchCommand extends Command {
    String keyword;
    public SearchCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(PriorityRoster roster, Ui ui, Storage storage) {
        ArrayList<Task> match = roster.findTaskWithKeyword(keyword);
        PriorityRoster searchRoster = new PriorityRoster(match);
        if(searchRoster.numberOfTasks() == 0) {
            return "Sorry :( , no tasks found for keyword: " + keyword;
        }
        return "Here are the matching tasks in your list:\n" + searchRoster.printRoster();
    }
}
