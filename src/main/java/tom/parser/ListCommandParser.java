package tom.parser;

import tom.command.Command;
import tom.command.ListCommand;
import tom.ui.Ui;

/**
 * Parser to ingest and parse List command.
 */
public class ListCommandParser extends CommandParser {

    /**
     * Constructs a ListCommandParser with the specified UI.
     *
     * @param ui The UI for interacting with the user.
     */
    public ListCommandParser(Ui ui) {
        super("list", ui);
    }

    /**
     * Creates a ListCommand.
     *
     * @return The created ListCommand.
     */
    @Override
    protected Command createCommand() {
        return new ListCommand();
    }
}
