package miki.task;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class represents a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks.
     *
     * @return List of tasks.
     */
    public List<Task> getTaskList() {
        return tasks;
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index Index of the task.
     * @return Task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index Index of the task to be deleted.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Returns a list of tasks that contain a given keyword.
     *
     * @param keyword String to be matched for task details.
     * @return List of tasks that contain the keyword.
     */
    public TaskList findTasks(String keyword) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)
                    || task.getTaskType().toString().toLowerCase().contains(keyword)
                    || task.toString().toLowerCase().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return new TaskList(foundTasks);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }
}
