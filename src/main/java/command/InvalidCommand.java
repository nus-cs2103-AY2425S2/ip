package command;
import storage.Storage;
import task.PriorityRoster;
import ui.Ui;

/**
 * Handles incorrect user command inputs
 */
public class InvalidCommand extends Command {
    String message;
    public InvalidCommand(String message) {
        this.message = message;
    }

    @Override
    public String execute(PriorityRoster priorityRoster, Ui ui, Storage storage) {
        return message;
    }
}
