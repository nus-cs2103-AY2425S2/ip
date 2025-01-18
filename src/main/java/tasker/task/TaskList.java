package tasker.task;

import java.util.ArrayList;

/**
 * A List of tasks.
 */
public class TaskList {
    /** Stored tasks of the list */
    private ArrayList<Task> tasks = new ArrayList<>();

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
        tasks.get(index).mark();
    }

    /**
     * Unarks a task in the list as done.
     *
     * @param index The index of the task to unmark as done.
     */
    public void unmarkTask(int index) {
        tasks.get(index).unmark();
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
