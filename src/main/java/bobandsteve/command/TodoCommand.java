package bobandsteve.command;

import bobandsteve.exception.BobAndSteveException;
import bobandsteve.exception.InvalidCommandFormatException;
import bobandsteve.storage.Storage;
import bobandsteve.task.Todo;
import bobandsteve.tasklist.TaskList;
import bobandsteve.ui.Ui;

/**
 * Command to add a todo task to the task list.
 * This command adds a new todo task with a specified description to the task list.
 */
public class TodoCommand extends Command {

    private final String description;

    /**
     * Constructs a TodoCommand object based on the input provided by the user.
     * The input must specify the description of the todo task.
     *
     * @param input The input string containing the task description.
     * @throws InvalidCommandFormatException If the input format is invalid or if no task description is provided.
     */
    public TodoCommand(String input) throws InvalidCommandFormatException {
        try {
            String[] split = input.split(" ", 2);
            if (split[1].isEmpty()) {
                throw new InvalidCommandFormatException("You must specify the task in the format: <task>");
            }
            this.description = split[1].trim();
            if (description.isEmpty()) {
                throw new InvalidCommandFormatException("You must specify the task in the format: <task>");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException("You must specify the task in the format: <task>");
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BobAndSteveException {
        Todo todo = new Todo(description, "[ ]");
        this.response = taskList.addTask(todo);
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
