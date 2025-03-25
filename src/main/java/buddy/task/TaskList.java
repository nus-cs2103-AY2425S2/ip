package buddy.task;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents task list
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Instantiates a new Task list.
     *
     * @param tasks the tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds new task to current task list.
     *
     * @param task The new task.
     */
    public void addTask(Task task) {
        int oldSize = this.tasks.size();
        this.tasks.add(task);

        assert this.tasks.size() == oldSize + 1 : "Task list size should increase by 1 after adding a task";
    }

    /**
     * Returns the size of task list.
     *
     * @return The size of task list.
     */
    public int getLength() {
        return this.tasks.size();
    }

    /**
     * Returns task indicated by index.
     *
     * @param index Task index in the task list.
     * @return Task indicated by index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Deletes taskToDelete in the task list
     *
     * @param taskToDelete Task that needs to be deleted.
     */
    public void deleteTask(Task taskToDelete) {
        int oldSize = this.tasks.size();

        this.tasks = this.tasks.stream()
                .filter(task -> !task.equals(taskToDelete))
                .collect(Collectors.toCollection(ArrayList::new));

        assert this.tasks.size() <= oldSize : "Task list size should not increase after deletion";
    }

    /**
     * Filters task list.
     *
     * @param keyword the keyword
     * @return the filtered task list
     */
    public TaskList filter(String keyword) {
        ArrayList<Task> filteredTasks = this.tasks.stream()
                .filter(t -> t.getDescription() != null && t.getDescription().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));

        assert filteredTasks.size() <= this.tasks.size() : "Filtered task list cannot be larger than original list";
        return new TaskList(filteredTasks);
    }
}
