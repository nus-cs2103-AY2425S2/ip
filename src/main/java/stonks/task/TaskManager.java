package stonks.task;

import java.util.ArrayList;

/**
 * Stores and manages all the tasks
 * Handles the addition and deletion of tasks
 */
public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns current tasks
     * @return ArrayList of all current tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Add a new task into the list
     * @param task task to be added
     */
    public void addTask(Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).isSameTask(task)) {
                if (tasks.get(i).isDone) {
                    task.markDone();
                }
                tasks.remove(i);
                tasks.add(i, task);
                return;
            }
        }
        tasks.add(task);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (i == tasks.size() - 1) {
                output.append(String.format("     %d. %s", i + 1, tasks.get(i).toString()));
            } else {
                output.append(String.format("     %d. %s\n", i + 1, tasks.get(i).toString()));
            }
        }
        return output.toString();
    }

    /**
     * Mark a task as completed
     * @param index index of task
     * @return task marked completed
     */
    public Task mark(int index) {
        this.tasks.get(index).markDone();;
        return this.tasks.get(index);
    }

    /**
     * Mark a task as incomplete
     * @param index index of task
     * @return task marked uncompleted
     */
    public Task unmark(int index) {
        this.tasks.get(index).markNotDone();
        return this.tasks.get(index);
    }

    /**
     * Returns total number of tasks
     * @return size of list of tasks
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Delete a task at the given index from the list
     * @param index index of task to be deleted
     * @return task that is deleted
     */
    public Task deleteTask(int index) {
        Task task = this.tasks.get(index);
        this.tasks.remove(index);
        return task;
    }
}
