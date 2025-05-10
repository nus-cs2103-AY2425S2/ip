package ferb.command;

import ferb.tasklist.TaskList;
import ferb.ui.Ui;
import ferb.filehandler.FerbFileHandler;

/**
 * Represents a command that finds tasks based on a keyword in the task list.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        assert keyword != null : "Keyword should not be null";
        this.keyword = keyword;
    }

    /**
     * Executes the find command, finding tasks based on the keyword and printing them.
     */
    @Override
    public void execute(Ui ui, FerbFileHandler fileHandler, TaskList tasks) {
        TaskList foundTasks = tasks.find(keyword);
        ui.showMatchingTasks(foundTasks);
    }
}
