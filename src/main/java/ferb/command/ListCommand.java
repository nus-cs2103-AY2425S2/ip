package ferb.command;

import ferb.filehandler.FerbFileHandler;
import ferb.task.Task;
import ferb.tasklist.TaskList;
import ferb.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, printing all tasks in the task list.
     */
    @Override
    public void execute(Ui ui, FerbFileHandler fileHandler, TaskList tasks) {
        if (tasks.isEmpty()) {
            ui.showEmptyTaskList();
        } else {
            ui.showTaskList(tasks);
        }
    }
}
