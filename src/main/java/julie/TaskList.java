package julie;

import java.util.ArrayList;
import java.util.List;

import julie.task.Task;

/**
 * Represents a list of tasks and provides methods to manage them.
 * This class allows tasks to be added, deleted, retrieved, and displayed.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

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
     * Retrieves a task from the list by its index.
     *
     * @param index The index of the task (zero-based).
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Displays all tasks in the list with index numbers.
     */
    public void showTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Displays tasks that contain the given keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public void showFoundTasks(String keyword) {
        boolean hasMatches = false;
        System.out.println("Here are the matching tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println((i + 1) + ". " + task);
                hasMatches = true;
            }
        }

        if (!hasMatches) {
            System.out.println("No matching tasks found.");
        }
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
