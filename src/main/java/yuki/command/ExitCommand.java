package yuki.command;

import yuki.Storage;
import yuki.TaskList;
import yuki.Ui;
import yuki.YukiException;
import yuki.task.Task;

/**
 * Represents a command to handle invalid commands.
 */
public class ExitCommand extends Command {

    public ExitCommand(String[] commands, String description, boolean isExit) {
        super(commands, description, isExit);
    }

    /**
     * Executes the command to handle invalid commands.
     * @param tasks TaskList containing the tasks.
     * @param ui Ui object to interact with the user.
     * @param storage Storage object to save the tasks.
     * @return The output of the command.
     * @throws YukiException if the task number does not exist or is not a number.
     */
    @Override
    public String execute(TaskList<Task> tasks, Ui ui, Storage storage) throws YukiException {
        Command.lastCommand = this;
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Undo the command.
     * @param tasks TaskList containing the tasks.
     * @return The output of the command.
     * @throws YukiException if the task number does not exist or is not a number.
     */
    @Override
    public String undo(TaskList<Task> tasks) throws YukiException {
        return "Yuki is exiting...";
    }
}
