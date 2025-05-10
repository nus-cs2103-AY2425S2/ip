package tom.parser;

import tom.command.Command;
import tom.command.DeleteCommand;
import tom.ui.Ui;

/**
 * Parser to ingest and parse Delete command.
 */
public class DeleteCommandParser extends CommandParser {

    /**
     * Constructs a DeleteCommandParser with the specified UI.
     *
     * @param ui The UI for interacting with the user.
     */
    public DeleteCommandParser(Ui ui) {
        super("delete", ui);
        addPattern("(\\d+)");
    }

    /**
     * Creates a DeleteCommand with the specified index.
     *
     * @return The created DeleteCommand.
     */
    @Override
    protected Command createCommand() {
        int index = Integer.parseInt(inputs.poll());
        return new DeleteCommand(index);
    }
}
