package miki.exception;

/**
 * Represents an exception that occurs when an event task is not formatted
 * correctly.
 */
public class EventException extends TaskException {
    public EventException() {
        super();
    }

    /**
     * Constructor for EventException.
     *
     * @param message The error message.
     */
    public EventException(String message) {
        super(message
                + "Try the following format:\n"
                + "Event    : Event {TASK DESCRIPTION} /from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM\n");
    }
}
