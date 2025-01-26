package tasker.task;

import java.util.ArrayList;
import java.util.List;

import tasker.Storage;
import tasker.exception.TaskerException;

/**
 * A List of tasks.
 */
public class TaskList {
    /** Stored tasks of the list */
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Class constructor.
     *
     * @param tasks The list of tasks to load from.
     */
    public TaskList(Storage storage) throws TaskerException {
        for (Task task : storage.getTasks()) {
            this.add(task);
        }
    }

    /**
     * Returns this list of tasks.
     *
     * @return This list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Reports the current number of tasks in the list.
     *
     * @return A report of current list count.
     */
    private String report() {
        return String.format("Now you have %d tasks in the list.", tasks.size());
    };

    /**
     * Adds a task to this list.
     *
     * @param task to be added.
     * @return A report of current list count.
     */
    public String add(Task task) {
        tasks.add(task);
        return report();
    }

    /**
     * Checks if the index exists within the list.
     *
     * @param index The index to check.
     * @return True if valid, false otherwise.
     */
    public boolean isValidIndex(int index) {
        return index < tasks.size();
    }

    /**
     * Deletes a task from this list.
     *
     * @param index The index of the task to be deleted.
     * @return A report of current list count.
     */
    public String delete(int index) {
        tasks.remove(index);
        return report();
    }

    /**
     * Returns the description of the tasks with the provided index.
     *
     * @param index The index of the task to be described.
     * @return Description of the task at the index.
     */
    public String getTaskDescription(int index) {
        return tasks.get(index).toString();
    }

    /**
     * Marks a task in the list as done.
     *
     * @param index The index of the task to mark as done.
     */
    public void markTask(int index) {
        tasks.get(index).setDone(true);
    }

    /**
     * Unarks a task in the list as done.
     *
     * @param index The index of the task to unmark as done.
     */
    public void unmarkTask(int index) {
        tasks.get(index).setDone(false);
    }

    @Override
    public String toString() {
        int size = tasks.size();
        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");

        for (int i = 0; i < size; i++) {
            output.append(String.format("%d.%s", i + 1, tasks.get(i)));

            if (i != size - 1) {
                output.append("\n");
            }
        }

        return output.toString();
    }
}
