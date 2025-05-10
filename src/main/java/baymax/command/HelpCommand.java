package baymax.command;

import java.io.IOException;

import baymax.Storage;
import baymax.TaskList;

/**
 * Represents the help command that displays usage instructions to the user.
 * It provides different responses based on whether the user explicitly asks for help
 * or enters an invalid command.
 */
public class HelpCommand extends Command {
    private static final String HELP_MESSAGE = "Here's how you can use Baymax:\n"
            + "1. list - View all tasks\n"
            + "2. todo <task> - Add a to-do task\n"
            + "3. deadline <task> /by <yyyy-MM-dd optional: HHmm> - Add a deadline\n"
            + "4. event <task> /from <yyyy-MM-dd optional: HHmm> /to <yyyy-MM-dd optional: HHmm> - Add an event\n"
            + "5. mark <task number> - Mark a task as completed\n"
            + "6. unmark <task number> - Unmark a completed task\n"
            + "7. delete <task number> - Delete a task\n"
            + "8. find <keyword> - Search for tasks containing keyword\n"
            + "9. help - Display this help message\n";

    private static final String ERROR_MESSAGE = "oopsie idk watchu talking\n"
            + "make it make sense heresies thk uu\n" + HELP_MESSAGE;

    private boolean isExplicitHelp;

    /**
     * Constructs a {@code HelpCommand} instance.
     * @param isExplicitHelp Determines if this was triggered by user explicitly typing "help".
     */
    public HelpCommand(boolean isExplicitHelp) {
        this.isExplicitHelp = isExplicitHelp;
    }

    /**
     * Executes the help command, providing relevant information.
     *
     * @param tasks   The task list (not used here).
     * @param storage The storage handler (not used here).
     * @return A help message explaining available commands.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws IOException {
        return isExplicitHelp ? HELP_MESSAGE : ERROR_MESSAGE;
    }
}
