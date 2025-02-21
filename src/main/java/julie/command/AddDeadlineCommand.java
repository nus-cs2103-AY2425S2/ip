package julie.command;

import java.time.format.DateTimeParseException;

import julie.Storage;
import julie.TaskList;
import julie.UI;
import julie.exception.WrongFormatException;
import julie.task.Deadline;
import julie.task.Task;


/**
 * Represents a command to add a new deadline task to the task list.
 * A deadline task includes a description and a specific due date/time.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final String dateTime;

    /**
     * Constructs an {@code AddDeadlineCommand} with the given description and deadline date/time.
     *
     * @param description The description of the deadline task.
     * @param dateTime The due date and time of the deadline task in the format {@code DD-MM-YYYY HHMM}.
     */
    public AddDeadlineCommand(String description, String dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    /**
     * Validates the input fields for a deadline task.
     *
     * @throws WrongFormatException If any required field is missing.
     */
    private void validateInput() throws WrongFormatException {
        if (description.isEmpty() && dateTime.isEmpty()) {
            throw new WrongFormatException("Oops! Missing both description and due date/time!\n"
                    + "Correct format: deadline <description> /by <DD-MM-YYYY HHMM>");
        }
        if (description.isEmpty()) {
            throw new WrongFormatException("Oops! Missing description!\n"
                    + "Correct format: deadline <description> /by <DD-MM-YYYY HHMM>");
        }
        if (dateTime.isEmpty()) {
            throw new WrongFormatException("Oops! Missing due date and time!\n"
                    + "Correct format: deadline <description> /by <DD-MM-YYYY HHMM>");
        }
    }

    /**
     * Executes the command to add a new deadline task.
     * The task is added to the task list, saved to storage, and acknowledged via the UI.
     *
     * @param tasks The task list where the new deadline task will be added.
     * @param ui The user interface to display feedback to the user.
     * @param storage The storage system to persist the task list.
     * @throws WrongFormatException If the deadline description or date/time is missing or if the date/time format is
     *      incorrect.
     * */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws WrongFormatException {
        validateInput();

        try {
            Task deadline = new Deadline(description, dateTime);
            tasks.addTask(deadline);
            storage.saveTasks(tasks.getAllTasks());
            ui.ackMessage(deadline, tasks.size());
        } catch (DateTimeParseException e) {
            throw new WrongFormatException("Oops! Wrong date format!\n"
                    + "Correct format: deadline <description> /by <DD-MM-YYYY HHMM>");
        }
    }
}
