package tom.exception;

import java.util.regex.Pattern;

/**
 * Exception thrown when an input is invalid for a given regex.
 */
public class InvalidRegexException extends TomParseException {

    /**
     * Constructs an InvalidRegexException with the specified detail message.
     *
     * @param pattern The regex pattern that was expected.
     * @parama format The format of the expected input.
     * @param input The input that was received.
     */
    public InvalidRegexException(Pattern pattern, String format, String input) {
        super(String.format(
            """
            Invalid input format %s expected.
            %s received instead.
            """,
            format, input
        ));
    }

    @Override
    public boolean needPrompt() {
        return true;
    }
}
