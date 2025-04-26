package command;

import storage.Storage;
import task.PriorityRoster;
import ui.Ui;

/**
 * Exits the chatbot
 */
public class ByeCommand extends Command {
    public ByeCommand() {}

    @Override
    public String execute(PriorityRoster priorityRoster, Ui ui, Storage storage) {
        return ui.endMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
