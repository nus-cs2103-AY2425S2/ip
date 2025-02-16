package Acheron.Exceptions;

/**
 * This exception is thrown if an input is wrongly formatted or incomplete
 * when trying to create a to do task
 */
public class ToDoExceptions extends GenericExceptions {

    /**
     * Overrides the toString() method so a custom error message is printed out if needed
     * @return Custom string message
     */
    @Override
    public String toString() {
        return "Check your to do input.\n"
                + "Make sure the text follows the format\n"
                + "todo [Some text]\n"
                + "(Note that the [ ] is not needed! E.g todo eat peach";
    }
}
