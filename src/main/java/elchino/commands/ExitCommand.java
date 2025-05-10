package elchino.commands;

import elchino.storage.Storage;
import elchino.tasks.TaskList;
import elchino.ui.Ui;

/**
 * Command to exit the program
 */
public class ExitCommand extends Command {
    public static final String MESSAGE_EXIT = "Adios! Espero verte pronto!";

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return MESSAGE_EXIT;
    }
}