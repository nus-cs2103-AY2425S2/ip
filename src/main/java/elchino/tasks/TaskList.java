package elchino.tasks;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor for a task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for a task list with existing tasks.
     * @param tasks The existing tasks to add to the list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list.
     * @param index The index of the task to remove.
     * @return The task that was removed.
     */
    public Task removeTask(int index) {
        return tasks.remove(index - 1);
    }

    /**
     * Marks a task as done.
     * @param index The index of the task to mark as done.
     */
    public void markTask(int index) {
        tasks.get(index - 1).setDone();
    }

    /**
     * Marks a task as not done.
     * @param index The index of the task to mark as not done.
     */
    public void unmarkTask(int index) {
        tasks.get(index - 1).setNotDone();
    }

    /**
     * Returns all tasks in the list as a string.
     */
    public String getTasksAsString() {
        if (tasks.isEmpty()) {
            return "No hay tareas.";
        }

        StringBuilder string = new StringBuilder("Estas son tus tareas:\n");
        for (int i = 0; i < tasks.size(); i++) {
            string.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return string.toString().trim();
    }

    /**
     * Retrieves a task from the list.
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Retrieves all tasks in the list.
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves the number of tasks in the list.
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /*
    * Returns an ArrayList of tasks that contain the keyword in their description.
    */
    public ArrayList<String> findTasks(String keyword) {
        ArrayList<String> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task.toString());
            }
        }
        return matchingTasks;
    }
}