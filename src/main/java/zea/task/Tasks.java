package zea.task;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * A collection of Task
 */
public class Tasks {
    private ArrayList<Task> tasks;

    public Tasks() {
        this.tasks = new ArrayList<>();
    }

    public Tasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * @return the total number of tasks
     */
    public int getTotalTasks() {
        return this.tasks.size();
    }

    public Task getTask(int idx) {
        return this.tasks.get(idx);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * @param i - 1-based index of the task to be marked as complete
     */
    public void mark(int i) {
        Task t = this.tasks.get(i);
        t.done();
    }

    /**
     * @param i - 1-based index of the task to be unmarked
     */
    public void unmark(int i) {
        Task t = this.tasks.get(i);
        t.undone();
    }

    /**
     * @param i - 1-based index of the task to be deleted
     */
    public void delete(int i) {
        this.tasks.remove(i);
    }

    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    /**
     * Finds all Task whose description contains the keyword
     * @param keyword - the keyword to search for
     * @return ArrayList of Task
     */
    public ArrayList<Task> find(String keyword) {
        return this.tasks.stream().filter(t ->
                t.description.contains(keyword)).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Task task : this.tasks) {
            sb.append(task.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
