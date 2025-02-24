package chitti;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Represents the user interface for the task management application.
 * This class handles the display of task lists and responses for various user actions, such as marking tasks,
 * deleting tasks, adding tasks, and displaying search results.
 */
public class Ui {
    /**
     * Returns a formatted string of the task list, with tasks sorted by their string representation.
     *
     * @param tasks The list of tasks to be displayed.
     * @return A string representation of the task list.
     */
    public String getTaskListString(ArrayList<Task> tasks) {
        Collections.sort(tasks, (task1, task2) -> task1.toString().compareTo(task2.toString()));

        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return response.toString();
    }

    /**
     * Returns a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     * @return A string indicating that the task has been marked as done.
     */
    public String getTaskMarkedString(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Returns a message indicating that a task has been marked as not done yet.
     *
     * @param task The task that has been marked as not done yet.
     * @return A string indicating that the task has been marked as not done yet.
     */
    public String getTaskUnmarkedString(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Returns a message indicating that a task has been deleted from the list.
     *
     * @param task The task that has been deleted.
     * @param size The current number of tasks in the list after deletion.
     * @return A string indicating that the task has been deleted and the updated task count.
     */
    public String getTaskDeletedString(Task task, int size) {
        return "Noted. I've removed this task:\n" + task + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Returns a message indicating that a new task has been added to the list.
     *
     * @param task The task that has been added.
     * @param size The current number of tasks in the list after addition.
     * @return A string indicating that the task has been added and the updated task count.
     */
    public String getTaskAddedString(Task task, int size) {
        return "Got it. I've added this task:\n" + task + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Returns a formatted string of the tasks that match the search criteria.
     *
     * @param tasks The list of tasks that match the search criteria.
     * @return A string representation of the found tasks.
     */
    public String getFoundListString(ArrayList<Task> tasks) {
        StringBuilder response = new StringBuilder("Here are the matching tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return response.toString();
    }
}