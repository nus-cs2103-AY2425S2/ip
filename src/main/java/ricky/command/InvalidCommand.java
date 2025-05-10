package ricky.command;

import ricky.Storage;
import ricky.Ui;
import ricky.task.TaskList;

/**
 * Represents a command for an invalid input.
 */
public class InvalidCommand extends Command {

    /**
     * Executes the invalid command, printing an invalid command message.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The UI to print the invalid command message.
     * @param storage The storage (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printInvalidCommand();
    }
}
