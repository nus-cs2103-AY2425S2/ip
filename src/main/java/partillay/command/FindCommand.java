package partillay.command;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import partillay.task.Task;
import partillay.task.TaskList;
import partillay.ui.Ui;

/**
 * Represents a find command to find tasks in a task list.
 */
public class FindCommand extends Command {
    private final String toBeSearched;

    /**
     * Constructs a new Find command.
     *
     * @param text the keyword to search in the task list
     */
    public FindCommand(String text) {
        this.toBeSearched = text;
    }

    /**
     * Iterates through the tasks in task list and displays tasks containing the keyword.
     *
     * @param tasks the task list that stores current tasks
     * @param ui    the user interface for displaying output
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        String result = "Here are the matching tasks in your list:\n";
        ArrayList<Task> tasksToShow = tasks.getTasks();
        int showIndex = 1;
        String regex = "\\b" + Pattern.quote(toBeSearched) + "\\b";
        Pattern pattern = Pattern.compile(regex);
        for (Task task : tasksToShow) {
            Matcher matcher = pattern.matcher(task.getDescription());
            if (matcher.find()) {
                result += showIndex + ". " + task + "\n";
                showIndex++;
            }
        }
        return ui.getLinedMessage(result);
    }
}
