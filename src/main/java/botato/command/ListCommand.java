package botato.command;

import botato.TaskList;
import botato.Ui;

/**
 * This class encapsulates the actions to be performed when a List command is parsed by the parser.
 * It shows all the tasks currently stored in the given task list.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return tasks.show();
    }
}
