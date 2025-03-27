package qwerty.commands;

import qwerty.exceptions.BotException;
import qwerty.ui.Storage;
import qwerty.ui.TaskList;
import qwerty.ui.Ui;

/**
 * Represents a command that can be executed by the bot.
 * This class serves as a base class for various specific bot commands.
 */
public class BotCommand {

    /**
     * Checks if the command corresponds to a "bye" command.
     *
     * @return false, as this is a base class and does not represent a "bye" command.
     */
    public boolean isBye() {
        return false;
    }

    /**
     * Executes the command. The default implementation does nothing, but it can be overridden
     * by subclasses to provide specific functionality.
     *
     * @param tasks The TaskList to interact with.
     * @param ui The Ui instance for displaying messages.
     * @param storage The Storage instance for handling file operations.
     * @throws BotException If an error occurs during command execution.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
    }
}
