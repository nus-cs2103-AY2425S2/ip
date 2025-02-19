package exceptions;

/**
 * Concrete class that is thrown when the description of a command that requires it is empty
 */
public class EmptyDescException extends ThoughtBotException {
    /**
     * Constructor for EmptyDescException class
     * @param correctFormat Correct format of the command given
     */
    public EmptyDescException(String correctFormat) {
        super("The description is empty!\n"
                + "The correct format is here.\n"
                + "Format: " + correctFormat);
    }
}
