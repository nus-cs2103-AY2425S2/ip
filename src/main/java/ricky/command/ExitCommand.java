/**
 * Represents a command to exit the application.
 */
package ricky.command;

import ricky.Storage;
import ricky.task.TaskList;
import ricky.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command, saving tasks, printing goodbye message, and exiting the application.
     *
     * @param tasks   The task list to save.
     * @param ui      The UI to print the goodbye message.
     * @param storage The storage to save the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Goodbye! Have a nice day!";
    }
}
