package steve.tasks;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a task that is used to search for tasks matching a given description.
 * This class filters tasks in the {@link TaskManager} based on a description
 * and displays matching tasks, if any.
 */
public class Find extends Task {
    private String description;
    private TaskManager taskManager;

    /**
     * Constructs a {@link Find} task with the given description and task manager.
     *
     * @param description The description used to filter tasks.
     * @param taskManager The TaskManager instance that holds the list of tasks.
     */
    public Find(String description, TaskManager taskManager) {
        super(description);
        this.description = description;
        this.taskManager = taskManager;
    }

    /**
     * Returns a list containing task objects
     */
    public List<Task> getTasks() {
        return taskManager.getTasks().stream()
                .filter(task -> task.getDescription()
                        .toLowerCase()
                        .contains(this.description.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Returns a no task found string
     */
    public String noMatchingTasks() {
        return "_________________________\n"
                + "     There are no matching tasks in your list:\n"
                + "_________________________\n";
    }

    /**
     * Returns a task found string
     */
    public String getMatchingTasks(List<Task> matchingTasks) {
        String s1 = ("_________________________\n"
                + "Here are the matching tasks in your list: \n");
        String s3 = ("_________________________\n");
        String s2 = "";
        int i = 1;
        for (Task task : matchingTasks) {
            s2 = s2 + i + task.list() + "\n";
            i++;
        }
        return s1 + s2 + "\n" + s3;
    }

    /**
     * Filters tasks in the task manager based on the description and prints
     * the matching tasks.
     * <p>
     * This method searches for tasks whose descriptions contain the given
     * search string (case-insensitive) and displays them. If no tasks match,
     * a message is displayed indicating no matches were found.
     */
    public String filterString() {
        List<Task> matchingTasks = getTasks();

        if (matchingTasks.isEmpty()) {
            return noMatchingTasks();
        } else {
            return getMatchingTasks(matchingTasks);
        }
    }
}

