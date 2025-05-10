package partillay.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the system.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new empty TaskList.
     * Initializes the tasks as an empty list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with a given list of tasks.
     *
     * @param tasks the list of tasks to initialize the TaskList with
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int indexToDelete) {
        return tasks.remove(indexToDelete);
    }

    public int size() {
        return tasks.size();
    }

    public void markTask(int index) {
        tasks.get(index).mark();
    }

    public void unmarkTask(int index) {
        tasks.get(index).unmark();
    }

    public String getTaskAsString(int index) {
        return tasks.get(index) + "";
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }


}
