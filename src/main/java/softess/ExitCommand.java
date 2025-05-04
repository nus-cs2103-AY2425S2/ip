package softess;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand with the specified user interface.
     *
     * @param ui The user interface handling interactions.
     */
    public ExitCommand(UserInterface ui) {
        super(ui);
    }

    /**
     * Triggers the command to display a goodbye message and exit the application.
     *
     * @return A string message indicating the application is closing.
     */
    @Override
    public String trigger() {
        return super.ui.showGoodByeMessage();
    }
}
