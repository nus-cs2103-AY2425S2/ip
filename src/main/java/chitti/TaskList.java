package chitti;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * This class manages the collection of tasks and provides methods for adding, deleting, retrieving, and searching tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with the specified initial list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list by its index.
     * The index is 1-based, meaning index 1 refers to the first task in the list.
     *
     * @param index The 1-based index of the task to be deleted.
     */
    void deleteTask(int index) {
        tasks.remove(index - 1);
    }

    /**
     * Retrieves a task from the task list by its index.
     * The index is 1-based, meaning index 1 refers to the first task in the list.
     *
     * @param index The 1-based index of the task to retrieve.
     * @return The task at the specified index.
     */
    Task getTask(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the list.
     */
    int size() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Searches for tasks that contain the specified search string in their string representation.
     *
     * @param search The search string to look for in the tasks.
     * @return A list of tasks that contain the search string.
     */
    ArrayList<Task> findTasks(String search) {
        ArrayList<Task> found = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++){
            if (tasks.get(i).toString().contains(search)){
                found.add(tasks.get(i));
            }
        }
        return found;
    }
}
