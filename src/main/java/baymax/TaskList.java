package baymax;

import java.util.ArrayList;

import baymax.task.Task;
/**
 * Represents a list of tasks managed by the Baymax chatbot.
 * Provides functionality to add, delete, mark, and unmark tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a new empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a {@code TaskList} initialized with an existing list of tasks.
     *
     * @param taskList The list of tasks to initialize with.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The new task to be added.
     */
    public void addTask(Task task) {
        assert task != null : "Task being added should never be null";
        tasks.add(task);
    }

    /**
     * Marks a task as completed.
     *
     * @param taskNumber The index (1-based) of the task to mark as completed.
     * @return The task that was marked as completed.
     */
    public Task markAsDone(int taskNumber) {
        assert (taskNumber > 0 && taskNumber <= tasks.size()) : "Invalid task number for marking as done";
        Task task = tasks.get(taskNumber - 1);
        task.markAsCompleted();
        return task;
    }

    /**
     * Unmarks a task, changing its status to incomplete.
     *
     * @param taskNumber The index (1-based) of the task to unmark.
     * @return The task that was unmarked.
     */
    public Task unmark(int taskNumber) {
        assert (taskNumber > 0 && taskNumber <= tasks.size()) : "Invalid task number for un-marking task";
        Task task = tasks.get(taskNumber - 1);
        task.unmarkAsCompleted();
        return task;
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskNumber The index (1-based) of the task to delete.
     * @throws IndexOutOfBoundsException If the given task number is invalid.
     */
    public void delete(int taskNumber) throws IndexOutOfBoundsException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid task number! Please enter a valid index.");
        }
        tasks.remove(taskNumber - 1);
    }

    /**
     * Returns the list of tasks.
     *
     * @return An {@code ArrayList} containing all tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Checks if the task list is empty.
     *
     * @return {@code true} if the task list is empty, otherwise {@code false}.
     */
    public Boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Searches for tasks that contain the specified keyword in their name.
     * The search is case-insensitive.
     *
     * @param keyword The keyword to search for within task names.
     * @return A new {@code TaskList} containing tasks that match the search keyword.
     */
    public TaskList findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<Task>();
        for (Task task : tasks) {
            if (task.getName().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        TaskList matchingTaskList = new TaskList(matchingTasks);
        return matchingTaskList;
    }
}
