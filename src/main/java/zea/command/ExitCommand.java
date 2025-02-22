package zea.command;

import zea.Storage;
import zea.task.Tasks;

/**
 * A command for exiting the program
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        this.isExit = true;
    }

    @Override
    public String execute(Tasks tasks, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }
}
