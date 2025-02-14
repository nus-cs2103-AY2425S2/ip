package julie;

import java.util.Scanner;

import julie.task.Task;


/**
 * Handles all user interactions for the chatbot.
 * Responsible for displaying messages, reading user input, and formatting responses.
 */
public class UI {
    private final Scanner scanner;

    /**
     * Constructs a new {@code UI} instance.
     * Initializes the scanner to read user input.
     */
    public UI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the chatbot starts.
     */
    public void showWelcome() {
        drawLine();
        System.out.println("Hello! I'm julie.Julie.\nWhat can I do for you?");
        drawLine();
    }

    /**
     * Draws a horizontal line for formatting output.
     */
    public void drawLine() {
        System.out.println("______________________________________________");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        drawLine();
        System.out.println(message);
        drawLine();
    }

    /**
     * Displays the list of tasks currently stored.
     *
     * @param tasks The {@code TaskList} containing the tasks to be displayed.
     */
    public void showTaskList(TaskList tasks) {
        drawLine();
        System.out.println("Here are your tasks:");
        tasks.showTasks();
        drawLine();
    }

    /**
     * Displays the list of tasks matching the keyword.
     *
     * @param tasks The {@code TaskList} containing the tasks to search in.
     * @param keyword The search keyword.
     */
    public void showFoundTaskList(TaskList tasks, String keyword) {
        drawLine();
        tasks.showFoundTasks(keyword);
        drawLine();
    }

    /**
     * Acknowledges the successful addition of a task.
     *
     * @param task The task that was added.
     * @param size The new total number of tasks.
     */
    public void ackMessage(Task task, int size) {
        drawLine();
        System.out.printf("Got it! I've added this task to the list:%n%s%nNow you have %d tasks in the list.%n",
                task.toString(), size);
        drawLine();
    }

    /**
     * Displays a message confirming that a task was successfully deleted.
     *
     * @param task The task that was deleted.
     * @param size The new total number of tasks remaining.
     */
    public void deleteMessage(Task task, int size) {
        drawLine();
        System.out.printf(
                "Noted, the following task has been removed:%n%s%nNow you have %d tasks in the list.%n",
                task.toString(), size
        );
        drawLine();
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void markMessage(Task task) {
        drawLine();
        System.out.printf("Nice! I've marked this task as done!%n%s%n", task.toString());
        drawLine();
    }

    /**
     * Displays a message confirming that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void unmarkMessage(Task task) {
        drawLine();
        System.out.printf("Okay, I have marked this task as undone!%n%s%n", task.toString());
        drawLine();
    }

    /**
     * Reads and returns the user's command input.
     *
     * @return A trimmed string containing the user input.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays the goodbye message when the chatbot exits.
     */
    public void showGoodbye() {
        System.out.println("Goodbye. See you later!");
    }

}
