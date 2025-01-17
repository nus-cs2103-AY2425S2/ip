package tasker.command;

/**
 * Parses the user input
 */
public class Parser {
    /**
     * Creates the command to handle the user input.
     *
     * @param command The input of the user
     * @return The command to handle the user input
     */
    public static Command parse(String command) {
        String[] cmdParts = command.split(" ");

        switch (cmdParts[0]) {
            case "list":
                return new ListCommand();

            default:
                return new AddCommand(command);
        }
    }
}
