package tom.parser;

import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tom.command.Command;
import tom.exception.IncompleteParseException;
import tom.exception.InvalidRegexException;
import tom.exception.TomParseException;
import tom.ui.Ui;

/**
 * Abstract class representing a command parser.
 * Parses user input and converts it into commands.
 */
public abstract class CommandParser {

    private static int lastId = 1;

    protected Queue<String> inputs = new LinkedList<>();
    protected Queue<Pattern> patterns = new LinkedList<>();
    protected Ui ui;

    private int id;

    /**
     * Constructs a CommandParser with the specified command head and UI.
     *
     * @param commandHead The initial command pattern.
     * @param ui          The UI for interacting with the user.
     */
    public CommandParser(String commandHead, Ui ui) {
        this.ui = ui;
        this.id = lastId++;
    }

    /**
     * Returns the unique ID of this CommandParser.
     *
     * @return The unique ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of this CommandParser.
     * @param id    The new ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Parses the next input string and matches it against the current pattern.
     *
     * @param input The input string to be parsed.
     * @throws InvalidRegexException If the input does not match the current pattern.
     * @throws TomParseException     If no more patterns to match.
     */
    public void parseNext(String input) throws TomParseException {
        if (input == null) {
            return;
        }

        if (patterns.isEmpty()) {
            throw new TomParseException(
                "Unnecessary arguments provided. Command ignored."
            );
        }

        Pattern pattern = patterns.element();
        Matcher matcher = pattern.matcher(input);

        if (!matcher.find()) {
            throw new InvalidRegexException(pattern, "yyyy-MM-dd", input);
        }
        patterns.poll();
        inputs.add(matcher.group());
    }

    /**
     * Adds a new pattern to the queue of patterns.
     *
     * @param regex The regular expression pattern to be added.
     */
    public void addPattern(String regex) {
        patterns.add(Pattern.compile(regex));
    }

    /**
     * Adds a new pattern to the queue of patterns.
     *
     * @param pattern The pattern to be added.
     */
    public void addPattern(Pattern pattern) {
        patterns.add(pattern);
    }

    /**
     * Returns a message indicating the number of patterns left to match.
     *
     * @return The prompt message.
     */
    public String getPromptMsg() {
        return String.format("%d more patterns left to match:", patterns.size());
    }

    /**
     * Produces a command if all patterns have been matched.
     *
     * @return The created command or an InvalidCommand if parsing error.
     * @throws TomParseException If the command creation fails.
     */
    public Command produceCommand() throws TomParseException {
        if (!isComplete()) {
            String errorMsg = String.format("""
                Current input: %s
                Parsed %d out of %d patterns. Please enter: %s
                """, String.join(" ", inputs), inputs.size(), inputs.size() + patterns.size(),
                 patterns.element().pattern());
            throw new IncompleteParseException(errorMsg);
        }
        return createCommand();
    }

    /**
     * Checks if all patterns have been matched.
     *
     * @return True if all patterns have been matched, false otherwise.
     */
    public boolean isComplete() {
        return patterns.isEmpty();
    }

    /**
     * Creates a command based on the matched patterns.
     *
     * @return The created command.
     * @throws TomParseException if the command creation fails.
     */
    protected abstract Command createCommand() throws TomParseException;
}
