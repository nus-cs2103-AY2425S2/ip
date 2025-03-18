package clank.command;

import clank.exception.ClankException;
import clank.task.TaskList;
import clank.utility.Storage;
import clank.utility.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;
    private boolean toDeleteAll = false;

    /**
     * Constructs a {@code DeleteCommand} from the user's input.
     * Parses whether a specific task should be deleted or if all tasks should be removed.
     *
     * @param input The raw input string from the user.
     * @throws ClankException If the input does not match the expected format.
     */
    public DeleteCommand(String input) throws ClankException {
        try {
            String[] parts = input.split(" ");
            if (parts.length != 2) {
                throw new ClankException(ClankException.ErrorType.INVALID_FORMAT,
                        "delete <index> or delete all");
            }
            if (parts[1].toLowerCase().equals("all")) {
                toDeleteAll = true;
            }
            this.index = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Oh no! That's not a number!");
        }
    }

    /**
     * Executes the delete command by removing a task or clearing all tasks from the task list.
     *
     * @param taskList The task list to modify.
     * @param ui The UI instance to interact with the user.
     * @param storage The storage system to save the updated task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList != null : "TaskList should not be null";
        assert storage != null : "Storage should not be null.";

        try {
            assert index >= 0 && index < taskList.getTasks().size() : "Index out of bounds in delete.";
            taskList.deleteTask(index, toDeleteAll);
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please specify a valid index!");
        }
    }
}
