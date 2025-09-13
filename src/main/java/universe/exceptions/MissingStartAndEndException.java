package universe.exceptions;

/**
 * Represents an exception that is thrown when user inputs an event command,
 * but without either a start date/time, an end date/time or both.
 *
 * @author yuqing-tham
 */
public class MissingStartAndEndException extends UniverseException {
    /**
     * Constructs a new MissingStartAndEndException with the specified detail message.
     */
    public MissingStartAndEndException() {
        super("An event must have a start and end time!");
    }
}
