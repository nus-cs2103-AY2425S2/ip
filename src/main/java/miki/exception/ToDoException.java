package miki.exception;

/**
 * Represents an exception that occurs when a todo task fails.
 */
public class ToDoException extends TaskException {
    public ToDoException() {
        super();
    }

    /**
     * Constructor for ToDoException.
     *
     * @param message The error message.
     */
    public ToDoException(String message) {
        super(message
                + "Try the following format:\n"
                + "Todo     : Todo {TASK DESCRIPTION}");
    }
}
