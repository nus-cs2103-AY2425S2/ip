package kyrie;

/**
 * Represents a command to exit the program.
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Constructor for ByeCommand.
     */
    public ByeCommand() {
        super();
    }

    /**
     * Returns true as this is an exit command.
     * 
     * @return True.
     */
    @Override
    public Boolean isExit() {
        return true;
    }

    /**
     * Executes the command to exit the program.
     * 
     * @param storage The storage object to save the task list to.
     * @param ui The ui object to show the user the goodbye message.
     * @param tasks The task list to add the task to.
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList tasks) {
        ui.showGoodbye();
    }
}