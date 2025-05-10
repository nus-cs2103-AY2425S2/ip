package tom.parser;

import tom.command.Command;
import tom.exception.UnknownCommandException;
import tom.ui.Ui;

/**
 * Parser to raise errors for Unknown commands.
 */
public class UnknownCommandParser extends CommandParser {

    /**
     * Constructs a SaveCommandParser with the specified UI.
     *
     * @param ui The UI for interacting with the user.
     */
    public UnknownCommandParser(Ui ui) {
        super("-", ui);
    }

    /**
     * Throws errors for Unknown command.
     *
     * @throws UnknownCommandException as command is unknown.
     */
    @Override
    protected Command createCommand() throws UnknownCommandException {
        throw new UnknownCommandException();
    }
}
