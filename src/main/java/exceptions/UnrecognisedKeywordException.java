package exceptions;

/**
 * Concrete class that is thrown when the keywords in the command of the user are missing or
 * formatted wrongly
 */
public class UnrecognisedKeywordException extends ThoughtBotException {
    /**
     * Constructor for UnrecognisedKeywordException class
     * @param correctFormat Correct format of the command given
     */
    public UnrecognisedKeywordException(String correctFormat) {
        super("A keyword has been typed wrongly or omitted!\n"
                + "The correct format is here.\n"
                + "Format: " + correctFormat);
    }
}
