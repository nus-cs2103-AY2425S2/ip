package commands;

import javafx.application.Platform;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to exit the chatbot.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Platform.exit();
        return null;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
