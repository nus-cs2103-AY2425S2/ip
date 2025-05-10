package perrygpt.ui;

import perrygpt.tasks.Task;

import java.util.ArrayList;

/**
 * The {@code Ui} class handles all interactions with the user.
 * It provides methods to generate messages such as greetings, errors, success notifications,
 * and task lists.
 */
public class Ui {

    /**
     * Constructs a new {@code Ui} object.
     */
    public Ui() {}

    /**
     * Returns the greeting message when the program starts.
     */
    public String greet() {
        return "Hello! I'm PerryGPT.\n"
                + "What can I do for you?";
    }

    /**
     * Returns the farewell message when the program exits.
     */
    public String farewell() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns an error message.
     *
     * @param message The error message to be displayed.
     * @return The formatted error message as a string.
     */
    public String showError(String message) {
        assert message != null : "Error message should not be null";
        return "Error: " + message;
    }

    /**
     * Returns a success message.
     *
     * @param message The success message to be displayed.
     * @return The formatted success message as a string.
     */
    public String showSuccess(String message) {
        assert message != null : "Success message should not be null";
        return message;
    }

    /**
     * Returns a formatted string of tasks in the list.
     *
     * @param tasks An {@link ArrayList} of {@link Task} objects representing the tasks to be displayed.
     * @return The formatted task list as a string.
     */
    public String showTaskList(ArrayList<Task> tasks) {
        return formatTaskList("Here are the tasks in your list:", tasks);
    }

    /**
     * Returns a formatted string of searched tasks.
     *
     * @param tasks The list of matching tasks.
     * @return The formatted string of tasks or a message if no matches were found.
     */
    public String displayTasks(ArrayList<Task> tasks) {
        return formatTaskList("No matching tasks. Try searching again.", tasks);
    }

    /**
     * Helper method to format a list of tasks into a string.
     *
     * @param emptyMessage The message to display if the list is empty.
     * @param tasks The list of tasks.
     * @return The formatted task list or the empty message.
     */
    private String formatTaskList(String emptyMessage, ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return emptyMessage;
        }

        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            output.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return output.toString().trim();
    }
}
