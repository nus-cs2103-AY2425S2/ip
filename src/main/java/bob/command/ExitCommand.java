package bob.command;


/**
 * This class represents a command to exit the program.
 */
public class ExitCommand extends Command {

    // Used in bob.cli.Cli
    public static final String EXIT_MESSAGE = "Thank you and goodbye";

    @Override
    public String execute() {
        return "";
    }
}
