package tom.parser;

import tom.command.Command;
import tom.command.LoadCommand;
import tom.ui.Ui;

/**
 * Parser to ingest and parse Load command.
 */
public class LoadCommandParser extends CommandParser {

    /**
     * Constructs a LoadCommandParser with the specified UI.
     *
     * @param ui The UI for interacting with the user.
     */
    public LoadCommandParser(Ui ui) {
        super("Load", ui);
    }

    /**
     * Creates a LoadCommand with the specified index.
     *
     * @return The created LoadCommand.
     */
    @Override
    protected Command createCommand() {
        return new LoadCommand();
    }
}
