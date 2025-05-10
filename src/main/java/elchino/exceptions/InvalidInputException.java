package elchino.exceptions;

/**
 * Exception for handling invalid input.
 */
public class InvalidInputException extends ElchinoException {

    /**
     * Constructor for InvalidInputException.
     */
    public InvalidInputException() {
        super("Entrada inválida: Por favor ingresa un número válido.");
    }

    /**
     * Constructor for InvalidInputException with a specific task number.
     * @param taskNumber The task number that is invalid.
     */
    public InvalidInputException(int taskNumber) {
        super("Lo siento, no puedo encontrar la tarea " + taskNumber + ".");
    }

    /**
     * Constructor for InvalidInputException with a specific message.
     * @param message The message to be displayed when the exception is thrown.
     */
    public InvalidInputException(String message) {
        super("Entrada inválida: " + message);
    }
}
