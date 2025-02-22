package zea.command;

import zea.Storage;
import zea.task.Tasks;
import zea.ui.Ui;

/**
 * A command for listing all tasks
 */
public class ListCommand extends Command {
    @Override
    public String execute(Tasks tasks, Storage storage) {
        return Ui.formatWithNumbering("Here are your tasks", tasks.getAllTasks());
    }
}
