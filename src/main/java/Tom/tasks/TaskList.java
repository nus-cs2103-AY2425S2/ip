package Tom.tasks;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Manages the task list and its operations, including undo functionality.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Initializes an empty task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Initializes a task list with existing tasks.
     *
     * @param tasks The list of tasks to initialize with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a task from the task list based on the given index.
     *
     * @param index The index of the task to be removed.
     * @return The removed Task object.
     */
    public Task removeTask(int index) {
        if (index < 0 || index >= taskList.size()) {
            System.out.println("Invalid task number");
        }
        return taskList.remove(index);
    }

    /**
     * Marks or unmarks a task in the task list.
     *
     * @param index The index of the task to mark or unmark.
     * @param newStatus True to mark as done, false to mark as not done.
     * @return The updated Task object.
     */
    public Task markTask(int index, boolean newStatus) {
        if (index < 0 || index >= taskList.size()) {
            System.out.println("Invalid task number");
        }
        Task task = taskList.get(index);
        task.markTask();
        return task;
    }

    /**
     * Marks or unmarks a task in the task list.
     *
     * @param index The index of the task to mark or unmark.
     * @param newStatus True to mark as done, false to mark as not done.
     * @return The updated Task object.
     */
    public Task unmarkTask(int index, boolean newStatus) {
        if (index < 0 || index >= taskList.size()) {
            System.out.println("Invalid task number");
        }
        Task task = taskList.get(index);
        task.unmarkTask();
        return task;
    }

    /**
     * Prints all tasks in the task list.
     */
    public String printTaskList() {
        if (taskList.isEmpty()) {
            return "No tasks available";
        }

        StringBuilder sb = new StringBuilder("Here are your tasks:\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append((i + 1) + ". " + taskList.get(i) + "\n");
        }
        return sb.toString();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The ArrayList of Task objects.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns the number of tasks.
     *
     * @return The number of Tasks.
     */
    public int getTaskListSize() { return taskList.size(); }

}
