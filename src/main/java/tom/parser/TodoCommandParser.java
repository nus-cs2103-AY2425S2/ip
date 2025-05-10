package tom.parser;

import tom.command.Command;
import tom.command.TodoCommand;
import tom.ui.Ui;

/**
 * Parser to ingest and parse Todo command.
 */
public class TodoCommandParser extends CommandParser {

    /**
     * Constructs a TodoCommandParser with the specified UI.
     *
     * @param ui The UI for interacting with the user.
     */
    public TodoCommandParser(Ui ui) {
        super("todo", ui);
        addPattern("(\\w+(?: +\\w+)*)?");
    }

    @Override
    protected Command createCommand() {
        return new TodoCommand(inputs.poll());
    }
}
