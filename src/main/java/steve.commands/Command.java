package steve.commands;
import steve.exceptions.InvalidTaskNumberException;

/**
 * Represents a Command that can be executed.
 * This interface is implemented by various command classes that perform specific
 * tasks, such as managing tasks, marking/unmarking tasks, etc. Each command
 * must define its own execution behavior in the {@link #execute()} method.
 */
public interface Command {
    String execute() throws InvalidTaskNumberException, NumberFormatException;
}
