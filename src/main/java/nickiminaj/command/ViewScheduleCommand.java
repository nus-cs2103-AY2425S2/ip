package nickiminaj.command;


import nickiminaj.NickiMinajException;
import nickiminaj.Storage;
import nickiminaj.TaskList;
import nickiminaj.Ui;
import nickiminaj.tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a command to view the user's task schedule for a specific date.
 */
public class ViewScheduleCommand extends Command {
    private LocalDate date;

    /**
     * Constructs a {@code ViewScheduleCommand} with the specified date.
     *
     * @param date The date for which the schedule is requested, in YYYY-MM-DD format.
     * @throws NickiMinajException If the date format is invalid.
     */
    public ViewScheduleCommand(String date) throws NickiMinajException {
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new NickiMinajException("Invalid date format! Please use YYYY-MM-DD.");
        }
    }

    /**
     * Executes the command by filtering and displaying tasks scheduled for the specified date.
     *
     * @param tasks   The task list to search through.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage system (not used in this command but included for consistency).
     * @throws NickiMinajException If an error occurs while executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NickiMinajException {
        List<Task> scheduledTasks = tasks.getTasks().stream()
                .filter(task -> task.isOnDate(date))
                .collect(Collectors.toList());

        if (scheduledTasks.isEmpty()) {
            ui.showError("No tasks scheduled for " + date + ".");
        } else {
            TaskList filteredTaskList = new TaskList(scheduledTasks);
            ui.showMatchingTasks(filteredTaskList);
        }
    }
}

