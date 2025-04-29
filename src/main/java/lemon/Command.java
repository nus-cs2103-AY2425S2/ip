/**
 * Represents a command for the chatbot, consisting of a command type and an optional argument
 * The command type indicates the operation to be formed and
 * the argument (if provided) specifies which task the operation performs on
 */

package lemon;

public class Command {
    private final CommandType type;
    private final String argument;

    /**
     * Constructs a Command with a specified type and no argument.
     *
     * @param type The type of the command.
     */
    public Command(CommandType type) {
        this.type = type;
        this.argument = null;
    }

    /**
     * Constructs a Command with a specified type and an argument.
     *
     * @param type The type of the command.
     * @param argument The argument for the command. May be null if not required.
     */
    public Command(CommandType type, String argument) {
        this.type = type;
        this.argument = argument;
    }

    /**
     * Returns the type of the command.
     *
     * @return The type of the command.
     */
    public CommandType getType() {
        return type;
    }

    /**
     * Returns the argument associated with the command, or null if no argument was provided.
     *
     * @return The argument for the command, or null if not applicable.
     */
    public String getArgument() {
        return argument;
    }

}
