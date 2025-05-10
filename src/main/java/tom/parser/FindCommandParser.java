package tom.parser;

import tom.command.Command;
import tom.command.FindCommand;
import tom.ui.Ui;

/**
 * Parser to ingest and parse Find command.
 */
public class FindCommandParser extends CommandParser {

    /**
     * Constructs a FindCommandParser with the specified UI.
     *
     * @param ui The UI for interacting with the user.
     */
    public FindCommandParser(Ui ui) {
        super("find", ui);
        addPattern("(\\w+(?: +\\w+)*)");
    }

    /**
     * Creates a FindCommand with the specified keyword.
     *
     * @return The created FindCommand.
     */
    @Override
    protected Command createCommand() {
        String keyword = inputs.poll();
        return new FindCommand(keyword);
    }
}
