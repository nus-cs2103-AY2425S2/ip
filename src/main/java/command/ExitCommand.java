package command;

import javafx.application.Platform;
import task.Tasklist;
import ui.Ui;

/**
 * Represents a command to exit the program and print the exit message to the UI.
 */
public class ExitCommand extends Command {

    public ExitCommand() {}

    @Override
    public void execute(Tasklist tasklist, Tasklist archivedTasklist, Ui ui) {
        Platform.exit();
    }
}
