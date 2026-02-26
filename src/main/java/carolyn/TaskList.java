package carolyn;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private static final long serialVersionUID = 1L;
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param t The {@link Task} to be added to the list.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Deletes a task from the task list based on its index.
     *
     * @param i The zero-based index of the {@link Task} to be removed..
     */
    public void delete(int i) {
        tasks.remove(i);
    }

    /**
     * Retrieves a task from the task list based on its index.
     *
     * @param i The zero-based index of the {@link Task} to retrieve.
     * @return The {@link Task} at the specified index.
     */
    public Task get(int i) throws CarolynException {
        if (i >= tasks.size() || i < 0) {
            throw new CarolynException("Invalid index");
        }
        return tasks.get(i);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of {@link Task} objects currently in the list.
     */
    public int size() {
        return tasks.size();
    }


    public TaskList find(String s) {
        TaskList found = new TaskList();
        assert found.size() == 0 : "at the beginning, the found list should be empty";
        for (int i = 0; i < tasks.size(); i ++) {
            Task t = tasks.get(i);
            if (t.getDescription().contains(s)) {
                found.add(t);
            }
        }
        return found;
    }

    @Override
    public String toString() {
        String indent = "    ";
        String s = "";
        for (int i = 0; i < tasks.size(); i ++) {
            Task item = tasks.get(i);
            String line = indent + (i + 1) + "." + item.toString() + "\n";
            s += line;
        }
        return s;
    }
}