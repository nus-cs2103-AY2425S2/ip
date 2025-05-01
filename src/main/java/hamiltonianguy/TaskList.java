package hamiltonianguy;

import java.util.List;
import hamiltonianguy.tasks.Task;
import hamiltonianguy.tasks.DeadlineTask;
import hamiltonianguy.storage.Storage;
import java.util.Comparator;
import java.util.stream.Collectors;


/**
 * Represents a list of tasks in the chatbot.
 * Provides methods to add, remove, mark, and unmark tasks.
 */

public class TaskList {
    private List<Task> tasks;
    private Storage storage;

    /**
     * Constructs a TaskList and loads tasks from storage.
     *
     * @param storage The storage handler for saving and loading tasks.
     */

    public TaskList(Storage storage) {
        this.storage = storage;
        this.tasks = storage.load(); // Load tasks on startup
    }

    /**
     * Adds a task to the list and saves the list.
     *
     * @param task The task to be added.
     */

    public void addTask(Task task) {
        tasks.add(task);
        storage.save(tasks); // Save after adding
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if empty, false otherwise.
     */

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void sortTasksByDeadline() {
        List<Task> sortedTasks = tasks.stream()
                .filter(task -> task instanceof DeadlineTask)
                .map(task -> (DeadlineTask) task)
                .sorted(Comparator.comparing(DeadlineTask::getBy))
                .collect(Collectors.toList());

        tasks.removeIf(task -> task instanceof DeadlineTask);
        tasks.addAll(sortedTasks);

        System.out.println("Tasks have been sorted by deadline.");
    }

    /**
     * Deletes a task by index and saves the list.
     *
     * @param index The index of the task to delete.
     */

    public void deleteTask(int index) {
        assert index >= 0 && index < tasks.size() : "Invalid index for task deletion";
        Task removedTask = tasks.remove(index);
        storage.save(tasks);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask);
    }

    /**
     * Marks a task as done by index and saves the list.
     *
     * @param index The index of the task to mark as done.
     */

    public void markTask(int index) {
        if (isValidIndex(index)) {
            tasks.get(index).mark();
            storage.save(tasks); // Save after marking done
            System.out.println("      Nice! I've marked this task as done:");
            System.out.println("      " + tasks.get(index));
        }
    }

    /**
     * Unmarks a task as not done by index and saves the list.
     *
     * @param index The index of the task to unmark.
     */

    public void unmarkTask(int index) {
        if (isValidIndex(index)) {
            tasks.get(index).unmark();
            storage.save(tasks); // Save after unmarking
            System.out.println("      OK, I've marked this task as not done yet:");
            System.out.println("      " + tasks.get(index));
        }
    }

    /**
     * Prints all tasks in the list.
     */

    public String getTaskListAsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Validates if an index is within range.
     *
     * @param index The index to check.
     * @return True if valid, false otherwise.
     */

    private boolean isValidIndex(int index) {
        if (tasks.isEmpty()) {
            System.out.println("      OOPS!!! The task list is empty. Add tasks to your list before doing anything else");
            return false;
        }
        if (index < 0 || index >= tasks.size()) {
            System.out.println("      OOPS!!! The task index is out of range.");
            return false;
        }
        return true;
    }

    /**
     * Searches for tasks that contain the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of matching tasks.
     */
    public String findTasks(String keyword) {
        StringBuilder result = new StringBuilder();
        result.append("Here are the matching tasks in your list:\n");

        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().toLowerCase().contains(keyword.toLowerCase())) {
                result.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
                found = true;
            }
        }

        return found ? result.toString() : "No matching tasks found.";
    }


    public int getSize() {
        return tasks.size();
    }

}