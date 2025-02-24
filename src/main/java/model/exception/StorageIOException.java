package model.exception;

import model.response.MessageGenerator;

/**
 * Represents an exception thrown when there is an I/O error during storage
 * operations.
 */
public class StorageIOException extends AliceException {

    /**
     * Constructs a new StorageIOException
     */
    public StorageIOException() {
        super();
    }

    @Override
    public String getMessage() {
        return MessageGenerator.getStorageIOExceptionMessage();
    }
}
