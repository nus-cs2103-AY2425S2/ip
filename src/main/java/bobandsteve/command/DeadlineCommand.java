package bobandsteve.command;

import bobandsteve.exception.BobAndSteveException;
import bobandsteve.exception.InvalidCommandFormatException;
import bobandsteve.storage.Storage;
import bobandsteve.task.Deadline;
import bobandsteve.tasklist.TaskList;
import bobandsteve.ui.Ui;

/**
 * Command to create a new deadline task with a description and deadline.
 * This command parses the user input to extract the task description and
 * deadline date, then creates a Deadline task and adds it to the task list.
 */
public class DeadlineCommand extends Command {

    private final String description;
    private final String by;

    /**
     * Constructs a new DeadlineCommand by parsing the user input.
     * The input is expected to follow the format: "task /by deadline".
     * If the format is invalid, an exception is thrown.
     *
     * @param input The user input string to be parsed.
     * @throws InvalidCommandFormatException If the input format is incorrect.
     */
    public DeadlineCommand(String input) throws InvalidCommandFormatException {
        try {
            String[] split = input.split(" ", 2);
            if (split[1].isEmpty()) {
                throw new InvalidCommandFormatException("Invalid format. Use: <task> /by <deadline>");
            }
            String[] by = split[1].split("/by", 2);
            if (by.length < 2 || by[0].trim().isEmpty() || by[1].trim().isEmpty()) {
                throw new InvalidCommandFormatException("Invalid format. Use: <task> /by <deadline>");
            }
            this.description = by[0].trim();
            this.by = by[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException("You must specify the task and deadline in the format: "
                    + "<task> /by <deadline>");
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BobAndSteveException {
        Deadline deadline = new Deadline(description, "[ ]", by);
        this.response = taskList.addTask(deadline);
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
