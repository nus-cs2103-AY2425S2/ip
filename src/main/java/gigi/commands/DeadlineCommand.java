package gigi.commands;

import java.io.IOException;
import java.time.LocalDateTime;

import gigi.exceptions.GigiException;
import gigi.storage.Storage;
import gigi.tasks.Deadlines;
import gigi.tasks.Tasklist;
import gigi.ui.Ui;

/**
 * Represents a command to add a Deadline task to the task list.
 * This command is triggered when the user inputs "deadline" followed by a task description and a deadline date.
 */

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private final String description;
    private final LocalDateTime deadlineDate;

    /**
     * Constructs a DeadlineCommand with the specified task description and deadline date.
     *
     * @param description  The description of the deadline task.
     * @param deadlineDate The due date and time of the task.
     */
    public DeadlineCommand(String description, LocalDateTime deadlineDate) {
        this.description = description;
        this.deadlineDate = deadlineDate;
    }

    /**
     * Executes the command to add a Deadline task to the task list.
     * The task is created, added to the list, saved to storage, and a confirmation message is displayed.
     *
     * @param tasks   The task list where the deadline task will be added.
     * @param ui      The UI component responsible for displaying messages.
     * @param storage The storage component for saving tasks.
     * @throws GigiException If an error occurs while saving tasks.
     */
    @Override
    public String execute(Tasklist tasks, Ui ui, Storage storage) throws GigiException, IOException {
        Deadlines deadline = new Deadlines(description, deadlineDate);
        tasks.addTask(deadline);

        tasks.saveTasks(storage);

        return ui.showAddMessage() + "\n"
                + ui.showMessage(String.valueOf(deadline)) + "\n"
                + ui.showTaskNumber(tasks);
    }
}
