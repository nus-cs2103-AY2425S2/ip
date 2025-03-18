package clank.exception;

/**
 * Represents the base exception class for errors in the Clank chatbot.
 * This exception extends {@code RuntimeException} and allows categorization
 * of errors using an {@code ErrorType} enumeration.
 */
public class ClankException extends RuntimeException {

    /**
     * Enum representing different types of errors that can occur in the Clank chatbot.
     * Each error type contains a predefined message to describe the issue.
     */
    public enum ErrorType {

        /**
         * Represents an error caused by an invalid format in user input.
         * This occurs when the user provides a command in an incorrect format.
         * The message prompts the user to follow the correct format.
         */
        INVALID_FORMAT("Oh no! The format is wrong!\n"
                + "The correct format for this command is: "),

        /**
         * Represents an error caused by an unknown or unrecognized command.
         * This occurs when the chatbot does not understand the user's input.
         * The message suggests that the user try another recognized command.
         */
        UNKNOWN_COMMAND("Hmm, I have not seen this command before!\n"
                + "Perhaps try another command that I understand?"),

        /**
         * Represents an error caused by an unrecognized task type.
         * This occurs when the chatbot encounters a task format it does not support.
         * The message indicates that the task type is not recognized.
         */
        UNKNOWN_TASK_TYPE("I don't quite get what this task does..."),

        /**
         * Represents an error that occurs when the chatbot fails to save tasks to storage.
         * This could be due to issues such as file permissions or unavailable storage.
         * The message prompts the user to check for potential problems.
         */
        FAILED_TO_SAVE("I seem to have encountered problems saving the tasks."
                + "Could you help me check what the problem is?"),

        /**
         * Represents an error that occurs when the chatbot fails to load tasks from storage.
         * This could be due to missing files, file corruption, or read access issues.
         * The message advises the user to check for potential loading problems.
         */
        FAILED_TO_LOAD("I seem to have encountered problems loading the tasks."
                + "Could you help me check what the problem is?");

        private final String message;

        /**
         * Constructs an {@code ErrorType} with a predefined message.
         *
         * @param message The default message associated with the error type.
         */
        ErrorType(String message) {
            this.message = message;
        }

        /**
         * Retrieves the base error message associated with this error type.
         */
        public String getMessage() {
            return message;
        }
    }

    /**
     * Constructs a new {@code ClankException} with a specific error type and additional message.
     * The additional message provides further details about the specific issue encountered.
     *
     * @param errorType The type of error encountered, defined in the {@code ErrorType} enum.
     * @param additionalMessage Additional details about the error, appended to the base error message.
     */
    public ClankException(ErrorType errorType, String additionalMessage) {
        super(errorType.getMessage() + additionalMessage);
    }
}

