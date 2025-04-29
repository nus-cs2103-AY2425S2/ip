package xuxin.command;

import xuxin.main.Statistics;
import xuxin.main.Storage;
import xuxin.main.TaskList;
import xuxin.main.Ui;

/**
 * Help Command is a command to print help message when open Duke.
 */
public class HelpCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, Statistics stats) {
        ui.addMessage("Please type: \n"
                + "1. todo + [task name] to add a task\n"
                + "2. deadline + [task name] + /by + [dd/MM/yyyy] to add a deadline // e.g., deadline homework /by 2/12/2019\n"
                + "3. event + [task name] + /from [dd/MM/yyyy] /to [dd/MM/yyyy] to add an event // e.g. event lunch /from 2/12/2019 /to 2/12/2019\n"
                + "4. list to show the tasks added\n"
                + "5. mark + [task index] to mark a task as done\n"
                + "6. unmark + [task index] to unmark a task as not done\n"
                + "7. stats to show the statistics of the tasks\n"
                + "8. delete + [task index] to delete a task\n"
                + "9. bye to say goodbye\n");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
