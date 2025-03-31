package clovis;

import java.util.ArrayList;
import java.util.Scanner;

import clovis.task.Task;
import clovis.task.TaskList;

/**
 * The {@code Ui} class handles user interaction by displaying messages and reading user input.
 */
public class Ui {
    private final Scanner sc;

    /**
     * Constructs a {@code Ui} instance and initializes the {@code Scanner} for user input.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user.
     *
     * @return a String containing the welcome message.
     */
    public static String displayWelcome() {
        return "Hello I'm Clovis.\nWhat can I do for you?";
    }

    /**
     * Reads a command input by the user.
     *
     * @return the user's input as a {@code String}.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays a message to the user.
     *
     * @param message the message to be displayed.
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to be displayed.
     */
    public void displayErrorMessage(String message) {
        System.err.println(message);
    }

    /**
     * Displays a goodbye message to the user.
     *
     * @return a String containing the goodbye message.
     */
    public String displayGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays a confirmation message that a task has been added to the list.
     *
     * @param task the task that was added.
     * @param tasks the task list to which the task was added.
     * @return a formatted String confirming the addition of the task and the updated list size.
     */
    public String displayAddMessage(Task task, TaskList tasks) {
        return "Got it. I've added this task:\n" + task
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Displays a confirmation message that a task has been deleted from the list.
     *
     * @param task the task that was deleted.
     * @param tasks the list from which the task was deleted.
     * @return a formatted String confirming the deletion of the task and the updated list size.
     */
    public String displayDeleteMessage(Task task, TaskList tasks) {
        return "Noted. I've removed this task:\n" + task
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Displays the list of tasks that contain the specified keyword.
     *
     * @param matchingTasks the list of tasks that match the specified keyword.
     * @param keyword the keyword used for searching tasks.
     * @return a formatted String listing all matching tasks, or a message indicating no matches found.
     *
     */
    public String listMatchingTasks(ArrayList<Task> matchingTasks, String keyword) {
        if (matchingTasks.isEmpty()) {
            return "No tasks matching " + keyword;
        } else {
            StringBuilder sb = new StringBuilder("Here are the matching task/task in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                sb.append((i + 1)).append(". ").append(matchingTasks.get(i)).append("\n");
            }
            return "Here are the matching task/task in your list:" + sb.toString();
        }
    }

    /**
     * Displays all the tasks in the task list.
     *
     * @param tasks the list of tasks containing the tasks.
     * @return a formatted String listing all tasks, or a message indicating the list is empty.
     */
    public String listTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "List is empty";
        }

        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.getTask(i + 1)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Displays a confirmation message that a task has been marked as completed.
     *
     * @param task The task that was marked as done.
     * @return a formatted String confirming the task is marked as done.
     */
    public String displayMarkMessage(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Displays a confirmation message that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     * @return a formatted String confirming the task is marked as not done.
     */
    public String displayUnmarkMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }
}
