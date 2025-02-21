package bobandsteve.command;

import bobandsteve.exception.BobAndSteveException;
import bobandsteve.exception.InvalidCommandFormatException;
import bobandsteve.storage.Storage;
import bobandsteve.tasklist.TaskList;
import bobandsteve.ui.Ui;

/**
 * Command to unmark a completed task in the task list.
 * This command unmarks a task at a specified position as incomplete.
 */
public class UnmarkCommand extends Command {

    private int pos = 0;

    /**
     * Constructs an UnmarkCommand object based on the input provided by the user.
     * The input must specify the position of the task to unmark.
     *
     * @param input The input string containing the task position to unmark.
     * @throws InvalidCommandFormatException If the input format is invalid or if no position is provided.
     */
    public UnmarkCommand(String input) throws InvalidCommandFormatException {
        String[] split = input.split(" ", 2);
        if (split.length < 2) {
            throw new InvalidCommandFormatException("You must specify the position of the task to mark.");
        }
        try {
            pos = Integer.parseInt(split[1]);
        } catch (NumberFormatException e) {
            throw new InvalidCommandFormatException("The position must be a valid integer.");
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BobAndSteveException {
        this.response = taskList.unmark(pos);
        storage.writeFile(taskList);
        ui.printOutput(this.response);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getString() {
        return this.response;
    }
}


