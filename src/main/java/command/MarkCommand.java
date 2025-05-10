package command;

import java.util.ArrayList;

import exceptions.ElmachoException;
import task.Task;
import task.Tasklist;
import ui.Ui;

/**
 * Represents a command to mark a task as completed and print the marked confirmation message to the UI.
 */
public class MarkCommand extends Command {

    private final int index;

    /**
     * Constructs a MarkCommand with the index of the task to be marked.
     * @param index The index of the task in the tasklist to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command: adds a "X" to the [ ] next to the task description
     *                           and displays the marked task on the UI.
     * @param tasklist The tasklist that contains the task to be marked.
     * @param ui The UI used to print the marked message.
     */
    public void execute(Tasklist tasklist, Tasklist archivedTasklist, Ui ui) throws ElmachoException {
        assert index >= 1 : "Index must be a positive integer.";

        if (index <= 0 || index > tasklist.getNumberOfTasks()) {
            throw new ElmachoException("Invalid task number. Change it.");
        }

        tasklist.mark(index - 1);
        ArrayList<Task> tasks = tasklist.getTasks();
        Task task = tasks.get(index - 1);

        assert task != null : "Task should not be null.";
        ui.printMarked(task);
    }
}
