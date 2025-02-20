package command;

import core.TaskList;
import ui.Ui;
import storage.Storage;
import exception.BaimiException;
import task.Deadline;

/**
 * Represents a command to add a new deadline task to the task list.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Constructs an AddDeadlineCommand with a specified task description and deadline.
     *
     * @param description The description of the deadline task to add.
     * @param by The deadline of the task.
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the add deadline command, adding a new deadline task to the task list.
     *
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage handler.
     * @return The response to the user command.
     * @throws BaimiException If an error occurs during the execution of the command.
     */
    @Override
    public String executeAndGetResponse(TaskList tasks, Ui ui, Storage storage) throws Exception {
        Deadline deadline = new Deadline(description, by);
        tasks.addTasks(deadline);
        storage.save(tasks.getTasks());

        return "Got it baby. I've added this task:\n  " + deadline +
                "\nNow you have " + tasks.getTasks().size() + " tasks in the list.";
    }
}
