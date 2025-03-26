package viktor.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import viktor.exceptions.ViktorException;
import viktor.parser.DateParser;
import viktor.tasks.DeadlineTask;
import viktor.tasks.EventTask;
import viktor.tasks.Task;
import viktor.tasks.TaskList;

/**
 * Command to list all tasks on a specific date.
 */
public class TimeCommand implements Commandable {
    private final TaskList tasks;
    private final String dateInput;

    /**
     * Constructs a TimeCommand with the given TaskList and date input.
     *
     * @param tasks The TaskList to search for tasks.
     * @param dateInput The date to search for tasks on.
     */
    public TimeCommand(TaskList tasks, String dateInput) {
        this.tasks = tasks;
        this.dateInput = dateInput;
    }

    /**
     * Executes the command to list all tasks on the specified date.
     *
     * @throws ViktorException If the input is invalid or there is an error with task creation.
     */
    @Override
    public String execute() throws ViktorException {
        LocalDate targetDate;

        try {
            targetDate = DateParser.parseDateOnly(dateInput);
        } catch (DateTimeParseException e) {
            throw new ViktorException("Invalid date format! Use 'd/M/yyyy', e.g., '12/12/2025'.");
        }

        boolean isFound = false;
        StringBuilder response = new StringBuilder(
                "Here are your tasks for " + DateParser.formatDate(targetDate) + ":\n");
        for (Task task : tasks.getTasks()) {
            if ((task instanceof DeadlineTask && ((DeadlineTask) task).matchesDate(targetDate))
                    || (task instanceof EventTask && ((EventTask) task).matchesDate(targetDate))) {
                response.append(task).append("\n");
                isFound = true;
            }
        }

        if (!isFound) {
            return "Are you truly a scientist? There are no tasks for "
                + DateParser.formatDate(targetDate) + "!";
        }
        return response.toString();
    }
}
