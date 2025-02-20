package command;

import core.TaskList;
import ui.Ui;
import storage.Storage;
import exception.BaimiException;
import task.Task;
import task.Todo;

/**
 * Represents a command to add a todo task to the task list.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command, exiting the program.
     *
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage handler.
     * @return The response to the user command.
     * @throws BaimiException If an error occurs during the execution of the command.
     */
    @Override
    public String executeAndGetResponse(TaskList tasks, Ui ui, Storage storage) throws BaimiException {
        return "Bye baby. Hope to see you again soon!";
    }


    /**
     * Returns true if the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    @Override
    public boolean isExit() {
        return true;  // This command exits the program
    }
}
