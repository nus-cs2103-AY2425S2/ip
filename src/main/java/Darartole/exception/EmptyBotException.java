package Darartole.exception;

/**
 * Deals with all the possible exception
 */
public class EmptyBotException extends Exception {
    /**
     * Throws exception when the bot receives the empty input 
     * 
     * @param message the message that is printed out when the exception is thrown.
     */
    public EmptyBotException(String message) {
        super(message);
    }
}
