package g.ui;

import java.util.ArrayList;

import g.tasks.Task;
import g.tasks.TaskList;

/**
 * The Ui class is responsible for generating formatted messages to be displayed
 * to the user through the GUI or console. It centralizes all user-facing text
 * output, providing consistent communication throughout the application.
 */
public class Ui {

    /**
     * Displays a generic message.
     *
     * @param message The message to display.
     * @return The formatted message.
     */
    public String showMessage(String message) {
        assert message != null : "UI message should not be null!";
        return message;
    }


    /**
     * Displays a welcome message when the application starts.
     *
     * @return The welcome message.
     */
    public String showWelcome() {
        return showMessage("Hello! I'm G\nWhat can I do for you?");
    }

    /**
     * Displays an error message.
     *
     * @param error The error message.
     * @return The formatted error message.
     */
    public String showError(String error) {
        return showMessage("Error: " + error);
    }

    /**
     * Displays an exit message when the application is closing.
     *
     * @return The exit message.
     */
    public String showExit() {
        return showMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the list of all tasks.
     *
     * @param tasks The task list to display.
     * @return The formatted message showing all tasks.
     */
    public String showTaskList(TaskList tasks) {
        return showMessage(tasks.listTasks());
    }

    /**
     * Displays a success message when adding a task.
     *
     * @param task The task added.
     * @param size The current size of the task list.
     * @return The formatted message for the added task.
     */
    public String displayAddTask(Task task, int size) {
        return showMessage("Got it. I've added this task:\n  " + task + 
                           "\nNow you have " + size + " tasks in the list.");
    }

    /**
     * Displays a success message when deleting a task.
     *
     * @param task The task deleted.
     * @param size The current size of the task list.
     * @return The formatted message for the deleted task.
     */
    public String displayDeleteTask(Task task, int size) {
        return showMessage("Noted. I've removed this task:\n  " + task + 
                           "\nNow you have " + size + " tasks in the list.");
    }

    /**
     * Displays a success message when marking a task as completed.
     *
     * @param task The task marked as done.
     * @return The formatted message for the marked task.
     */
    public String displaySuccessfulMarkTask(Task task) {
        return showMessage("Nice! I've marked this task as done:\n  " + task);
    }

    /**
     * Displays a success message when unmarking a task.
     *
     * @param task The task unmarked.
     * @return The formatted message for the unmarked task.
     */
    public String displaySuccessfulUnmarkTask(Task task) {
        return showMessage("OK, I've marked this task as not done yet:\n  " + task);
    }

    /**
     * Displays the results of a search for tasks containing the keyword.
     *
     * @param tasks   The list of matching tasks.
     * @param keyword The search keyword.
     * @return The formatted search result message.
     */
    public String displayFindResults(ArrayList<Task> tasks, String keyword) {
        if (tasks.isEmpty()) {
            return showMessage("No matching tasks found for keyword: " + keyword);
        } else {
            StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
            return showMessage(sb.toString().trim());
        }
    }

    /**
     * Displays a loading error message if the task data cannot be loaded properly.
     *
     * @return The loading error message.
     */
    public String showLoadingError() {
        return showMessage("Loading error. Unable to load existing task data. Starting with a new task list.");
    }
}
