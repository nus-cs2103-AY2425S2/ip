package exceptions;

/**
 * Abstract parent class of all ThoughtBot exceptions that can be thrown by the program
 */
public class ThoughtBotException extends Exception {
    /**
     * Constructor for ThoughtBotException class
     * @param errorMessage Error message detailing what caused the exception
     */
    public ThoughtBotException(String errorMessage) {
        super(errorMessage);
    }
}
