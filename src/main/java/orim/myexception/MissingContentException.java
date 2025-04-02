package orim.myexception;

/**
 * Indicates there is a missing of description when trying to create a new Task.
 */
public class MissingContentException extends MyException{
    public MissingContentException(String errorMessage) {
        super(errorMessage);
    }
}
