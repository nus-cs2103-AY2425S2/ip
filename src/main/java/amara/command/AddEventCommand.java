package amara.command;

import java.util.ArrayList;

import amara.exceptions.AmaraException;
import amara.storage.Storage;
import amara.task.Event;
import amara.task.Task;
import amara.ui.Ui;

/**
 * A {@link Command} implementation that adds a {@link Event} task
 * to a given {@code ArrayList<Task>}.
 * <p>
 * The given {@link Event} task is added to the provided {@code ArrayList<Task>}.
 * </p>
 */
public class AddEventCommand extends Command {
    private static final String MESSAGE = "Got it. I've added this task:\n"
            + "  %s\nNow you have %d tasks in the list.";
    private Event event;

    public AddEventCommand(Event event) {
        this.event = event;
    }

    /**
     * Adds the given {@link Event} task to the list of tasks and
     * generates the {@code String} for the ui.
     *
     * @param tasks List of tasks.
     * @param ui UI handler.
     * @param storage To store the given List of tasks.
     * @return Messaage generated and passed to the UI handler.
     */
    @Override
    public String execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws AmaraException {
        tasks.add(this.event);
        String message = String.format(AddEventCommand.MESSAGE, this.event, tasks.size());
        ui.display(message);
        storage.saveList(tasks);
        return message;
    }
}
