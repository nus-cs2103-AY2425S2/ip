package softess;


public abstract class Command {

    /** The user interface associated with this command. */

    protected UserInterface ui;

    /**
     * Constructs a new command object with the given user interface.
     *
     * @param ui the user interface instance to associate with the command
     */
    public Command(UserInterface ui) {
        this.ui = ui;
    }

    /**
     * Executes the action defined by this command.
     */
    public abstract String trigger();
}
