package Acheron.Exceptions;

/**
 * This exception is thrown if the saved file is corruped
 */
public class CorruptedFileException extends Exception {

    /**
     * Overrides the toString() method so a custom error message is printed out if needed
     * @return Custom string message
     */
    @Override
    public String toString() {
        return "Corrupted file. Cannot read data";
    }
}
