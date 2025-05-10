package nyanko.command;

import nyanko.storage.Storage;
import nyanko.task.TaskList;
import nyanko.ui.Ui;

/**
 * Represents an invalid command entered by the user.
 * This command is executed when the user inputs an unrecognized command.
 * It displays an error message to inform the user.
 */
public class InvalidCommand extends Command {

    /**
     * Executes the {@code InvalidCommand} by displaying an error message.
     *
     * @param tasks   The {@link TaskList} (not used in this command).
     * @param ui      The {@link Ui} instance for displaying the error message.
     * @param storage The {@link Storage} (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String invalidString = "Whot on earth are you saying!!!";
        ui.showMessage(invalidString);
    }
}
