package gigi.commands;

import gigi.exceptions.GigiException;
import gigi.storage.Storage;
import gigi.tasks.Task;
import gigi.tasks.Tasklist;
import gigi.ui.Ui;

/**
 * Represents a command to list all tasks currently in the task list.
 * */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public ListCommand() {}

    @Override
    public String execute(Tasklist tasks, Ui ui, Storage storage) throws GigiException {
        if (tasks.isEmpty()) {
            return ui.showMessage("MEOW!!! You have nothing to do.");
        } else {
            int count = 0;
            StringBuilder result = new StringBuilder();
            result.append("Don't forget what you have to do:\n");
            for (Task task : tasks) {
                count++;
                result.append(count).append(". ");
                result.append(task.toString()).append("\n");
            }
            return result.toString();
        }
    }
}
