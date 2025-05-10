package kyrie;

import java.util.ArrayList;

/**
 * Represents the task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private static int SIZE = 100;
    private int taskCount = 0;
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        this.taskCount++;
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to be deleted, 1-indexed.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        Task task = this.tasks.get(index - 1);
        this.tasks.remove(index - 1);
        this.taskCount--;
        return task;
    }

    /**
     * Retrieves a task from the task list by index.
     *
     * @param index The index of the task to be retrieved, 1-indexed.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

    /**
     * Retrieves the last task in the task list.
     *
     * @return The last task in the task list.
     */
    public Task getTask() {
        return this.tasks.get(this.taskCount - 1);
    }

    /**
     * Retrieves the maximum size of the task list.
     * 
     * @return The maximum size of the task list.
     */
    public int getMaxSize() {
        return TaskList.SIZE;
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked, 1-indexed.
     */
    public void markTaskAsDone(int index) {
        this.tasks.get(index - 1).markAsDone();
    }

    /**
     * Unmarks a task as done.
     *
     * @param index The index of the task to be unmarked, 1-indexed.
     */
    public void unmarkTaskAsDone(int index) {
        this.tasks.get(index - 1).unmarkAsDone();
    }

    /**
     * Lists all the tasks in the task list.
     */
    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + this.tasks.get(i));
        }
    }

    /**
     * Retrieves the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getTaskCount() {
        return this.taskCount;
    }

    /**
     * Checks if the task list is full.
     * 
     * @return True if the task list is full, false otherwise.
     */
    public Boolean isFull() {
        return this.taskCount == TaskList.SIZE;
    }

    /**
     * Converts the task list to a string suitable for files.
     *
     * @return The string representation of the task list suitable for files.
     */
    public String toFileString() {
        String output = "";
        for (Task task : this.tasks) {
            output += task.toFileString() + "\n";
        }
        return output;
    }

    /**
     * Finds tasks that contain the keyword.
     * 
     * @param keyword The keyword to search for.
     * @return The list of tasks that contain the keyword.
     */
    public TaskList findTasks(String keyword) {
        TaskList foundTasks = new TaskList();
        for (Task task : this.tasks) {
            if (task.getName().contains(keyword)) {
                foundTasks.addTask(task);
            }
        }
        return foundTasks;
    }
}