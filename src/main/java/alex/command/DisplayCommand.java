package alex.command;

import alex.Storage;
import alex.Ui;
import alex.task.TaskList;

public class DisplayCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.displayList(ui);
    }
}
