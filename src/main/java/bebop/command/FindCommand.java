package bebop.command;

import bebop.exception.BebopException;
import bebop.task.TaskList;
import bebop.ui.Storage;
import bebop.ui.Ui;

/**
 * Finds commands with matching string.
 */

public class FindCommand extends Command {
    private final String command;

    public FindCommand(String command) {
        this.command = command;
    }

    /**
     * Find tasks with matching names.
     *
     * @param tasks Tasklist storing tasks.
     * @param ui Ui to print commands.
     * @param storage stores task into Bebop.txt.
     *
     * @return string if the program will continue or not.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] input = command.split(" ");
            assert input.length > 0;
            String output = "";
            if (input.length != 2) {
                throw new BebopException("Invalid find command, needs a subject to find");
            }
            output = tasks.findTask(input[1]);
            return output;
        } catch (BebopException e) {
            return e.getMessage();
        }
    }
}
