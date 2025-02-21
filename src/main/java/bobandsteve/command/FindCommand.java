package bobandsteve.command;

import bobandsteve.exception.BobAndSteveException;
import bobandsteve.exception.InvalidCommandFormatException;
import bobandsteve.storage.Storage;
import bobandsteve.tasklist.TaskList;
import bobandsteve.ui.Ui;

/**
 * Command to find a task as based on input.
 * This command finds the task related to the input and return to the user as a string.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructs a FindCommand object based on the input provided by the user.
     * The input must specify the keyword to search for their tasks.
     *
     * @param input The input string containing the task position.
     * @throws InvalidCommandFormatException If the input format is invalid or if the position is not a valid integer.
     */
    public FindCommand(String input) throws InvalidCommandFormatException {
        String[] split = input.split(" ", 2);
        if (split.length < 2 || split[1].trim().isEmpty()) {
            throw new InvalidCommandFormatException("You must specify the keyword to search.");
        }
        this.keyword = split[1];
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BobAndSteveException {
        this.response = taskList.find(keyword);
        ui.printOutput(response);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getString() {
        return response;
    }
}


