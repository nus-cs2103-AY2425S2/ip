package botato.exception;

/**
 * This exception is thrown when trying to set the status of a class to the same one as it already has.
 */
public class SameStatusException extends BotatoException {
    public SameStatusException(boolean status) {
        super(status ? "Dummy, you've already done this task!" : "Yes, you haven't done this task yet...");
    }
}
