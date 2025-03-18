package clank.command;

import java.util.ArrayList;

import clank.exception.ClankException;
import clank.task.Task;
import clank.task.TaskList;
import clank.utility.Storage;
import clank.utility.Ui;

/**
 * Represents a command that retrieves and displays upcoming tasks within a specified number of days.
 * This command can be triggered manually by the user or automatically at application startup.
 */
public class ReminderCommand extends Command {
    private long days;
    private boolean isLaunch;

    /**
     * Constructs a {@code ReminderCommand} that retrieves upcoming tasks within the specified number of days.
     * This command can be used both at application startup and manually by the user.
     *
     * @param input The raw input string containing the reminder command and number of days.
     * @param isLaunch {@code true} if the command is triggered at application startup,
     *                 {@code false} if triggered manually.
     * @throws ClankException If the input format is incorrect (e.g., missing the number of days).
     */
    public ReminderCommand(String input, boolean isLaunch) throws ClankException {
        try {
            String[] parts = input.split(" ");
            if (parts.length != 2) {
                throw new ClankException(ClankException.ErrorType.INVALID_FORMAT,
                        "reminder <number of days>");
            }

            this.days = Integer.parseInt(parts[1]);
            this.isLaunch = isLaunch;
        } catch (NumberFormatException e) {
            System.out.println("Oh no! That's not a number!");
        }
    }

    /**
     * Executes the reminder command by retrieving upcoming tasks within the specified number of days.
     * Displays the upcoming tasks through the UI.
     *
     * @param taskList The task list containing all tasks.
     * @param ui The UI instance used to display the tasks.
     * @param storage The storage system (not modified in this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> upcomingTasks = taskList.findUpcomingTasks(days);
        ui.showUpcomingTasks(upcomingTasks, isLaunch);
    }
}
