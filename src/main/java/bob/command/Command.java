package bob.command;

/**
 * This class represents a command. It is an abstract class that
 * contains an abstract method {@code execute()} that is implemented
 * by its <i>many</i> subclasses.
 */
public abstract class Command {

    /**
     * Executes the command and returns the output.
     *
     * @return String output.
     */
    public abstract String execute() throws Exception;
}
