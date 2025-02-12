package duchess;

/**
 * The {@code DuchessException} class represents a custom exception for handling errors in the Duchess application.
 * It extends the {@code Exception} class and includes an {@code ErrorType} to categorize specific error scenarios.
 */
public class DuchessException extends Exception {
    private final ErrorType errorType;

    /**
     * Constructs a {@code DuchessException} with the specified error message.
     * The {@code errorType} is set to {@code ErrorType.UNKNOWN_ERROR} by default.
     *
     * @param s The detail message explaining the exception.
     */
    public DuchessException(String s) {
        super(s);
        this.errorType = ErrorType.UNKNOWN_ERROR;
    }

    /**
     * Constructs a {@code DuchessException} with the specified error message and error type.
     *
     * @param s         The detail message explaining the exception.
     * @param errorType The specific type of error, represented as an {@code ErrorType}.
     */
    public DuchessException(String s, ErrorType errorType) {
        super(s);
        this.errorType = errorType;
    }

    /**
     * Returns a detailed error message based on the {@code errorType}.
     * Custom messages are returned for known error types, while a default message
     * is returned for {@code ErrorType.UNKNOWN_ERROR}.
     *
     * @return The detailed error message.
     */
    @Override
    public String getMessage() {
        String s;
        switch (this.errorType) {
        case INVALID_INDEX:
            s = "Looks like the index given can't be found...";
            break;
        case INVALID_COMMAND:
            s = "What are you blabbering about you baboon.";
            break;
        case INVALID_FORMAT:
            s = "You can do better than that... format your command properly...";
            break;
        case INVALID_FORMAT_TODO:
            s = "You can do better than that... follow this format will ya?\n"
                    + "todo <task>";
            break;
        case INVALID_FORMAT_DEADLINE:
            s = "You can do better than that... follow this format will ya?\n"
                    + "deadline <task> /by <yyyy-MM-dd HHmm>";
            break;
        case INVALID_FORMAT_EVENT:
            s = "You can do better than that... follow this format will ya?\n"
                    + "event <task> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>";
            break;
        default:
            s = "We got an unknown error here...\n" + super.getMessage();
            break;
        }
        return s;
    }
}
