package tyler.command;

import tyler.storage.Storage;
import tyler.task.Task;
import tyler.task.list.TaskList;
import tyler.ui.Ui;

/**
 * Represents a command to delete an element at the specified index.
 */
public class DeleteCommand extends Command {
    private final String[] tokens;

    public DeleteCommand(String[] tokens) {
        super();
        this.tokens = tokens;
    }

    /**
     * Removes the task at the specified index. Returns the list with the task deleted.
     *
     * @param tasks The list of tasks which has an object needing to be deleted.
     * @param ui The UI object for any required printing.
     * @param storage The storage for I/O involving storing the tasks on the disk (unused).
     * @return The list with the task now deleted.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        int index;
        Task deletedTask = null;
        try {
            index = Integer.parseInt(tokens[1]) - 1;
            assert index >= 0 && index < tasks.size();
            deletedTask = tasks.remove(index);
            ui.showMessage("\t I've removed this task: \n" + "\t " + deletedTask
                    + "\t There are " + tasks.size() + " tasks in the list.");
        } catch (NumberFormatException e) {
            ui.showMessage("\t !!Please enter a number as the argument!!");
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showMessage("\t !!Please provide the correct number of arguments!!");
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage("\t !!There aren't this many tasks in the list!!");
        }
        return tasks;
    }
}
