package tasker.task;

import java.util.ArrayList;

/**
 * A List of tasks
 */
public class TaskList {
    /** Stored tasks of the list */
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to this list
     *
     * @param Task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the description of the tasks with the provided index
     *
     * @param index The index of the task to be described
     * @return Description of the task at the index
     */
    public String getTaskDescription(int index) {
        return tasks.get(index).toString();
    }

    /**
     * Returns the number of tasks within the list
     *
     * @return The number of tasks within the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Marks a task in the list as done.
     *
     * @param index The index of the task to mark as done
     */
    public void markTask(int index) {
        tasks.get(index).mark();
    }

    /**
     * Unarks a task in the list as done.
     *
     * @param index The index of the task to unmark as done
     */
    public void unmarkTask(int index) {
        tasks.get(index).unmark();
    }

    @Override
    public String toString() {
        int size = tasks.size();
        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");

        for (int i = 0; i < size; i++) {
            output.append((i + 1) + ". " + tasks.get(i));

            if (i != size - 1) {
                output.append("\n");
            }
        }

        return output.toString();
    }
}
