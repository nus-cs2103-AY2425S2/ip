package exceptions;

/**
 * Concrete class that is thrown when the command that the user has input is not recognised
 */
public class UnrecognisedCmdException extends ThoughtBotException {
    /**
     * Constructor for UnrecognisedCmdException class
     */
    public UnrecognisedCmdException() {
        super("Sorry, I do not understand the command!\n"
                + "Do ensure the command is valid");
    }
}
