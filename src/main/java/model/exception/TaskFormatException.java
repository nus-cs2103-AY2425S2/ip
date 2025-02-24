package model.exception;

import model.response.MessageGenerator;

/**
 * Represents an exception thrown when a task format is invalid.
 */
public class TaskFormatException extends AliceException {

    /**
     * Constructs a new TaskFormatException
     */
    public TaskFormatException() {
        super();
    }

    @Override
    public String getMessage() {
        return MessageGenerator.getTaskFormatExceptionMessage();
    }
}
