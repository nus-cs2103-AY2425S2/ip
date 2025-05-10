package laffy.command.exceptions;


/**
 * Represents an exception when there is an error in parsing.
 */
public class LaffyException extends Exception {
    public LaffyException(String message) {
        super("[PARSE-ERROR]: " + message + "\n");
    }
}
