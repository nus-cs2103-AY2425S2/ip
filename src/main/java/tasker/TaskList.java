package tasker;

import java.util.ArrayList;

/**
 * A List of tasks
 */
public class TaskList {
    /** Stored tasks of the list */
    private ArrayList<String> tasks = new ArrayList<>();

    /**
     * Adds a task to this list
     *
     * @param task The task to be added
     */
    public void add(String task) {
        tasks.add(task);
    }

    @Override
    public String toString() {
        int size = tasks.size();
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < size; i++) {
            output.append((i + 1) + ". " + tasks.get(i));

            if (i != size - 1) {
                output.append('\n');
            }
        }

        return output.toString();
    }
}
