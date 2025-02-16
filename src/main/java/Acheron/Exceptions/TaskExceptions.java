package Acheron.Exceptions;

/**
 * This exception is thrown if attempting to create a Task object
 * with wrongly formatted inputs. This is usually only used for testing
 */
public class TaskExceptions extends GenericExceptions {

    /**
     * Overrides the toString() method so a custom error message is printed out if needed
     * @return Custom string message
     */
    @Override
    public String toString() {
        return "Tasks cannot have null name!";
    }
}
