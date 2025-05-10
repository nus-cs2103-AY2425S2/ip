package gigi.commands;

import java.io.IOException;

import gigi.exceptions.GigiException;
import gigi.storage.Storage;
import gigi.tasks.Task;
import gigi.tasks.Tasklist;
import gigi.ui.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 * This command is triggered when the user inputs "mark" followed by a task index.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private final int taskIndex;

    /**
     * Constructs a MarkCommand with the specified task index.
     * The index is adjusted to be zero-based for internal processing.
     *
     * @param i The 1-based task index provided by the user.
     */
    public MarkCommand(int i) {
        this.taskIndex = i - 1;
    }

    /**
     * Executes the command to mark a task as done.
     * If the task index is invalid, an exception is thrown.
     * Otherwise, the task is updated, saved, and a confirmation message is shown.
     *
     * @param tasks   The task list where the task is located.
     * @param ui      The UI component responsible for displaying messages.
     * @param storage The storage component for saving task updates.
     * @throws GigiException If the task index is out of bounds.
     */
    @Override
    public String execute(Tasklist tasks, Ui ui, Storage storage) throws GigiException, IOException {
        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new GigiException("MEOW! You don't have that many tasks.");
        }

        Task markedTask = tasks.getTask(taskIndex);
        tasks.markTaskAsDone(taskIndex);
        tasks.saveTasks(storage);

        return ui.showDoneMessage() + "\n"
                + ui.showMessage(markedTask.toString()) + "\n"
                + ui.showTaskNumber(tasks);
    }
}

