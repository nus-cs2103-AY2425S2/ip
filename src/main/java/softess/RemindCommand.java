package softess;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * A command that checks and lists all deadline tasks that are due within the next 3 days.
 */
public class RemindCommand extends Command {

    /**
     * The task list to check for upcoming deadlines.
     */
    private TaskList tasks;

    /**
     * The date-time format expected for deadline tasks.
     */
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Constructs a RemindCommand with the specified user interface and task list.
     *
     * @param ui The user interface handling interactions.
     * @param tasks The task list containing tasks.
     */
    public RemindCommand(UserInterface ui, TaskList tasks) {
        super(ui);
        this.tasks = tasks;
    }

    /**
     * Triggers the command to check for deadline tasks that are due within the next 3 days.
     *
     * @return A string listing all upcoming deadline tasks or a message if none are found.
     */
    @Override
    public String trigger() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime threeDaysLater = now.plusDays(3);
        List<String> upcomingDeadlines = new ArrayList<>();

        for (Task task : this.tasks.getTasks()) {
            if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;
                try {
                    System.out.println(deadlineTask.by);
                    LocalDateTime deadlineDate = LocalDateTime.parse(deadlineTask.by.trim(), INPUT_FORMAT);
                    if (!deadlineDate.isAfter(threeDaysLater)) {
                        upcomingDeadlines.add(deadlineTask.toString());
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Skipping invalid deadline format for task: " + deadlineTask.description);
                }
            }
        }

        if (upcomingDeadlines.isEmpty()) {
            return "No deadlines are due within the next 3 days.";
        }

        StringBuilder result = new StringBuilder("Here are your upcoming deadlines:\n");
        for (String deadline : upcomingDeadlines) {
            result.append(deadline).append("\n");
        }

        return result.toString();
    }
}
