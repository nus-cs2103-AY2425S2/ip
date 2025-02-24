package julie.command;

import java.time.format.DateTimeParseException;

import julie.Storage;
import julie.TaskList;
import julie.UI;
import julie.exception.WrongFormatException;
import julie.task.Event;
import julie.task.Task;

/**
 * Represents a command to add a new event task to the task list.
 * An event task includes a description, start date/time, and end date/time.
 */
public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Constructs an {@code AddEventCommand} with the given description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from The start date and time of the event in the format {@code DD-MM-YYYY HHMM}.
     * @param to The end date and time of the event in the format {@code DD-MM-YYYY HHMM}.
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Validates the input fields for an event task.
     *
     * @throws WrongFormatException If any required field is missing.
     */
    private void validateInput() throws WrongFormatException {
        if (description.isEmpty() && from.isEmpty() && to.isEmpty()) {
            throw new WrongFormatException("Oops! Missing description, start date/time, and end date/time!\n"
                    + "Correct format: event <description> /from <DD-MM-YYYY HHMM> /to <DD-MM-YYYY HHMM>");
        }
        if (description.isEmpty()) {
            throw new WrongFormatException("Oops! Missing description of event!\n"
                    + "Correct format: event <description> /from <DD-MM-YYYY HHMM> /to <DD-MM-YYYY HHMM>");
        }
        if (from.isEmpty()) {
            throw new WrongFormatException("Oops! Missing event start date/time!\n"
                    + "Correct format: event <description> /from <DD-MM-YYYY HHMM> /to <DD-MM-YYYY HHMM>");
        }
        if (to.isEmpty()) {
            throw new WrongFormatException("Oops! Missing event end date/time!\n"
                    + "Correct format: event <description> /from <DD-MM-YYYY HHMM> /to <DD-MM-YYYY HHMM>");
        }
    }

    /**
     * Executes the command to add a new event task.
     * The task is added to the task list, saved to storage, and acknowledged via the UI.
     *
     * @param tasks The task list where the new event task will be added.
     * @param ui The user interface to display feedback to the user.
     * @param storage The storage system to persist the task list.
     * @throws WrongFormatException If the event description, start time, or end time is missing,
     *                              or if the date/time format is incorrect.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws WrongFormatException {
        validateInput();

        try {
            Task event = new Event(description, from, to);
            tasks.addTask(event);
            storage.saveTasks(tasks.getAllTasks());
            ui.ackMessage(event, tasks.size());
        } catch (DateTimeParseException e) {
            throw new WrongFormatException("Oops! Wrong date format!\n"
                    + "Correct format: event <description> /from <DD-MM-YYYY HHMM> /to <DD-MM-YYYY HHMM>");
        } catch (IllegalArgumentException e) {
            ui.showMessage(e.getMessage());
        }
    }

}
