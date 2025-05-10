package gigi.commands;

import gigi.storage.Storage;
import gigi.tasks.Tasklist;
import gigi.ui.Ui;

/**
 * Represents a command to display list of command the Gigi chatbot.
 * This command is triggered when the user inputs "help".
 */

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    /**
     * Constructs a HelpCommand.
     */
    public HelpCommand() {}

    /**
     * Executes the help.
     */
    @Override
    public String execute(Tasklist tasks, Ui ui, Storage storage) {
        return ui.showHelpMessage();
    }
}
