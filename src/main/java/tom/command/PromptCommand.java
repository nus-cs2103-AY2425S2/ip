package tom.command;

import java.util.function.Consumer;

import tom.exception.TomParseException;
import tom.storage.Storage;
import tom.tasklist.TaskList;
import tom.ui.Ui;

/**
 * Represents a command that prompts for additional input for a given parser.
 */
public class PromptCommand extends Command {

    private final String promptMsg;
    private Consumer<String> handler;

    /**
     * Constructs a PromptCommand with a specific prompt and callback.
     *
     * @param promptMsg The message to prompt user with.
     * @param handler The callback function upon receiving input.
     */
    public PromptCommand(TomParseException exception, Consumer<String> handler) {
        this.promptMsg = exception.getMessage();
        if (exception.needPrompt()) {
            this.handler = handler;
        }
    }

    /**
     * Executes the wait command, which does nothing until input is received.
     *
     * @param tasks   The task list.
     * @param ui      The UI for interacting with the user.
     * @param storage The storage for saving tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.promptForInput(id, promptMsg, handler);
    }

    /**
     * Indicates whether this command exits the application.
     *
     * @return false as this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
