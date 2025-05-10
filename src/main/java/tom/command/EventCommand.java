package tom.command;

import java.time.LocalDate;

import tom.exception.InvalidDateException;
import tom.parser.Parser;
import tom.storage.Storage;
import tom.task.Event;
import tom.tasklist.TaskList;
import tom.ui.Ui;

/**
 * Represents a command to add an event task to the task list.
 */
public class EventCommand extends Command {

    private String description;
    private String start;
    private String end;

    /**
     * Constructs an EventCommand with the specified description, start date, and end date.
     *
     * @param description The description of the task.
     * @param start The start date of the event.
     * @param end The end date of the event.
     */
    public EventCommand(String description, String start, String end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    /**
     * Executes the command to add an event task to the task list.
     *
     * @param tasks The task list.
     * @param ui The UI for interacting with the user.
     * @param storage The storage for saving tasks.
     * @throws InvalidDateException if the date string does not match the date pattern.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDateException {
        LocalDate dateStart = Parser.stringToDate(start);
        LocalDate dateEnd = Parser.stringToDate(end);
        Event task = new Event(description, dateStart, dateEnd);
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
