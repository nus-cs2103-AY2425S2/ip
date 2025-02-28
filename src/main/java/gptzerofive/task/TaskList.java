package gptzerofive.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks the list of tasks
     */
    public TaskList(List<Task> tasks) {
        assert tasks != null : "Task list should not be null";
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        assert task != null : "Task should not be null";
        tasks.add(task);
    }

    /**
     * Retrieves a task from the task list by its index.
     *
     * @param index the index of the task to retrieve
     * @return the task at the specified index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Removes a task from the task list by its index.
     *
     * @param index the index of the task to remove
     * @return the removed task
     */
    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return the number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Filters the tasks based on the keyword.
     *
     * @param keyword the keyword to filter by
     * @return TaskList containing tasks that contain the keyword
     */
    public TaskList filterTasks(String keyword) {
        assert keyword != null : "Keyword should not be null";
        List<Task> filteredTasks = tasks.stream().filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
        return new TaskList(filteredTasks);
    }

    /**
     * Returns a string representation of the task list.
     *
     * @return a string representation of the task list
     */
    public String getTaskListString() {
        StringBuilder returnValue = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            returnValue.append(i + 1).append(".").append(task.toString()).append("\n");
        }
        return returnValue.toString();
    }
}
