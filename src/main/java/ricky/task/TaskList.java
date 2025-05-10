package ricky.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to be returned.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        assert index >= 0 && index < tasks.size() : "Index should be within the valid range.";
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index The index of the task to be deleted.
     */
    public void delete(int index) {
        assert index >= 0 && index < tasks.size() : "Index should be within the valid range.";
        tasks.remove(index);
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markDone(int index) {
        assert index >= 0 && index < tasks.size() : "Index should be within the valid range.";
        tasks.get(index).markDone();
    }

    /**
     * Marks the task at the specified index as undone.
     *
     * @param index The index of the task to be marked as undone.
     */
    public void markUndone(int index) {
        assert index >= 0 && index < tasks.size() : "Index should be within the valid range.";
        tasks.get(index).markUndone();
    }

    /**
     * Finds tasks that contain the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks that contain the specified keyword.
     */
    public TaskList find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return new TaskList(matchingTasks);
    }

    public TaskList checkDuplicate(Task task) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (t.toString().equals(task.toString())) {
                matchingTasks.add(t);
            }
        }
        return new TaskList(matchingTasks);
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
