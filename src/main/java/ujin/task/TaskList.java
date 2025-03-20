package ujin.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks. This class provides methods for managing the collection
 * of tasks, including adding, deleting, and retrieving tasks.
 * Tasks are stored in a {@link List}, and operations on the list can be performed using
 * the available methods, such as adding a task, removing a task, and getting a task by its index.
 */
public class TaskList {

    /**
     * The list of tasks in the task list.
     */
    protected List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a {@link TaskList} with the specified list of tasks.
     *
     * @param li A list of {@link Task} objects to initialize the task list.
     */
    public TaskList(List<Task> li) {
        this.tasks = li;
    }

    /**
     * Adds a task to the task list.
     *
     * @param t The {@link Task} to be added to the list.
     */
    public void add(Task t) {
        tasks.add(t);
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
     * Deletes the task at the specified index from the task list.
     *
     * @param index The index of the task to be deleted.
     */
    public void delete(int index) {
        tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index from the task list.
     *
     * @param i The index of the task to retrieve.
     * @return The {@link Task} at the specified index.
     */
    public Task get(int i) {
        return tasks.get(i);
    }
}
