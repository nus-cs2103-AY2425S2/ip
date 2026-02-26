package carolyn;

public class Command {
    protected String type;
    protected Object[] args;

    /**
     * Constructs a Command instance with a specified type and arguments.
     *
     * @param type The type of command. Must be a non-empty string.
     * @param args The arguments associated with the command.
     */
    public Command(String type, Object[] args) {
        this.type = type;
        this.args = args;
    }

    /**
     * Retrieves the type of this command.
     * Ensures that the command type is not an empty string.
     *
     * @return The type of the command.
     */
    public String getType() {
        assert this.type.length() > 0 : "For every command, type should be specified";
        return this.type;
    }

    /**
     * Retrieves the arguments associated with this command.
     *
     * @return An array of arguments for the command.
     */
    public Object[] getArgs() {
        return this.args;
    }
}