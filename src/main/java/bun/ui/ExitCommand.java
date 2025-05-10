package bun.ui;

public class ExitCommand extends Command {

    /**
     * Constructs a new instance of `ExitCommand`.
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Execute the command by displaying the message about ending the program.
     *
     * @param taskList TaskList to be updated by the command (not used).
     * @param ui       Ui to be updated by the command.
     * @param storage  Storage to be updated by the command (not used).
     * @return
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.getEndOfConversation();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
