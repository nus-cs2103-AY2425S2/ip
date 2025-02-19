package julie;

import julie.task.Task;

/**
 * Handles all user interactions for the chatbot.
 * Responsible for displaying messages, reading user input, and formatting responses.
 */
public class UI {
    private StringBuilder responseBuffer;

    /**
     * Constructs a new {@code UI} instance.
     * Initializes the scanner to read user input.
     */
    public UI() {
        this.responseBuffer = null;
    }

    public void enableCaptureMode() {
        responseBuffer = new StringBuilder();
    }

    public String getCapturedResponse() {
        String response = responseBuffer.toString().trim();
        responseBuffer = null; // Disable capture mode
        return response;
    }

    /**
     * Displays a general message.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        if (responseBuffer != null) {
            responseBuffer.append(message).append("\n");
        } else {
            System.out.println(message);
        }
    }


    /**
     * Displays the welcome message when the chatbot starts.
     *
     * @return
     */
    public String showWelcome() {
        return "Hello! I'm Julie.\nWhat can I do for you?";
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        if (responseBuffer != null) {
            responseBuffer.append(message).append("\n");
        } else {
            System.err.println(message);
        }
    }

    /**
     * Displays the list of tasks currently stored.
     *
     * @param tasks The {@code TaskList} containing the tasks to be displayed.
     */
    public void showTaskList(TaskList tasks) {
        showMessage(tasks.getTaskListString());
    }

    /**
     * Displays the list of tasks matching the keyword.
     *
     * @param tasks The {@code TaskList} containing the tasks to search in.
     * @param keyword The search keyword.
     */
    public void showFoundTaskList(TaskList tasks, String keyword) {
        showMessage(tasks.getFoundTaskListString(keyword));
    }

    /**
     * Acknowledges the successful addition of a task.
     *
     * @param task The task that was added.
     * @param size The new total number of tasks.
     */
    public void ackMessage(Task task, int size) {
        String msg = "Got it! I've added this task to the list:\n" + task
                + "\nNow you have " + size + " tasks in the list.\n";
        showMessage(msg);
    }

    /**
     * Displays a message confirming that a task was successfully deleted.
     *
     * @param task The task that was deleted.
     * @param size The new total number of tasks remaining.
     */
    public void deleteMessage(Task task, int size) {
        String msg = "Noted, the following task has been removed:\n" + task
                + "\nNow you have " + size + " tasks in the list.\n";
        showMessage(msg);
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void markMessage(Task task) {
        showMessage("Nice! I've marked this task as done!\n" + task);
    }

    /**
     * Displays a message confirming that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void unmarkMessage(Task task) {
        showMessage("Okay, I have marked this task as undone!\n" + task);
    }

    /**
     * Displays the goodbye message when the chatbot exits.
     */
    public String showGoodbye() {
        return "Goodbye. See you later!";
    }

}
