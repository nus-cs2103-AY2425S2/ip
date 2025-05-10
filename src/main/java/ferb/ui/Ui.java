package ferb.ui;

import ferb.task.Task;
import ferb.tasklist.TaskList;

/**
 * Represents the user interface for the Ferb application.
 */
public class Ui {
    private String message;

    /**
     * Returns the current message.
     *
     * @return the current message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        this.message = "Hello! I'm Ferb\nWhat can I do for you today?";
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodbye() {
        this.message = "Bye. Hope to see you again soon!";
    }

    /**
     * Displays the loading error message.
     */
    public void showLoadingError() {
        this.message = "Error loading file. Creating new task list.";
    }

    /**
     * Displays the message for adding a task.
     *
     * @param task the task that was added
     */
    public void showTaskAdded(Task task) {
        this.message = "Got it. I've added this task:\n  " + task.toString();
    }

    /**
     * Displays the message for marking a task as done.
     *
     * @param task the task that was marked as done
     */
    public void showTaskMarkedDone(Task task) {
        this.message = "Nice! I've marked this task as done:\n  " + task.toString();
    }

    /**
     * Displays the message for unmarking a task as done.
     *
     * @param task the task that was unmarked as done
     */
    public void showTaskUnmarkedDone(Task task) {
        this.message = "Okay! I've marked this task as undone:\n  " + task.toString();
    }

    /**
     * Displays the message for deleting a task.
     *
     * @param task the task that was deleted
     */
    public void showTaskDeleted(Task task) {
        this.message = "Noted. I've removed this task:\n  " + task.toString();
    }

    /**
     * Displays the list of tasks.
     *
     * @param taskList the list of tasks
     */
    public void showTaskList(TaskList taskList) {
        this.message = "Here are the tasks in your list:\n" + taskList;
    }

    /**
     * Displays the message for an empty task list.
     */
    public void showEmptyTaskList() {
        this.message = "You have no tasks in your list.";
    }

    /**
     * Displays the list of tasks that match the search keyword.
     *
     * @param taskList the list of matching tasks
     */
    public void showMatchingTasks(TaskList taskList) {
        if (taskList.isEmpty()) {
            showEmptyTaskList();
        } else {
            this.message = "Here are the tasks in your list:\n" + taskList;
        }
    }

    /**
     * Displays the list of tasks sorted by descriptions.
     *
     * @param taskList the list of tasks sorted by descriptions
     */
    public void showSortedByDescription(TaskList taskList) {
        if (taskList.isEmpty()) {
            showEmptyTaskList();
        } else {
            this.message = "Here are the tasks in your list sorted by descriptions:\n" + taskList;
        }
    }

    /**
     * Displays the list of tasks sorted by date.
     *
     * @param tasklist the list of tasks sorted by date
     */
    public void showSortedByDate(TaskList tasklist) {
        if (tasklist.isEmpty()) {
            showEmptyTaskList();
        } else {
            this.message = "Here are the tasks in your list sorted by date:\n" + tasklist;
        }
    }
}