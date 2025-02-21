package bobandsteve.command;

import bobandsteve.exception.BobAndSteveException;
import bobandsteve.exception.InvalidCommandFormatException;
import bobandsteve.storage.Storage;
import bobandsteve.tasklist.TaskList;
import bobandsteve.ui.Ui;

/**
 * Command to delete a task from the task list.
 * The command expects the user to input the task number to delete.
 */
public class DeleteCommand extends Command {

    private int pos = 0;

    /**
     * Constructs a DeleteCommand with the given input.
     * Parses the input to extract the task number and validates its format.
     * If the format is incorrect or the task number is invalid, an exception is thrown.
     *
     * @param input The user input for the delete command in the form "delete task number".
     * @throws InvalidCommandFormatException If the input is invalid or in an incorrect format.
     */
    public DeleteCommand(String input) throws InvalidCommandFormatException {
        String[] split = input.split(" ", 2);
        if (split.length < 2) {
            throw new InvalidCommandFormatException("Deletes a task. Usage: delete <task number>");
        }
        try {
            pos = Integer.parseInt(split[1]);
        } catch (NumberFormatException e) {
            throw new InvalidCommandFormatException("The position must be a valid integer.");
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BobAndSteveException {
        this.response = taskList.deleteTask(pos);
        storage.writeFile(taskList);
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


