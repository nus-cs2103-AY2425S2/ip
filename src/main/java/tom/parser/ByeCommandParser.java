package tom.parser;

import tom.command.ByeCommand;
import tom.command.Command;
import tom.ui.Ui;

/**
 * Parser to ingest and parse Bye command.
 */
public class ByeCommandParser extends CommandParser {

    /**
     * Constructs a ByeCommandParser with the specified UI.
     *
     * @param ui The UI for interacting with the user.
     */
    public ByeCommandParser(Ui ui) {
        super("bye", ui);
    }

    /**
     * Creates a ByeCommand.
     *
     * @return The created ByeCommand.
     */
    @Override
    protected Command createCommand() {
        return new ByeCommand();
    }
}
