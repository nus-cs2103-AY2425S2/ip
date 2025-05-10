package tom.parser;

import tom.command.Command;
import tom.command.MarkCommand;
import tom.command.UnmarkCommand;
import tom.ui.Ui;

/**
 * Parser to ingest and parse Mark and Unmark commands.
 */
public class MarkUnmarkCommandParser extends CommandParser {

    private boolean isMark;

    /**
     * Constructs a MarkUnmarkCommandParser with the specified UI and mark flag.
     *
     * @param isMark True if the command is to mark, false if to unmark.
     * @param ui     The UI for interacting with the user.
     */
    public MarkUnmarkCommandParser(boolean isMark, Ui ui) {
        super("mark|unmark", ui);
        this.isMark = isMark;
        addPattern("(\\d+)");
    }

    /**
     * Creates a MarkCommand or UnmarkCommand based on the mark flag.
     *
     * @return The created MarkCommand or UnmarkCommand.
     */
    @Override
    protected Command createCommand() {
        int index = Integer.parseInt(inputs.poll());
        return isMark ? new MarkCommand(index) : new UnmarkCommand(index);
    }
}
