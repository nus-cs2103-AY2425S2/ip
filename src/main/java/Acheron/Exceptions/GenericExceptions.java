package Acheron.Exceptions;

/**
 * This exception is thrown if an input is wrongly formatted or incomplete
 * when trying to create a deadline task
 */
public class GenericExceptions extends Exception {

    /**
     * Overrides the toString() method so a custom error message is printed out if needed
     * @return Custom string message
     */
    @Override
    public String toString() {
        return "Something went wrong. Perhaps your command is wrong?";
    }
}

