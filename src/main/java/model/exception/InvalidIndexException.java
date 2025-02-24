package model.exception;

import model.response.MessageGenerator;

/**
 * Represents an exception thrown when an invalid index is provided for mark,
 * unmark, delete etc commands
 */
public class InvalidIndexException extends AliceException {

    /**
     * Constructs a new InvalidIndexException
     */
    public InvalidIndexException() {
        super();
    }

    @Override
    public String getMessage() {
        return MessageGenerator.getInvalidIndexExceptionMessage();
    }
}
