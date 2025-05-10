package tom.parser;

import tom.command.Command;
import tom.command.EventCommand;
import tom.exception.TomParseException;
import tom.ui.Ui;

/**
 * Parser to ingest and parse Event command.
 */
public class EventCommandParser extends CommandParser {

    // regex for YYYY-MM-DD
    private static final String DATE_PATTERN = "\\d{4}\\-(?:0?[1-9]|1[012])\\-(?:0[1-9]|[12][0-9]|3[01])";

    /**
     * Constructs an EventCommandParser with the specified UI.
     *
     * @param ui The UI for interacting with the user.
     */
    public EventCommandParser(Ui ui) {
        super("event", ui);
        addPattern("(\\w+(?: +\\w+)*)?");
        addPattern(DATE_PATTERN);
        addPattern(DATE_PATTERN);
    }

    /**
     * Parses the next input string and matches it against the current pattern.
     * Attempts to split input into description and date.
     * 
     * @param input The input string to be parsed.
     * @throws TomParseException If the input does not match the current pattern.
     */
    @Override
    public void parseNext(String input) throws TomParseException {
        String[] parts = input.split(" /from | /to ");
        for (String part : parts) {
            super.parseNext(part.trim());
        }
    }

    /**
     * Creates an EventCommand with the specified description, start date, and end date.
     *
     * @return The created EventCommand.
     */
    @Override
    protected Command createCommand() {
        String description = inputs.poll();
        String startDate = inputs.poll();
        String endDate = inputs.poll();
        return new EventCommand(description, startDate, endDate);
    }
}
