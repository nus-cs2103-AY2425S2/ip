package Tom.commands;

import java.util.ArrayList;
import Tom.TomException;
import Tom.tasks.Task;
import Tom.tasks.TaskList;

/**
 * Represents user searching for tasks based on a keyword.
 */
public class FindCommand extends Command {
    private String searchString;

    /**
     * Constructs a FindCommand to search for tasks that contain a given keyword.
     *
     * @param input The user input split into parts.
     * @throws TomException If no keyword is provided.
     */
    public FindCommand(String[] input) throws TomException {
        validateInput(input);
        this.searchString = extractKeyword(input);
    }

    /**
     * Validates the user input.
     *
     * @param input The user input split into parts.
     * @throws TomException If no keyword is provided.
     */
    private void validateInput(String[] input) throws TomException {
        if (input.length < 2 || input[1].trim().isEmpty()) {
            throw new TomException("Please search in this format. Usage: find <keyword>");
        }
    }

    /**
     * Extracts the keyword from the user input.
     *
     * @param input The user input split into parts.
     * @return The lowercase keyword to search for.
     */
    private String extractKeyword(String[] input) {
        return input[1].trim().toLowerCase();
    }

    /**
     * Executes the find command to search for tasks that contain the keyword.
     *
     * @param tasks The task list to search within.
     * @return The string representation of the command's response.
     */
    @Override
    public String execute(TaskList tasks) {
        ArrayList<Task> matchingTasks = findMatchingTasks(tasks);

        return formatResult(matchingTasks);
    }

    /**
     * Finds tasks that contain the keyword.
     *
     * @param tasks The task list to search within.
     * @return A list of matching tasks.
     */
    private ArrayList<Task> findMatchingTasks(TaskList tasks) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.getTaskList()) {
            if (task.toString().toLowerCase().contains(searchString)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Formats the search results after finding to be displayed to the user.
     *
     * @param matchingTasks The list of tasks that match the keyword.
     * @return A formatted string of matching tasks or a message if none are found.
     */
    private String formatResult(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        }

        StringBuilder sb = new StringBuilder("Here are the matching tasks found in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            sb.append((i + 1) + ". " + matchingTasks.get(i) + "\n");

        }
        return sb.toString();
    }
}