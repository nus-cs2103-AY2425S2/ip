package nyanko.command;

import nyanko.storage.Storage;
import nyanko.task.TaskList;
import nyanko.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String list = "";
        for (int i = 0; i < tasks.size(); i++) {
            list = list + (i + 1) + ". " + tasks.getTask(i).toString() + "\n";
        }
        System.out.println(list);
    }
}
