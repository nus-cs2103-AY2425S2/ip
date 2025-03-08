package wizt.command;


import wizt.storage.Storage;
import wizt.task.TaskList;
import wizt.ui.Ui;

/**
 *  Represents commands that exit from program
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    /**
     * Set boolean isExit to true so program can exit
     * @return isExit
     */
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder response = new StringBuilder();
        response.append("\n Good Day to you Boss! Hope to see you again soon!");
        return response.toString();
    }

}
