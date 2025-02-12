package duchess;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param t The task to be added.
     */
    public void add(Task t) {
        this.taskList.add(t);
    }

    /**
     * Removes a task from the task list by index.
     *
     * @param i The index of the task to be removed.
     */
    public void remove(int i) {
        this.taskList.remove(i);
    }

    /**
     * Retrieves a task from the task list by index.
     *
     * @param i The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int i) {
        return this.taskList.get(i);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return this.taskList.size();
    }
    /**
     * Finds tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of matching tasks.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getTaskname().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    public void sort() {
        Collections.sort(taskList, Comparator.comparing(Task::getTaskname));
    }
}
