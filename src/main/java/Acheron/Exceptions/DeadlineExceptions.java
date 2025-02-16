package Acheron.Exceptions;

/**
 * This exception is thrown if an input is wrongly formatted or incomplete
 * when trying to create a deadline task
 */
public class DeadlineExceptions extends GenericExceptions {

    /**
     * Overrides the toString() method so a custom error message is printed out if needes
     * @return Custom string message
     */
    @Override
    public String toString() {
        return "Check your deadline input.\n"
                + "Make sure the text follows the format\n"
                + "deadline [Some text] /by [sometext]\n"
                + "(Note that the [ ] is not needed! E.g deadline guide souls /by Tuesday";
    }
}
