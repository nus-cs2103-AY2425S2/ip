package bob.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bob.storage.Storage;

/**
 * This class represents a list of tasks. It contains static,
 * methods to manipulate the task list such as:
 * <ul>
 *     <li> {@link #getCount()} </li>
 *     <li> {@link #getTask(int)} </li>
 *     <li> {@link #getTasks()} </li>
 *     <li> {@link #addTask(Task)} </li>
 *     <li> {@link #deleteTaskByNumber(int)} </li>
 *     <li> {@link #deleteTasks(ArrayList)} </li>
 *     <li> {@link #markTask(int)} </li>
 *     <li> {@link #unmarkTask(int)} </li>
 * </ul>
 * If task list is modified using the above methods,
 * the changes are saved to the CSV file.
 *
 * @see Storage
 */
public class TaskList {
    private static List<Task> tasks;

    /**
     * Returns the number of tasks in the list.
     *
     * @return size of list (int).
     */
    public static int getCount() {
        tasks = Storage.fetchTasksFromFile();
        return tasks.size();
    }

    /**
     * Returns the task at the specified index.
     * Uses 1-indexing.
     *
     * @param number (1-indexed).
     * @return Task object.
     */
    public static Task getTask(int number) throws IndexOutOfBoundsException {
        tasks = Storage.fetchTasksFromFile();
        return tasks.get(number - 1);
    }

    /**
     * Returns a copy of the list of tasks.
     *
     * @returns List of Task objects.
     */
    public static List<Task> getTasks() {
        tasks = Storage.fetchTasksFromFile();
        return List.copyOf(tasks);
    }

    /**
     * Adds a task to the list.
     *
     * @param task object.
     * @throws IOException if error during file IO.
     */
    public static void addTask(Task task) throws IOException {
        tasks = Storage.fetchTasksFromFile();
        tasks.add(task);
        Storage.saveTasksToFile(tasks);
    }

    /**
     * Deletes specified tasks from the list.
     *
     * @param taskNumbers task numbers to be deleted.
     * @throws IOException if error during file IO.
     */
    public static void deleteTasks(List<Integer> taskNumbers) throws IOException {
        tasks = Storage.fetchTasksFromFile();

        // removeIf allows deletion without modifying list while iterating
        tasks.removeIf(task -> taskNumbers.contains(tasks.indexOf(task) + 1));

        Storage.saveTasksToFile(tasks);
    }

    /**
     * Marks a task as completed.
     *
     * @param number (1-indexed).
     * @throws IOException if error during file IO.
     * @throws IndexOutOfBoundsException if number is out of range.
     */
    public static void markTask(int number) throws IOException, IndexOutOfBoundsException {
        tasks = Storage.fetchTasksFromFile();
        tasks.get(number - 1).mark();
        Storage.saveTasksToFile(tasks);
    }

    /**
     * Unmarks a task, i.e. not completed.
     *
     * @param number (1-indexed).
     * @throws IOException if error during file IO.
     * @throws IndexOutOfBoundsException if number is out of range.
     */
    public static void unmarkTask(int number) throws IOException, IndexOutOfBoundsException {
        tasks = Storage.fetchTasksFromFile();
        tasks.get(number - 1).unmark();
        Storage.saveTasksToFile(tasks);
    }
}
