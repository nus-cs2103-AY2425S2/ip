package tom.parser;

import tom.command.Command;
import tom.command.SaveCommand;
import tom.ui.Ui;

/**
 * Parser to ingest and parse Save command.
 */
public class SaveCommandParser extends CommandParser {

    /**
     * Constructs a SaveCommandParser with the specified UI.
     *
     * @param ui The UI for interacting with the user.
     */
    public SaveCommandParser(Ui ui) {
        super("save", ui);
    }

    /**
     * Creates a SaveCommand.
     *
     * @return The created SaveCommand.
     */
    @Override
    protected Command createCommand() {
        return new SaveCommand();
    }
}
