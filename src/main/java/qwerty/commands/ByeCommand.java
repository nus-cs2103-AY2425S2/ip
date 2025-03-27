package qwerty.commands;

import qwerty.exceptions.BotException;
import qwerty.ui.Storage;
import qwerty.ui.TaskList;
import qwerty.ui.Ui;

/**
 * Represents a command to terminate the bot operation.
 * This command corresponds to the "bye" action and overrides the
 * `isBye` method to return true.
 */
public class ByeCommand extends BotCommand {

    /**
     * Checks if the command corresponds to a "bye" command.
     * This implementation returns true to indicate the "bye" command.
     *
     * @return true, indicating this is a "bye" command.
     */
    @Override
    public boolean isBye() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        ui.showBye();
    }
}
