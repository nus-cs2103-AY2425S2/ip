package julie;

import java.util.ArrayList;
import java.util.List;

import julie.task.Task;

/**
 * Represents a list of tasks and provides methods to manage them.
 * This class allows tasks to be added, deleted, retrieved, and displayed.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} initialized with an existing list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list based on its index.
     *
     * @param index The index of the task to be removed (zero-based).
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Retrieves a task from the list using a 1-based index.
     *
     * @param index The 1-based index of the task.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task getTask(int index) {
        if (index < 1 || index > tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid task number! Please enter a number between 1 and "
                    + tasks.size());
        }
        return tasks.get(index - 1);
    }

    /**
     * Returns a formatted string of all tasks in the list.
     *
     * @return A string representing the list of tasks.
     */
    public String getTaskListString() {
        if (tasks.isEmpty()) {
            return "Your task list is empty!";
        }

        StringBuilder list = new StringBuilder("Here are your tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            list.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return list.toString().trim();
    }

    /**
     * Returns a formatted string of tasks that match the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return A string representing the list of matching tasks.
     */
    public String getFoundTaskListString(String keyword) {
        StringBuilder found = new StringBuilder("Here are the matching tasks in your list:\n");
        boolean hasMatches = false;

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                found.append(i + 1).append(". ").append(task).append("\n");
                hasMatches = true;
            }
        }

        if (!hasMatches) {
            return "No matching tasks found.";
        }
        return found.toString().trim();
    }

    /**
     * Returns a copy of the list of tasks to maintain encapsulation.
     *
     * @return A new list containing all tasks.
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

}
