package task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import storage.Storage;

/**
 * Represents a list of tasks.
 * Provides methods to add, delete, and retrieve tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;

    /**
     * Constructs a TaskList with tasks loaded from storage.
     * @param storage The storage system used to load and save tasks.
     * @throws IOException If an error occurs while loading tasks.
     */
    public TaskList(Storage storage) throws IOException {
        this.storage = storage;
        this.tasks = storage.load();
    }

    /**
     * Adds a task to the task list and saves it to storage.
     * @param task The task to add.
     * @throws IOException If an error occurs while saving tasks.
     */
    public void add(Task task) throws IOException {
        assert task != null : "Cannot add a null task";
        tasks.add(task);
        storage.save(tasks);
    }

    /**
     * Retrieves the task at the specified index.
     * @param index The index of the task to retrieve.
     * @return The Task at the given index.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Marks a task as done.
     * @param index The index of the task (1-based index).
     * @return The updated task.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task markTask(int index) {
        if (index < 1 || index > tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid task number!");
        }
        Task task = tasks.get(index - 1);
        task.markAsDone();
        return task;
    }

    /**
     * Marks a task as not done.
     * @param index The index of the task (1-based index).
     * @return The updated task.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task unmarkTask(int index) {
        if (index < 1 || index > tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid task number!");
        }
        Task task = tasks.get(index - 1);
        task.markAsNotDone();
        return task;
    }

    /**
     * Returns the current list of tasks.
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the list.
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves the most recently added task.
     * @return The last task in the list.
     */
    public Task getLastTask() {
        return tasks.get(tasks.size() - 1);
    }

    /**
     * Prints all tasks currently in the task list.
     */
    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println(" Task list is empty");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + tasks.get(i));
            }
        }
    }

    /**
     * Deletes a task from the list by index and saves the updated list to storage.
     * @param index The index of the task to delete (0-based index).
     * @return The deleted task.
     * @throws IOException If an error occurs while saving tasks.
     */
    public Task delete(int index) throws IOException {
        Task removed = tasks.remove(index);
        storage.save(tasks);
        return removed;
    }

    /**
     * Searches for tasks that contain the given keyword in their description.
     * @param keyword The keyword to search for.
     * @return A list of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        assert keyword != null && !keyword.trim().isEmpty() : "Keyword must not be null or empty";
        return tasks.stream()
                .filter(task -> task.description.toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
