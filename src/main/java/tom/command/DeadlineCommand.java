package tom.command;

import java.time.LocalDate;

import tom.exception.InvalidDateException;
import tom.parser.Parser;
import tom.storage.Storage;
import tom.task.Deadline;
import tom.tasklist.TaskList;
import tom.ui.Ui;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class DeadlineCommand extends Command {

    private String description;
    private String end;

    /**
     * Constructs a DeadlineCommand with the specified description and end date.
     *
     * @param description The description of the task.
     * @param end The end date of the task.
     */
    public DeadlineCommand(String description, String end) {
        this.description = description;
        this.end = end;
    }

    /**
     * Executes the command to add a deadline task to the task list.
     *
     * @param tasks The task list.
     * @param ui The UI for interacting with the user.
     * @param storage The storage for saving tasks.
     * @throws InvalidDateException if the date is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDateException {
        LocalDate dateEnd = Parser.stringToDate(end);
        Deadline task = new Deadline(description, dateEnd);
        tasks.addTask(task);
        ui.showMessage(id, "added %s to tasklist (current size: %d)", task, tasks.size());
    };

    /**
     * Indicates whether this command exits the application.
     *
     * @return false as this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
