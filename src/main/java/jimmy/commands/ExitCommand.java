package jimmy.commands;

import jimmy.JimmyException;
import jimmy.Storage;
import jimmy.Ui;
import jimmy.tasks.TaskList;

/**
 * The {@code ExitCommand} class represents a command to terminate the application.
 * When executed, it displays a goodbye message to the user and signals the program to exit.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an {@code ExitCommand}.
     * 
     * @param arguments the keyword used to check if the exit command is valid or not.
     * @throws JimmyException
    */
    public ExitCommand(String arguments) throws JimmyException {
        super();
        if (!arguments.isEmpty()) {
            throw new JimmyException("Bye command must not have additional arguments.");
        }
    }

    /**
     * Executes the exit command by displaying a goodbye message to the user.
     *
     * @param tasks   the {@code TaskList} containing all current tasks (not used in this command).
     * @param ui      the {@code Ui} instance for displaying messages to the user.
     * @param storage the {@code Storage} instance (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "";
    }

    /**
     * Indicates whether this command should terminate the program.
     *
     * @return {@code true} to signal the application to exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
