package jimmy.commands;

import jimmy.JimmyException;
import jimmy.Storage;
import jimmy.Ui;
import jimmy.tasks.TaskList;

/**
 * The {@code ListCommand} class represents a command to display all tasks
 * in the task list. It provides feedback to the user via the UI, showing either
 * the list of tasks or a message indicating that the task list is empty.
 */
public class ListCommand extends Command {

    /**
     * Constructs a {@code ListCommand}.
     *
     * @param arguments the keyword used to check if the list command is valid or not.
     * @throws JimmyException
     */
    public ListCommand(String arguments) throws JimmyException {
        if (!arguments.isEmpty()) {
            throw new JimmyException("List command must not have additional arguments.");
        }
    }

    /**
     * Executes the list command by displaying all tasks in the task list.
     * If the list is empty, it notifies the user accordingly.
     *
     * @param tasks   the {@code TaskList} containing all current tasks.
     * @param ui      the {@code Ui} instance for displaying messages to the user.
     * @param storage the {@code Storage} instance (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = "";
        if (tasks.size() == 0) {
            output += "Your task list is empty.";
        } else {
            output += "Here are the tasks in your list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                output += (i + 1) + ". " + tasks.getTask(i) + "\n";
            }
        }
        ui.showMessage(output);
        return output;
    }

    /**
     * Indicates whether this command should terminate the program.
     *
     * @return {@code false} as the list command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
