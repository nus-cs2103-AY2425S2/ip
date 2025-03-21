package tyler.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import tyler.storage.Storage;
import tyler.task.Deadline;
import tyler.task.Event;
import tyler.task.Task;
import tyler.task.list.TaskList;
import tyler.ui.Ui;

/**
 * Represents a command to print the tasks occurring on a specific date.
 */
public class DateCommand extends Command {
    private final String[] tokens;

    public DateCommand(String[] tokens) {
        super();
        this.tokens = tokens;
    }

    /**
     * Shows the tasks which happen on the specified date.
     *
     * @param tasks The list of tasks to check for matching dates.
     * @param ui The UI object for any required printing.
     * @param storage The storage for I/O involving storing the tasks on the disk (unused).
     * @return The unmodified list.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        LocalDate date = null;
        try {
            date = LocalDate.parse(tokens[1]);
            int i = 1;
            StringBuilder message = new StringBuilder();
            for (Task t : tasks) {
                if (t.getCategory().equals("D")) {
                    if (((Deadline) t).getBy().toLocalDate().equals(date)) {
                        message.append("\t ").append(i).append(". ").append(t).append("\n");
                        i++;
                    }
                } else if (t.getCategory().equals("E")) {
                    if (((Event) t).getFrom().toLocalDate().equals(date)
                            || ((Event) t).getTo().toLocalDate().equals(date)) {
                        message.append("\t ").append(i).append(". ").append(t).append("\n");
                        i++;
                    }
                }
            }
            ui.showMessage(message.toString());
        } catch (DateTimeParseException e) {
            ui.showMessage("\t !!Please enter the date in YYYY-MM-DD format!!");
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showMessage("\t !!Please provide the correct number of arguments!!");
        }
        return tasks;
    }
}
