package amara.command;

import java.util.ArrayList;

import amara.exceptions.AmaraException;
import amara.storage.Storage;
import amara.task.Task;
import amara.ui.Ui;

/**
 * An abstract class representing a contract for various
 * {@link Command} implementations that {@link Amara} can execute.
 * <p>
 * Commands interact with the {@link Ui} to display results and may modify the
 * given {@code ArrayList<Task>}. Specific behaviors are defined in concrete subclasses.
 * </p>
 */
public abstract class Command {
    public boolean isBye() {
        return false;
    }

    public abstract String execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws AmaraException;
}
