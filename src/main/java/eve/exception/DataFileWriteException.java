package eve.exception;

/**
 * Represents an exception caused by error when writing to the data file.
 * It contains a custom error message to be displayed on the user interface.
 */
public class DataFileWriteException extends EveException {
    public DataFileWriteException() {
        super("Something went wrong when trying to write data file");
    }
}
