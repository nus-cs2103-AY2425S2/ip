package ricky;

import ricky.task.Task;
import ricky.task.TaskList;

/**
 * Handles interactions with the user.
 */
public class Ui {

    /**
     * Returns the welcome message.
     *
     * @return the welcome message.
     */
    public String getWelcomeMessage() {
        return "Hello! I'm Ricky.\nWhat can I do for you?";
    }

    /**
     * Returns the goodbye message.
     *
     * @return the goodbye message.
     */
    public String getGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a message listing all tasks.
     *
     * @param tasks The list of tasks.
     * @return the list message.
     */
    public String getListMessage(TaskList tasks) {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            output += (i + 1) + "." + tasks.get(i) + "\n";
        }
        return output;
    }

    /**
     * Returns a message indicating that a task has been added.
     *
     * @param task  The task that was added.
     * @param tasks The list of tasks.
     * @return the add message.
     */
    public String getAddMessage(Task task, TaskList tasks) {
        return "Got it. I've added this task:\n" + task + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Returns a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     * @return the mark message.
     */
    public String getMarkMessage(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Returns a message indicating that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     * @return the unmark message.
     */
    public String getUnmarkMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Returns a message indicating that a task has been deleted.
     *
     * @param task  The task that was deleted.
     * @param tasks The list of tasks.
     * @return the delete message.
     */
    public String getDeleteMessage(Task task, TaskList tasks) {
        return "Noted. I've removed this task:\n" + task + "\nNow you have " + (tasks.size() - 1)
                + " tasks in the list.";
    }

    /**
     * Prints an error message indicating that there was an error loading the file.
     */
    public void showLoadingError() {
        System.out.println("Error loading file.");
    }

    /**
     * Prints an error message indicating that there was an error storing the file.
     */
    public void showStorageError() {
        System.out.println("Error storing file.");
    }

    /**
     * Returns a message indicating that the command is invalid.
     *
     * @return the invalid command message.
     */
    public String printInvalidCommand() {
        return "I don't know what that means. Please enter a valid command.";
    }

    /**
     * Returns a message listing all matching tasks.
     *
     * @param tasks The list of matching tasks.
     * @return the find message.
     */
    public String getFindMessage(TaskList tasks) {
        if (tasks.size() == 0) {
            return "There are no matching tasks in your list.";
        } else {
            String output = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                output += (i + 1) + "." + tasks.get(i) + "\n";
            }
            return output;
        }
    }

    /**
     * Returns a message indicating that a task already exists in the list.
     *
     * @param task The task that already exists.
     * @return the duplicate message.
     */
    public String getDuplicateMessage(Task task) {
        return "This task already exists in your list:\n" + task;
    }
}
