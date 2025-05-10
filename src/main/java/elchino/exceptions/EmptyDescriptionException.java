package elchino.exceptions;

/**
 * Represents an exception thrown when the description of a task is empty.
 */
public class EmptyDescriptionException extends ElchinoException {
    /**
     * Constructor for EmptyDescriptionException.
     */
    public EmptyDescriptionException() {
        super("Descripción vacía: Por favor ingresa una descripción válida.");
    }
}