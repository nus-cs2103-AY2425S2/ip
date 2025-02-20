package amara.command;

import java.util.ArrayList;

import amara.exceptions.AmaraException;
import amara.storage.Storage;
import amara.task.Task;
import amara.ui.Ui;

/**
 * A {@link Command} that signals the termination of the program.
 * <p>
 * Executes the necessary operations to exit, including
 * saving the task list and triggering the exit sequence.
 * </p>
 */
public class ByeCommand extends Command {
    private static final String MESSAGE = "Bye. Hope to see you again soon! <3";

    @Override
    public boolean isBye() {
        return true;
    }

    /**
     * Saves all {@link Task} from {@code tasks} to a {@code .txt}
     * file for future startup.
     *
     * @param tasks List of tasks.
     * @param ui UI handler.
     * @param storage To store the given List of tasks.
     * @return Messaage generated and passed to the UI handler.
     */
    @Override
    public String execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws AmaraException {
        try {
            ui.display(ByeCommand.MESSAGE);
            storage.saveList(tasks);
            return ByeCommand.MESSAGE;
        } catch (AmaraException e) {
            throw new AmaraException("Unable to save task list :(");
        }
    }
}
