package tom.parser;

import tom.command.HelpCommand;
import tom.command.Command;
import tom.ui.Ui;

/**
 * Parser to ingest and parse Help command.
 */
public class HelpCommandParser extends CommandParser {

    /**
     * Constructs a HelpCommandParser with the specified UI.
     *
     * @param ui The UI for interacting with the user.
     */
    public HelpCommandParser(Ui ui) {
        super("help", ui);
    }

    /**
     * Creates a HelpCommand.
     *
     * @return The created HelpCommand.
     */
    @Override
    protected Command createCommand() {
        return new HelpCommand();
    }
}
