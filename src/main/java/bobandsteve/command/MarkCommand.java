package bobandsteve.command;

import bobandsteve.exception.BobAndSteveException;
import bobandsteve.exception.InvalidCommandFormatException;
import bobandsteve.storage.Storage;
import bobandsteve.tasklist.TaskList;
import bobandsteve.ui.Ui;

/**
 * Command to mark a task as completed.
 * This command marks the task at a specified position in the task list as completed.
 */
public class MarkCommand extends Command {

    private int pos = 0;

    /**
     * Constructs a MarkCommand object based on the input provided by the user.
     * The input must specify the position of the task to mark as completed.
     *
     * @param input The input string containing the task position.
     * @throws InvalidCommandFormatException If the input format is invalid or if the position is not a valid integer.
     */
    public MarkCommand(String input) throws InvalidCommandFormatException {
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
        this.response = taskList.mark(pos);
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


